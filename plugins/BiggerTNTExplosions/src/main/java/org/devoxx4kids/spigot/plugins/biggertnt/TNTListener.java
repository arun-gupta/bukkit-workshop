package org.devoxx4kids.spigot.plugins.biggertnt;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

/**
 * @author Aditya Gupta
 */
public class TNTListener implements Listener {

    @EventHandler
    public void makeExplosion(EntityExplodeEvent event) {
        EntityType entitytype = event.getEntityType();
        World world = event.getEntity().getWorld();
        Location location = event.getEntity().getLocation();

        float power = 20.0F;
        if (entitytype == EntityType.PRIMED_TNT) {
            event.setCancelled(true);
            world.createExplosion(location, power);
        }
    }
}
