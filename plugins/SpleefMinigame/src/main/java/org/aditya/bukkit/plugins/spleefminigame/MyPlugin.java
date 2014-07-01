package org.aditya.bukkit.plugins.spleefminigame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {

    public static Location pos1;
    public static Location pos2;
    public BufferedWriter writer;
    public BufferedReader reader;
    List<ArenaLocations> arenaList = new ArrayList<>();

    // This code is called after the server starts and after the /reload command
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());

        try {
            File arenaLocations = new File("arena-locations.txt");
            if (!arenaLocations.exists()) {
                arenaLocations.createNewFile();
            }
            
            reader = new BufferedReader(new FileReader(arenaLocations));

            Location pos1Array;
            Location pos2Array;
            String arenaNameArray;
            String[] splitArray;
            String[] splitArray2;
            String line;

            while ((line = reader.readLine()) != null) {
                splitArray = line.split("   ");
                splitArray2 = splitArray[0].split(",");
                pos1Array = createLocationFromStrings(splitArray2[0],
                        splitArray2[1], splitArray2[2], splitArray2[3]);

                splitArray2 = splitArray[1].split(",");
                pos2Array = createLocationFromStrings(splitArray2[0],
                        splitArray2[1], splitArray2[2], splitArray2[3]);

                arenaNameArray = splitArray[2];

                System.out.println(pos1Array);
                System.out.println(pos2Array);
                System.out.println(arenaNameArray);

                arenaList.add(new ArenaLocations(pos1Array, pos2Array, arenaNameArray));
            }
            
            writer = new BufferedWriter(new FileWriter(arenaLocations, true));

        } catch (IOException ex) {
            Logger.getLogger(MyPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(cmd.getName().equals("spleefarena"))) {
            return false;
        }
        if (!(sender instanceof Player)) {
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Error: "
                    + ChatColor.DARK_RED + "You have not put in any arguments!");
            sender.sendMessage(ChatColor.RED + "Proper usage is: "
                    + ChatColor.DARK_RED + cmd.getUsage());
            return true;
        }

        String arg = args[0];
        Player player = ((Player) sender);

        if ("pos1".equals(arg)) {
            pos1 = player.getLocation();
            player.sendMessage(ChatColor.BLUE + "First position set to ("
                    + pos1.getBlockX() + ", " + pos1.getBlockY() + ", "
                    + pos1.getBlockZ() + ").");
        } else if ("pos2".equals(arg)) {
            pos2 = player.getLocation();
            player.sendMessage(ChatColor.BLUE + "Second position set to ("
                    + pos2.getBlockX() + ", " + pos2.getBlockY() + ", "
                    + pos2.getBlockZ() + ").");
        } else if ("create".equals(arg)) {
            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Error: "
                        + ChatColor.DARK_RED + "You must give a name"
                        + " for the arena!");
                return true;
            }

            if (pos1 == null || pos2 == null) {
                player.sendMessage(ChatColor.RED + "Error: "
                        + ChatColor.DARK_RED + "You have not selected"
                        + " the corner positions for your arena!");
                return true;
            }


            for (int i = 0; i < arenaList.size(); i++) {
                if (arenaList.get(i).getArenaName().equals(args[1])) {
                    player.sendMessage(ChatColor.RED + "Error: "
                            + ChatColor.DARK_RED + "The name you selected"
                            + " is already in use!");
                    return true;
                }
            }

            String worldName = pos1.getWorld().toString().split("}")[0];
            worldName = worldName.split("=")[1];
            String arenaLine = worldName + "," + pos1.getBlockX()
                    + "," + pos1.getBlockY() + "," + pos1.getBlockZ();

            worldName = pos2.getWorld().toString().split("}")[0];
            worldName = worldName.split("=")[1];
            arenaLine = arenaLine + "   " + worldName + "," + pos2.getBlockX()
                    + "," + pos2.getBlockY() + "," + pos2.getBlockZ()
                    + "   " + args[1];

            try {
                writer.write(arenaLine);
                writer.newLine();
                writer.flush();
            } catch (IOException ex) {
                Logger.getLogger(MyPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }

            player.sendMessage(arenaLine);

            int snowBlocks = 0;

            int x1;
            int x2;
            int y1;
            int y2;
            int z1;
            int z2;

            if (pos1.getBlockX() < pos2.getBlockX()) {
                x1 = pos1.getBlockX();
                x2 = pos2.getBlockX();
            } else {
                x2 = pos1.getBlockX();
                x1 = pos2.getBlockX();
            }

            if (pos1.getBlockY() < pos2.getBlockY()) {
                y1 = pos1.getBlockY();
                y2 = pos2.getBlockY();
            } else {
                y2 = pos1.getBlockY();
                y1 = pos2.getBlockY();
            }


            if (pos1.getBlockZ() < pos2.getBlockZ()) {
                z1 = pos1.getBlockZ();
                z2 = pos2.getBlockZ();
            } else {
                z2 = pos1.getBlockZ();
                z1 = pos2.getBlockZ();
            }

            for (int xx = x1; xx <= x2; xx++) {
                for (int yy = y1; yy <= y2; yy++) {
                    for (int zz = z1; zz <= z2; zz++) {
                        Block block = player.getWorld().getBlockAt(xx, yy, zz);
                        if (block.getType() == Material.SNOW_BLOCK) {
                            snowBlocks++;
                        }
                    }
                }
            }

            player.sendMessage(ChatColor.BLUE + "Arena created with " + ChatColor.GOLD
                    + snowBlocks + ChatColor.BLUE + " blocks of snow.");

            arenaList.add(new ArenaLocations(pos1, pos2, args[1]));
        } else if ("help".equals(arg)) {
            player.sendMessage("");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
            player.sendMessage(ChatColor.AQUA + "Spleef Minigame Plugin made by Aditya Gupta");
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Plugin Information");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
            player.sendMessage("");
            player.sendMessage(ChatColor.AQUA + "'/spleefarena pos1' sets the first corner of your arena to where you are standing.");
            player.sendMessage(ChatColor.AQUA + "'/spleefarena pos2' sets the second corner of your arena to where you are standing.");
            player.sendMessage(ChatColor.AQUA + "'/spleefarena create [name]' creates an arena with the selected corners and name.");
            player.sendMessage(ChatColor.AQUA + "'/spleefarena delete [name]' deletes the arena with the specified name.");
            player.sendMessage(ChatColor.AQUA + "'/spleefarena join [name]' joins the specified arena and puts you in a random spot inside.");
            player.sendMessage(ChatColor.AQUA + "'/spleefarena leave' leaves the current arena you are in.");
            player.sendMessage(ChatColor.AQUA + "'/spleefarena list' lists all availible arenas.");
            player.sendMessage(ChatColor.AQUA + "'/spleefarena help' shows this information page.");
        } else if ("list".equals(arg)) {
            String names = "";

            for (int i = 0; i < arenaList.size(); i++) {
                if (i != arenaList.size() - 1) {
                    names = names + arenaList.get(i).getArenaName() + ", ";
                } else {
                    names = names + arenaList.get(i).getArenaName();
                }
            }

            player.sendMessage(ChatColor.GOLD + "Arenas: " + ChatColor.BLUE + names);
        }
        return true;
    }

    public Location createLocationFromStrings(String worldString, String xString, String yString, String zString) {
        Server server = getServer();
        World world = server.getWorld(worldString);
        int X = Integer.parseInt(xString);
        int Y = Integer.parseInt(yString);
        int Z = Integer.parseInt(zString);
        Location locationReturn = new Location(world, X, Y, Z);
        return locationReturn;
    }
}
