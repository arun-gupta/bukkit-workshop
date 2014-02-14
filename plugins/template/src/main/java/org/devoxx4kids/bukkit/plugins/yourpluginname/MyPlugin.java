package org.devoxx4kids.bukkit.plugins.yourpluginname;

import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    // This code is called only once after the server starts
    @Override
    public void onLoad() {
        getLogger().log(Level.INFO, "{0}.onLOad()", this.getClass().getName());
        saveDefaultConfig();
        getLogger().info("MyPlugin.key1 = " + getConfig().getString("key1"));
        getLogger().info("MyPlugin.key2 = " + getConfig().getString("key2"));
        getLogger().info("MyPlugin.key3 = " + getConfig().getString("key3"));
    }

    // This code is called after the server starts and after the /reload command
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }
}
