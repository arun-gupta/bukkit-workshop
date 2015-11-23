package org.devoxx4kids.spigot.plugins.tntmagnet;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author arungupta
 */
public class TNTTask extends BukkitRunnable {

    private final JavaPlugin plugin;
    private int counter;
    Player p;
    Block b;
    private final float radius = 50.0F;

    public TNTTask(JavaPlugin plugin, int counter, Player p, Block b) {
        System.out.println("MyTask.ctor: " + counter);
        this.plugin = plugin;
        this.p = p;
        this.b = b;
        if (counter < 1) {
            throw new IllegalArgumentException("counter must be greater than 1");
        } else {
            this.counter = counter;
        }
    }

    @Override
    public void run() {
        System.out.println("run: " + counter);
        if (counter > 0) {
            p.sendMessage(ChatColor.GOLD + "Detonating in " + ChatColor.DARK_PURPLE + counter-- + ChatColor.GOLD + " seconds!");
        } else {
            p.sendMessage(ChatColor.GOLD + "Your " + ChatColor.RED + "TNT Magnet "
                    + ChatColor.GOLD + "has detonated!");

            b.setType(Material.AIR);

            TNTPrimed tnt = (TNTPrimed) b.getWorld().spawnEntity(new Location(b.getWorld(), b.getX() + 0.5, b.getY() + 0.5, b.getZ()), EntityType.PRIMED_TNT);

            tnt.setFuseTicks(0);

            this.cancel();
        }

        if (counter == 1) {
            Entity bat = b.getWorld().spawnEntity(new Location(b.getWorld(), b.getX() + 0.5, b.getY() + 0.5, b.getZ() + 0.5), EntityType.BAT);

            List<Entity> entitylist = ((LivingEntity) bat).getNearbyEntities(radius, radius, radius);

            for (int i = 0; i < entitylist.toArray().length; i++) {

                if (entitylist.get(i) instanceof Player) {
                    continue;
                }

                entitylist.get(i).teleport(new Location(b.getWorld(), b.getX() + 0.5, b.getY() + 0.5, b.getZ() + 0.5));
            }

            bat.remove();
        }
    }
}
