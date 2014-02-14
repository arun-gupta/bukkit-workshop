/**
 * Copyright: Aditya Gupta
 */
package org.devoxx4kids.bukkit.plugins.creeper;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @author Aditya Gupta
 */
public class CreeperListener implements Listener{
    
        @EventHandler
        public void makeCreeper(BlockPlaceEvent event){
            Player player = event.getPlayer();
            World world = player.getWorld();
            Block block = event.getBlockPlaced();
            if(block.getType() == Material.LEAVES || block.getType() == Material.LEAVES_2){
                if(world.getBlockAt(block.getX(), block.getY() - 1, block.getZ()).getType() == Material.TNT){
                    Block block2 = world.getBlockAt(block.getX(), block.getY() - 1, block.getZ());
                    block.setType(Material.AIR);
                    block2.setType(Material.AIR);
                    Location location = block2.getLocation();
                    world.spawnEntity(location, EntityType.CREEPER);
                }
            }
        }
}
