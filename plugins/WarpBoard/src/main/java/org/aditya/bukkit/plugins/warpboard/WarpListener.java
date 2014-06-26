/**
 * Copyright: Aditya Gupta
 */
package org.aditya.bukkit.plugins.warpboard;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Aditya Gupta
 */
class WarpListener implements Listener {

    @EventHandler
    public void checkForInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (!(e.getInventory().getTitle().contains(ChatColor.DARK_PURPLE + "Warp Board"))) {
            return;
        }
        e.setCancelled(true);

        if (e.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
            p.sendMessage(ChatColor.GOLD + "Warping to " + ChatColor.GREEN + "Emerald Land");
            p.teleport(new Location(p.getWorld(), 0.5, 100, 0.5));
        } else if (e.getCurrentItem().getType() == Material.DIAMOND_BLOCK) {
            p.sendMessage(ChatColor.GOLD + "Warping to " + ChatColor.BLUE + "Diamond Land");
            p.teleport(new Location(p.getWorld(), 100.5, 100, 0.5));
        } else if (e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
            p.sendMessage(ChatColor.GOLD + "Warping to " + ChatColor.RED + "Redstone Land");
            p.teleport(new Location(p.getWorld(), 200.5, 100, 0.5));
        }
    }

    @EventHandler
    public void openCustomInventory(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (!e.getItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Warp Board")) {
            return;
        }
        e.getPlayer().performCommand("warpboard");
    }

    @EventHandler
    public void giveWarpBoardCompass(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        System.out.println("3.141592653589793238" + p.getWorld());

        if (!"".equals(p.getWorld().toString())) {
            return;
        }

        ItemStack is = new ItemStack(Material.COMPASS);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Warp Board");
        is.setItemMeta(im);
        p.getInventory().setItem(0, is);
    }
}
