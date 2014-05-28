/**
 * Copyright: Aditya Gupta
 */
package org.aditya.bukkit.plugins.fishingdrops;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Aditya Gupta
 */
class FishingListener implements Listener {

    public FishingListener() {
    }

    @EventHandler
    public void changeFishingDrops(PlayerFishEvent e) {
        if (e.getState() != PlayerFishEvent.State.CAUGHT_FISH) {
            return;
        }
        if(!(e.getCaught() instanceof Item)){
            return;
        }
        
        Item i = (Item) e.getCaught();
        ItemStack is = i.getItemStack();
        is.setType(Material.DIAMOND);
        i.setItemStack(is);
    }
}
