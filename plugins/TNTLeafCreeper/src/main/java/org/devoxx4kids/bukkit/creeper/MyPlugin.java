package org.devoxx4kids.bukkit.creeper;

import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    // This code is called only once after the server starts
    @Override
    public void onLoad() {
        getLogger().info("MyPlugin.onLoad()");
    }

    // This code is called after the server starts and after the /reload command
    @Override
    public void onEnable() {
        getLogger().info("MyPlugin.onEnable()");
        getServer().getPluginManager().registerEvents(new CreeperListener(), this);
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().info("MyPlugin.onDisable()");
    }
}
