package org.devoxx4kids.spigot.plugins.biggertnt;

import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;

public class BiggerTNT extends JavaPlugin {
    // This code is called only once after the server starts
    @Override
    public void onLoad() {
        getLogger().log(Level.INFO, "{0}.onLoad()", this.getClass().getName());
    }

    // This code is called after the server starts and after the /reload command
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
        getServer().getPluginManager().registerEvents(new TNTListener(), this);
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }
}
