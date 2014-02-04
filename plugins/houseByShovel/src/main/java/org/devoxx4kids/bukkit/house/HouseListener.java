/**
 * Copyright: Aditya Gupta
 */
package org.devoxx4kids.bukkit.house;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

/**
 * @author Aditya Gupta
 */
public class HouseListener implements Listener {

    @EventHandler
    public void makeHouse(BlockDamageEvent event) {
        event.setCancelled(true);

        Player player = event.getPlayer();
        World world = player.getWorld();
        Block block = event.getBlock();
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        int x1 = block.getX();
        int y1 = block.getY();
        int z1 = block.getZ();

        if (player.getItemInHand().getType() == Material.GOLD_SPADE) {

            Material houseMaterial = Material.WOOD;

            //The bottom part of the walls starts here.

            int[] wall1_and_2 = {
                x, y, z,
                x, y + 1, z,
                x, y + 2, z,
                x + 1, y + 1, z,
                x - 1, y + 1, z,
                x + 1, y + 2, z,
                x - 1, y + 2, z,
                x + 2, y + 1, z,
                x - 2, y + 1, z,
                x + 2, y + 2, z,
                x - 2, y + 2, z
            };

            for (int i = 0; i < wall1_and_2.length; i = i + 3) {
                block = world.getBlockAt(wall1_and_2[i], wall1_and_2[i + 1], wall1_and_2[i + 2]);
                block.setType(houseMaterial);
            }

            for (int i = 3; i < wall1_and_2.length; i = i + 3) {
                block = world.getBlockAt(wall1_and_2[i], wall1_and_2[i + 1], wall1_and_2[i + 2] + 6);
                block.setType(houseMaterial);
            }

            int[] wall3_and_4 = {
                x, y + 1, z,
                x, y + 2, z,
                x, y + 1, z + 1,
                x, y + 1, z - 1,
                x, y + 2, z + 1,
                x, y + 2, z - 1,
                x, y + 1, z + 2,
                x, y + 1, z - 2,
                x, y + 2, z + 2,
                x, y + 2, z - 2
            };

            for (int i = 0; i < wall3_and_4.length; i = i + 3) {
                block = world.getBlockAt(wall3_and_4[i] + 3, wall3_and_4[i + 1], wall3_and_4[i + 2] + 3);
                block.setType(houseMaterial);
                block = world.getBlockAt(wall3_and_4[i] - 3, wall3_and_4[i + 1], wall3_and_4[i + 2] + 3);
                block.setType(houseMaterial);
            }

            //The top part of the walls starts here.

            for (int i = 0; i < wall1_and_2.length; i = i + 3) {
                block = world.getBlockAt(wall1_and_2[i], wall1_and_2[i + 1] + 2, wall1_and_2[i + 2]);
                block.setType(houseMaterial);
            }

            for (int i = 3; i < wall1_and_2.length; i = i + 3) {
                block = world.getBlockAt(wall1_and_2[i], wall1_and_2[i + 1] + 2, wall1_and_2[i + 2] + 6);
                block.setType(houseMaterial);
            }

            for (int i = 0; i < wall3_and_4.length; i = i + 3) {
                block = world.getBlockAt(wall3_and_4[i] + 3, wall3_and_4[i + 1] + 2, wall3_and_4[i + 2] + 3);
                block.setType(houseMaterial);
                block = world.getBlockAt(wall3_and_4[i] - 3, wall3_and_4[i + 1] + 2, wall3_and_4[i + 2] + 3);
                block.setType(houseMaterial);
            }

            int[] roof = {
                x, y, z,
                x + 1, y, z,
                x + 2, y, z,
                x - 1, y, z,
                x - 2, y, z,
                x, y, z + 1,
                x, y, z + 2,
                x, y, z - 1,
                x, y, z - 2,
                x + 1, y, z + 1,
                x + 1, y, z + 2,
                x + 1, y, z - 1,
                x + 1, y, z - 2,
                x + 2, y, z + 1,
                x + 2, y, z + 2,
                x + 2, y, z - 1,
                x + 2, y, z - 2,
                x - 2, y, z + 1,
                x - 2, y, z + 2,
                x - 2, y, z - 1,
                x - 2, y, z - 2,
                x - 1, y, z + 1,
                x - 1, y, z - 1,
                x - 1, y, z + 2,
                x - 1, y, z - 2,};

            for (int i = 0; i < roof.length; i = i + 3) {
                block = world.getBlockAt(roof[i], roof[i + 1], roof[i + 2] + 3);
                block.setType(houseMaterial);
                block = world.getBlockAt(roof[i], roof[i + 1] + 5, roof[i + 2] + 3);
                block.setType(houseMaterial);
            }

            int[] glowstone = {
                x + 2, y, z - 2,
                x - 2, y, z + 2,
                x - 2, y, z - 2,
                x + 2, y, z + 2
            };

            for (int i = 0; i < glowstone.length; i = i + 3) {
                block = world.getBlockAt(glowstone[i], glowstone[i + 1] + 4, glowstone[i + 2] + 3);
                block.setType(Material.GLOWSTONE);
            }

            block = world.getBlockAt(x, y + 1, z);
            block.setType(Material.FENCE_GATE);
            block = world.getBlockAt(x, y + 2, z);
            block.setType(Material.AIR);
            block = world.getBlockAt(x + 2, y + 1, z + 5);
            block.setType(Material.CHEST);
            block = world.getBlockAt(x + 1, y + 1, z + 5);
            block.setType(Material.CHEST);
            block = world.getBlockAt(x, y + 1, z + 5);
            block.setType(Material.FURNACE);
            block = world.getBlockAt(x - 1, y + 1, z + 5);
            block.setType(Material.BREWING_STAND);
            block = world.getBlockAt(x - 2, y + 1, z + 5);
            block.setType(Material.ENCHANTMENT_TABLE);
        }
    }
}
