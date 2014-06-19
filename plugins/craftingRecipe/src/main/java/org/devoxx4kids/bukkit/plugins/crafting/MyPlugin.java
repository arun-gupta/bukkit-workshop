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
        ShapedRecipe recipe = new ShapedRecipe(new ItemStack(Material.COOKIE, 1))
                .shape("wsw", "wmw", "wcw")
                .setIngredient('w', Material.WHEAT)
                .setIngredient('s', Material.SUGAR)
                .setIngredient('c', Material.COCOA)
                .setIngredient('m', Material.MILK_BUCKET);
        getServer().addRecipe(recipe);
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }
}
