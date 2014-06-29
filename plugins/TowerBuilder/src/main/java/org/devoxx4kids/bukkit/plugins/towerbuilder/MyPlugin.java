package org.devoxx4kids.bukkit.plugins.towerbuilder;

import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    // This code is called only once after the server starts

    @Override
    public void onLoad() {
        getLogger().log(Level.INFO, "{0}.onLoad()", this.getClass().getName());
    }

    // This code is called after the server starts and after the /reload command
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(cmd.getName().equals("tower"))) {
            return false;
        }
        if (!(sender instanceof Player)) {
            return false;
        }
        if (args.length != 2) {
            sender.sendMessage("Usage: /tower <material> <height>");
            return false;
        }

        Material material = Material.matchMaterial(args[0]);
        
        if(material == null){
            sender.sendMessage(ChatColor.RED + args[0] + ChatColor.DARK_RED + " is not a valid material!");
            return false;
        }
        
        int height;
        
        try{
            height = Integer.parseInt(args[1]);
        } catch (NumberFormatException nfe){
            sender.sendMessage(ChatColor.RED + args[1] + ChatColor.DARK_RED + " is not a valid number!");
            return false;
        }

        int x = ((Player) sender).getLocation().getBlockX();
        int y = ((Player) sender).getLocation().getBlockY();
        int z = ((Player) sender).getLocation().getBlockZ();

        int[] tower = {
            x + 1, y, z,
            x - 1, y, z,
            x, y, z + 1,
            x, y, z - 1,
            x + 1, y, z + 1,
            x - 1, y, z - 1,
            x + 1, y, z - 1,
            x - 1, y, z + 1
        };

        Block block;
        World world = ((Player) sender).getWorld();

        int layer = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < tower.length; j += 3) {
                block = world.getBlockAt(tower[j], tower[j + 1] + layer, tower[j + 2]);
                block.setType(material);
            }
            layer++;
        }

        sender.sendMessage(ChatColor.GREEN + "You made a tower of " + material + " that is " + height + " blocks high.");
        return true;
    }
}
