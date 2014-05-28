/**
 * Copyright: Aditya Gupta
 */
package org.devoxx4kids.bukkit.plugins.newblock;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**
 * @author Aditya Gupta
 */
class ElevatorListener implements Listener {
    
    @EventHandler
    public void checkForBlock(BlockPlaceEvent event){
        Block block = event.getBlock();
        
        if(block.getType() != Material.REDSTONE_BLOCK){
            return;
        }
        
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.GOLD + "You have placed an elevator. Use '/elevator help' to find out more.");
    }
    
}
