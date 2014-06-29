package org.devoxx4kids.bukkit.plugins.zombiebones;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Aditya Gupta
 */
class BoneListener implements Listener {

    public BoneListener() {
    }

    @EventHandler
    public void dropBones(EntityDeathEvent e) {
        if(e.getEntityType() != EntityType.ZOMBIE){
            return;
        }
        Location l = e.getEntity().getLocation();
        World w = e.getEntity().getWorld();
        
        Random r = new Random();
        int i = r.nextInt(3);
        
        w.dropItem(l, new ItemStack(Material.BONE, i));
    }
}
