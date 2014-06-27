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
        enchantments.put("power", Enchantment.ARROW_DAMAGE);
        enchantments.put("flame", Enchantment.ARROW_FIRE);
        enchantments.put("infinity", Enchantment.ARROW_INFINITE);
        enchantments.put("punch", Enchantment.ARROW_KNOCKBACK);
        enchantments.put("sharpness", Enchantment.DAMAGE_ALL);
        enchantments.put("bane_of_arthropods", Enchantment.DAMAGE_ARTHROPODS);
        enchantments.put("smite", Enchantment.DAMAGE_UNDEAD);
        enchantments.put("efficiency", Enchantment.DIG_SPEED);
        enchantments.put("unbreaking", Enchantment.DURABILITY);
        enchantments.put("fire_aspect", Enchantment.FIRE_ASPECT);
        enchantments.put("knockback", Enchantment.KNOCKBACK);
        enchantments.put("fortune", Enchantment.LOOT_BONUS_BLOCKS);
        enchantments.put("looting", Enchantment.LOOT_BONUS_MOBS);
        enchantments.put("luck_of_the_sea", Enchantment.LUCK);
        enchantments.put("lure", Enchantment.LURE);
        enchantments.put("respiration", Enchantment.OXYGEN);
        enchantments.put("protection", Enchantment.PROTECTION_ENVIRONMENTAL);
        enchantments.put("blast_protection", Enchantment.PROTECTION_EXPLOSIONS);
        enchantments.put("feather_falling", Enchantment.PROTECTION_FALL);
        enchantments.put("fire_protection", Enchantment.PROTECTION_FIRE);
        enchantments.put("projectile_protection", Enchantment.PROTECTION_PROJECTILE);
        enchantments.put("silk_touch", Enchantment.SILK_TOUCH);
        enchantments.put("thorns", Enchantment.THORNS);
        enchantments.put("aqua_affinity", Enchantment.WATER_WORKER);
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equals("highenchant")) {
            return false;
        }
        if (!(sender instanceof Player)) {
            return false;
        }
        
        if (args.length != 2) {
            ((Player) sender).sendMessage(ChatColor.DARK_RED + cmd.getUsage());
            return true;
        }
        
        if (!enchantments.containsKey(args[0])) {
            return false;
        }
        
        int level = Integer.parseInt(args[1]);
        
        ItemStack itemStack = ((Player) sender).getItemInHand();
        itemStack.addUnsafeEnchantment(enchantments.get(args[0]), level);
        
        ((Player) sender).sendMessage(ChatColor.GOLD + "You have enchanted your item with " + ChatColor.GREEN 
                + args[0] + ChatColor.GOLD + " at level " + ChatColor.GREEN + level + ChatColor.GOLD + ".");
        
        return true;
    }

}
