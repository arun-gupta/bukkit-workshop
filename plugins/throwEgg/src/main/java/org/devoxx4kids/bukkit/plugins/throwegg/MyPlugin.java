package org.devoxx4kids.bukkit.plugins.throwegg;

import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    // This code is called only once after the server starts
    
    public static EntityType entityType;
    public static int numberOfEntities;

    @Override
    public void onLoad() {
        getLogger().log(Level.INFO, "{0}.onLoad()", this.getClass().getName());
    }

    // This code is called after the server starts and after the /reload command
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
        getServer().getPluginManager().registerEvents(new EggListener(), this);
        
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(cmd.getName().equals("eggconfig"))) {
            return false;
        }
        if (args.length != 2) {
            sender.sendMessage(ChatColor.DARK_RED + cmd.getUsage());
            return false;
        }
        
        entityType = EntityType.valueOf(args[0].toString().toUpperCase());
        numberOfEntities = Integer.parseInt(args[1].toString());
        
        sender.sendMessage(ChatColor.GREEN + "Eggs will now hatch " + numberOfEntities + " " + entityType + "(s).");
        
        return true;
    }
}