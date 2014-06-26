package org.devoxx4kids.bukkit.plugins.enchantments;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    
    Map<String, Enchantment> enchantments = new HashMap<>();

    // This code is called after the server starts and after the /reload command

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
        enchantments.put("arrow_fire", Enchantment.ARROW_FIRE);
        enchantments.put("arrow_infinite", Enchantment.ARROW_INFINITE);
        enchantments.put("knowckback", Enchantment.KNOCKBACK);
        enchantments.put("luck", Enchantment.LUCK);
        enchantments.put("protection_explosions", Enchantment.PROTECTION_EXPLOSIONS);
        enchantments.put("durability", Enchantment.DURABILITY);
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equals("enchantment")) {
            return false;
        }
        if (!(sender instanceof Player)) {
            return false;
        }
        
        if (args.length != 2) {
            sender.sendMessage(ChatColor.DARK_RED + cmd.getUsage());
        }
        
        if (!enchantments.containsKey(args[0])) {
            return false;
        }
        
        int level = Integer.parseInt(args[1]);
        
        ItemStack itemStack = ((Player) sender).getItemInHand();
        itemStack.addUnsafeEnchantment(enchantments.get(args[0]), level);
        
        return true;
    }

}
