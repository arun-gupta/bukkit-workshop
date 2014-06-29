package org.devoxx4kids.bukkit.plugins.flamingpigs;

import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {

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
        if (!(cmd.getName().equals("flamingpigs"))) {
            return false;
        }
        if (!(sender instanceof Player)) {
            return false;
        }

        Location location = ((Player) sender).getLocation();

        if (args.length != 1) {
            sender.sendMessage(ChatColor.DARK_RED + cmd.getUsage());
            return false;
        }

        int numberOfPigs;
        
        try{
            numberOfPigs = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe){
            sender.sendMessage(ChatColor.RED + args[1] + ChatColor.DARK_RED + " is not a valid number!");
            return false;
        }

        for (int i = 0; i < numberOfPigs; i++) {
            Entity pig = ((Player) sender).getWorld().spawnEntity(location, EntityType.PIG);
            pig.setFireTicks(6000);
        }
        return true;
    }
}
