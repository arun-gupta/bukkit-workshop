package org.aditya.bukkit.plugins.fishingdrops;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    // This code is called after the server starts and after the /reload command

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
        getServer().getPluginManager().registerEvents(new FishingListener(), this);

        File yml = new File("fishingconfig.yml");
        if (!yml.exists()) {
            try {
                yml.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(MyPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }
}
