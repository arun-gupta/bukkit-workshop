package org.aditya.bukkit.plugins.warpboard;

import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {

    public Inventory inv;

    // This code is called after the server starts and after the /reload command
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
        this.getServer().getPluginManager().registerEvents(new WarpListener(), this);
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(cmd.getName().equals("warpboard"))) {
            return false;
        }
        if (!(sender instanceof Player)) {
            return false;
        }

        openGUI(((Player) sender));

        return true;
    }

    public void openGUI(Player p) {
        ItemStack isEmerald = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta imEmerald = isEmerald.getItemMeta();
        imEmerald.setDisplayName(ChatColor.GOLD + "Warp to " + ChatColor.GREEN + "Emerald Land");
        isEmerald.setItemMeta(imEmerald);

        ItemStack isDiamond = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta imDiamond = isDiamond.getItemMeta();
        imDiamond.setDisplayName(ChatColor.GOLD + "Warp to " + ChatColor.BLUE + "Diamond Land");
        isDiamond.setItemMeta(imDiamond);

        ItemStack isRedstone = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta imRedstone = isRedstone.getItemMeta();
        imRedstone.setDisplayName(ChatColor.GOLD + "Warp to " + ChatColor.RED + "Redstone Land");
        isRedstone.setItemMeta(imRedstone);

        inv = Bukkit.createInventory(p, 27, ChatColor.DARK_PURPLE + "Warp Board");
        inv.setItem(12, isEmerald);
        inv.setItem(13, isDiamond);
        inv.setItem(14, isRedstone);
        p.openInventory(inv);
    }
}
