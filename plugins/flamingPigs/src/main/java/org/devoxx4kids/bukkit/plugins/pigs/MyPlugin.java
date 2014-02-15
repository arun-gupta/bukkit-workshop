package org.devoxx4kids.bukkit.plugins.pigs;

import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
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
        if (cmd.getName().equals("flamingpigs")) {
            if (!(sender instanceof Player)) {
                return false;
            }

            Location location = ((Player) sender).getLocation();

            if (args.length != 1) {
                sender.sendMessage("Usage: /flamingpigs <number of pigs to spawn>");
            }

            int numberOfPigs = Integer.parseInt(args[0]);

            for(int i = 0; i < numberOfPigs; i++){
                Entity pig = Bukkit.getWorld("World").spawnEntity(location, EntityType.PIG);
                pig.setFireTicks(600);
                if(pig.isDead()){
                    EntityDamageEvent entitydamageevent = pig.getLastDamageCause();
                    entitydamageevent.setCancelled(true);
                    pig.setFallDistance(100);
                }
            }
            
            return true;
        }else{
            return false;
        }
    }
}
