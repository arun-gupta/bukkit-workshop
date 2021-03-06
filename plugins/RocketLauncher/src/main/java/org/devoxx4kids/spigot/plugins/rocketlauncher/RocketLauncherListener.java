/**
 * Copyright: Aditya Gupta
 */
package org.devoxx4kids.spigot.plugins.rocketlauncher;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Aditya Gupta
 */
class RocketLauncherListener implements Listener {

    Map<String, Boolean> safety = new HashMap<>();
    
    @EventHandler
    public void checkForNewPlayer(PlayerJoinEvent e){
        if (!(safety.containsKey(e.getPlayer().getName()))) {
            safety.put(e.getPlayer().getName(), true);
        }
    }

    @EventHandler
    public void fireGun(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
            
            if(e.getPlayer().getItemInHand().getType() != Material.DIAMOND_BARDING){
                return;
            }
            
            if(safety.get(p.getName())){
                safety.put(p.getName(), false);
                p.sendMessage(ChatColor.AQUA + "Safety is now OFF.");
            } else {
                safety.put(p.getName(), true);
                p.sendMessage(ChatColor.AQUA + "Safety is now ON.");
            }
            
            return;
        }
        
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if(p.getItemInHand().getType() != Material.DIAMOND_BARDING || safety.get(p.getName()) == true){
            return;
        }
            
        Fireball f = e.getPlayer().launchProjectile(Fireball.class);
        f.setIsIncendiary(false);
    }

    @EventHandler
    public void makeBigExplosion(EntityExplodeEvent event) {
        Entity e = event.getEntity();

        if (!(e instanceof Fireball)) {
            return;
        }
        Fireball f = (Fireball) e;
        if (f.isIncendiary()) {
            return;
        }
        World w = f.getWorld();
        w.createExplosion(f.getLocation(), 10.0F);
        event.setCancelled(true);
    }
}
