package org.devoxx4kids.bukkit.yourname.yourpluginname;

import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    // This code is called only once after the server starts
    @Override
    public void onLoad() {
        saveDefaultConfig();
        getLogger().info("MyPlugin.key1 = " + getConfig().getString("key1"));
        getLogger().info("MyPlugin.key2 = " + getConfig().getString("key2"));
        getLogger().info("MyPlugin.key3 = " + getConfig().getString("key3"));
    }

    // This code is called after the server starts and after the /reload command
    @Override
    public void onEnable() {
        getLogger().info("MyPlugin.onEnable()");
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().info("MyPlugin.onDisable()");
    }
}
