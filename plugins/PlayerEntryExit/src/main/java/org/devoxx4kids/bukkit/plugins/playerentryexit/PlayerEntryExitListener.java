/**
 * Copyright: Aditya Gupta
 */
package org.devoxx4kids.bukkit.plugins.playerentryexit;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Aditya Gupta
 */
class PlayerEntryExitListener implements Listener {
    
    static Logger logger;

    public PlayerEntryExitListener(Logger logger2) {
        logger = logger2;
    }
    
    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String playername = player.getName();
        logger.log(Level.INFO, "{0} has joined the server.", playername);
    }
    
    @EventHandler
    public void playerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String playername = player.getName();
        logger.log(Level.INFO, "{0} has left the server.", playername);
    }

}
