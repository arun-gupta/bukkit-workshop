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
import org.bukkit.plugin.Plugin;

/**
 * @author Aditya Gupta
 */
public class SpongeListener implements Listener {

    private Plugin plugin;

    public SpongeListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void absorbWater(BlockPlaceEvent event) {
        Block eventblock = event.getBlock();
        World world = eventblock.getWorld();
        if (eventblock.getType() == Material.SPONGE) {
            plugin.getLogger().info("Sponge place detected.");

            int x = eventblock.getX();
            int y = eventblock.getY();
            int z = eventblock.getZ();

            int waterBlocksFound = 0;
            for (int xx = x - 1; xx <= x + 1; xx++) {
                for (int yy = y - 1; yy <= y + 1; yy++) {
                    for (int zz = z - 1; zz <= z + 1; zz++) {
                        Block block = world.getBlockAt(xx, yy, zz);
                        if (block.getType() == Material.WATER || block.getType() == Material.STATIONARY_WATER) {
                            block.setType(Material.OBSIDIAN);
                            waterBlocksFound++;
                        }
                    }
                }
            }
            plugin.getLogger().info("Replaced " + waterBlocksFound + " water blocks with obsidian.");
        }
    }
}
