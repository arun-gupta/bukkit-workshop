/**
 * Copyright: Aditya Gupta
 */
package org.devoxx4kids.bukkit.plugins.spongewater;

import java.util.logging.Level;
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
        
        if (eventblock.getType() != Material.SPONGE) {
            return;
        }

        plugin.getLogger().info("Sponge place detected.");
        
        int radius = 1;

        int x = eventblock.getX();
        int y = eventblock.getY();
        int z = eventblock.getZ();

        int waterBlocksFound = 0;
        for (int xx = x - radius; xx <= x + radius; xx++) {
            for (int yy = y - radius; yy <= y + radius; yy++) {
                for (int zz = z - radius; zz <= z + radius; zz++) {
                    Block block = world.getBlockAt(xx, yy, zz);
                    if (block.getType() == Material.WATER || block.getType() == Material.STATIONARY_WATER) {
                        block.setType(Material.GLASS);
                        waterBlocksFound++;
                    }
                }
            }
        }
        plugin.getLogger().log(Level.INFO, "Replaced {0} water blocks with glass.", waterBlocksFound);
    }
}
