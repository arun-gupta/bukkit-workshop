package com.yourname.yourpluginname;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {
    // This code is called only once after the server starts
    @Override
    public void onLoad() {
        saveDefaultConfig();
        getLogger().info("key1 = " + getConfig().getString("key1"));
        getLogger().info("key2 = " + getConfig().getString("key2"));
        getLogger().info("key3 = " + getConfig().getString("key3"));
    }

    // This code is called after the server starts and after the /reload command
    @Override
    public void onEnable() {
        getLogger().info("Called PluginMain.onEnable()");
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().info("Called PluginMain.onDisable()");
    }
}
