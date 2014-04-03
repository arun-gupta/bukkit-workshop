package org.devoxx4kids.bukkit.plugins.crafting;

import java.util.logging.Level;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    // This code is called only once after the server starts
    @Override
    public void onLoad() {
        getLogger().log(Level.INFO, "{0}.onLoad()", this.getClass().getName());
    }

    // This code is called after the server starts and after the /reload command
    @Override
    public void onEnable() {
            getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
        ShapedRecipe recipe = new ShapedRecipe(new ItemStack(Material.POTATO_ITEM, 1))
                .shape("ded", "lnl", "ded")
                .setIngredient('d', Material.DIAMOND)
                .setIngredient('e', Material.EMERALD)
                .setIngredient('l', Material.LAPIS_BLOCK)
                .setIngredient('n', Material.NETHER_STAR);
        getServer().addRecipe(recipe);
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }
}
