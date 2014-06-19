package org.devoxx4kids.bukkit.plugins.smeltingrecipe;

import java.util.logging.Level;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    // This code is called after the server starts and after the /reload command
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
        FurnaceRecipe recipe = new FurnaceRecipe(
                new ItemStack(Material.COAL_ORE, 1),
                Material.COAL
        );
        getServer().addRecipe(recipe);
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }
}
