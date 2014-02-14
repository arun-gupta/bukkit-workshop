/**
 * Copyright: Aditya Gupta
 */
package org.devoxx4kids.bukkit.plugins.bone;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Aditya Gupta
 */
public class BoneListener implements Listener {

    @EventHandler
    public void dropBone(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ZOMBIE) {
            Location location = entity.getLocation();
            World world = entity.getWorld();

            Random seed = new Random();
            int randomNumber = seed.nextInt(3);

            world.dropItem(location, new ItemStack(Material.BONE, randomNumber));
        }
    }
}
