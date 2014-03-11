package org.devoxx4kids.bukkit.plugins.zombiePlague;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rmichela on 3/11/14.
 */
public class ZombiePlugin extends JavaPlugin implements Listener {
    private final double ZOMBIE_POISON_DAMAGE = 2;
    private final int SECONDS_BETWEEN_POISON_DAMAGE = 10;
    private final int TICKS_PER_SECOND = 20;

    private Set<String> bittenPlayers = new HashSet<String>();

    @Override
    public void onEnable() {
        // Register plugin events
        getServer().getPluginManager().registerEvents(this, this);
        // Schedule a reoccurring action to damage bitten players
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new PlayerDamageTask(), SECONDS_BETWEEN_POISON_DAMAGE * TICKS_PER_SECOND, SECONDS_BETWEEN_POISON_DAMAGE * TICKS_PER_SECOND);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        // If a Player is hit by a Zombie
        if (event.getEntity().getType() == EntityType.PLAYER
                && event.getDamager().getType() == EntityType.ZOMBIE
                && event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            // Add the player's name to the list of bitten players
            bittenPlayers.add(((Player)event.getEntity()).getName());
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // If a Player right clicks on a Cake block
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.CAKE_BLOCK) {
            // Remove the player's name from the list of bitten players
            bittenPlayers.remove(event.getPlayer().getName());
            event.getPlayer().sendMessage("The cake cures your poison");
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        // If the dying player was previously bitten
        if (bittenPlayers.contains(player.getName())) {
            // Remove the player's name from the list of bitten players and spawn a zombie where they died
            bittenPlayers.remove(player.getName());
            player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
        }
    }

    private class PlayerDamageTask implements Runnable {
        @Override
        public void run() {
            // For each bitten player, damage and slow the player, then send them a message.
            for(String playerName : bittenPlayers) {
                // Don't damage players in creative mode
                Player player = getServer().getPlayer(playerName);
                    if (player.getGameMode() != GameMode.CREATIVE) {
                    player.damage(ZOMBIE_POISON_DAMAGE);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (SECONDS_BETWEEN_POISON_DAMAGE + 1) * TICKS_PER_SECOND, 1));
                    player.sendMessage("The zombie poison boils your blood.");
                }
            }
        }
    }
}
