package org.devoxx4kids.bukkit.plugins.throwegg;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

/**
 * @author Aditya Gupta
 */
public class EggListener implements Listener {

    @EventHandler
    public void hatchEntity(CreatureSpawnEvent event) {
        Entity entity = event.getEntity();
        Location location = entity.getLocation();
        World world = entity.getWorld();

        if (event.getSpawnReason() == SpawnReason.EGG && event.getEntityType() == EntityType.CHICKEN)
        {
            entity.remove();
            for (int i = 0 ; i < MyPlugin.numberOfEntities ; i++) {
                world.spawnEntity(location, MyPlugin.entityType);
            }
        }
    }
}