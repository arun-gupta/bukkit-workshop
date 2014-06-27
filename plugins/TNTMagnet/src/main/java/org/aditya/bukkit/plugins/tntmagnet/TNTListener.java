    /**
 * Copyright: Aditya Gupta
 */
package org.aditya.bukkit.plugins.tntmagnet;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @author Aditya Gupta
 */
class TNTListener implements Listener {
    
    private final MyPlugin plugin;
    
    public TNTListener(MyPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void explodeTNT(BlockPlaceEvent e) throws InterruptedException {
        Block b = e.getBlock();
        
        if (b.getType() != Material.TNT) {
            return;
        }
        
        Player p = e.getPlayer();
        
        p.sendMessage(ChatColor.GOLD + "You have placed a " + 
            ChatColor.RED + "TNT Magnet" + ChatColor.GOLD + "! It will detonate in " + 
            ChatColor.DARK_PURPLE + "5 " + ChatColor.GOLD + "seconds.");
        
        new TNTTask(this.plugin, 4, p, b).runTaskTimer(this.plugin, 20, 20);
    }
}
