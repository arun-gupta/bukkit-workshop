/**
 * Copyright: Aditya Gupta
 */
package org.devoxx4kids.bukkit.plugins.sponge;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @author Aditya Gupta
 */
public class SpongeListener implements Listener {

    @EventHandler
    public void absorbWater(BlockPlaceEvent event) {
        Block eventblock = event.getBlock();
        World world = eventblock.getWorld();
        if (eventblock.getType() == Material.SPONGE) {

            int x = eventblock.getX();
            int y = eventblock.getY();
            int z = eventblock.getZ();

            int[] areaAroundSponge = {
                x, y + 1, z,
                x, y - 1, z,
                x + 1, y + 1, z,
                x - 1, y + 1, z,
                x, y + 1, z + 1,
                x, y + 1, z - 1,
                x + 1, y + 1, z + 1,
                x - 1, y + 1, z - 1,
                x + 1, y + 1, z - 1,
                x - 1, y + 1, z + 1,
                x + 1, y, z,
                x - 1, y, z,
                x, y, z + 1,
                x, y, z - 1,
                x + 1, y, z + 1,
                x - 1, y, z - 1,
                x + 1, y, z - 1,
                x - 1, y, z + 1,
                x + 1, y - 1, z,
                x - 1, y - 1, z,
                x, y - 1, z + 1,
                x, y - 1, z - 1,
                x + 1, y - 1, z + 1,
                x - 1, y - 1, z - 1,
                x + 1, y - 1, z - 1,
                x - 1, y - 1, z + 1
            };

            for (int i = 0; i < areaAroundSponge.length; i = i + 3) {
                Block block = world.getBlockAt(areaAroundSponge[i], areaAroundSponge[i + 1], areaAroundSponge[i + 2]);
                if (block.getType() == Material.WATER) {
                    block.setType(Material.OBSIDIAN);
                }
            }
        }
    }
}
