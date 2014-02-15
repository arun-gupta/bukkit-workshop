/**
 * Copyright: Aditya Gupta
 */
package org.devoxx4kids.bukkit.plugins.lightning;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * @author Aditya Gupta
 */
public class LightningListener implements Listener{
    
    @EventHandler
    public void lightningByFire(EntityDeathEvent event) throws InterruptedException{
            Entity entity = event.getEntity();
            Location location = entity.getLocation();
            World world = entity.getWorld();
            EntityDamageEvent entitydamageevent = entity.getLastDamageCause();
            
            if(entitydamageevent.getCause() == DamageCause.LAVA || entitydamageevent.getCause() == DamageCause.FIRE || entitydamageevent.getCause() == DamageCause.FIRE_TICK){
                world.strikeLightningEffect(location);
            }
    }
}
