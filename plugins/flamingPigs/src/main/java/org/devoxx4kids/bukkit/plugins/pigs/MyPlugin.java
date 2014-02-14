package org.devoxx4kids.bukkit.plugins.pigs;

import java.util.logging.Level;
import org.bukkit.Bukkit;
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
        if (cmd.getName().equals("pigsOnFire")) {
            if (!(sender instanceof Player)) {
                return false;
            }

            Location location = ((Player) sender).getLocation();

            if (args.length != 1) {
                sender.sendMessage("Usage: /pigsOnFire howMany");
            }

            int number = Integer.parseInt(args[0]);

            for (int i = 0; i < number; i++) {
                Entity pig = Bukkit
                        .getWorld("World")
                        .spawnEntity(
                                location.add(1.0 * i, 0, 0), 
                                EntityType.PIG);
                
                // TODO: Need to figure out how to make the pigs die
                pig.setFireTicks(2 * (i+1));
                pig.setTicksLived(2 * (i+1));
            }
            
            // doSomething
            return true;
        }
        //If this has happened the function will return true. 
        // If this hasn't happened the a value of false will be returned.
        return false;
    }
}
