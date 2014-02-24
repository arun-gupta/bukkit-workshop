package org.devoxx4kids.bukkit.plugins.teleport;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Level;

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
        if (cmd.getName().equals("teleportall")) {
            if (!(sender instanceof Player)) {
                return false;
            }

            Location location = ((Player) sender).getLocation();
            List<Player> players = location.getWorld().getPlayers();

            for(Player player : players) {
                player.teleport(location, PlayerTeleportEvent.TeleportCause.COMMAND);
            }
            
            return true;
        }else{
            return false;
        }
    }
}
