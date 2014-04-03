package org.devoxx4kids.bukkit.plugins.teleport;

import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import sun.print.resources.serviceui;

public class MyPlugin extends JavaPlugin {
    // This code is called only once after the server starts
    @Override
    public void onLoad() {
        getLogger().log(Level.INFO, "{0}.onLOad()", this.getClass().getName());
        saveDefaultConfig();
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
        if (cmd.getName().equals("tpall")) {
            if (!(sender instanceof Player)) {
                return false;
            }

            Player[] player = sender.getServer().getOnlinePlayers();
            Location location = sender.getServer().getPlayer(sender.getName()).getLocation();
            
            for(int i = 0; i < player.length; i++){
                player[i].teleport(location);
            }
            
            return true;
        }else{
            return false;
        }
    }
}
