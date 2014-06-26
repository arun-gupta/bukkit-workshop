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
        
        Thread.sleep(5000);
        
        p.sendMessage(ChatColor.GOLD + "Your " + ChatColor.RED + "TNT Magnet " + 
                ChatColor.GOLD + "has detonated!");
        
        Entity bat = p.getWorld().spawnEntity(new Location(p.getWorld(), b.getX() + 0.5
                , b.getY() + 0.5, b.getZ() + 0.5), EntityType.BAT);
        
        List<Entity> entitylist = ((LivingEntity) bat).getNearbyEntities(4.0, 4.0, 4.0);
        
        bat.remove();
        
        for(int i = 0 ; i < entitylist.toArray().length ; i++){
            entitylist.get(i).teleport(new Location(p.getWorld(), b.getX() + 0.5
                , b.getY() + 0.5, b.getZ() + 0.5));
        }
        
        b.setType(Material.AIR);
        
        TNTPrimed tnt = (TNTPrimed) p.getWorld().spawnEntity(new Location(p.getWorld(), b.getX() + 0.5
                , b.getY() + 0.5, b.getZ()), EntityType.PRIMED_TNT);
        
        tnt.setFuseTicks(0);
    }
}
