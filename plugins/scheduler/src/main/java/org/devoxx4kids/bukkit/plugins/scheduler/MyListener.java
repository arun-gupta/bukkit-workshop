package org.devoxx4kids.bukkit.plugins.scheduler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author arungupta
 */
public class MyListener implements Listener {
    
    private final MyPlugin plugin;

    public MyListener(MyPlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // Create the task and schedule to run it five times, once every 20 ticks
        new MyTask(this.plugin, 5).runTaskTimer(this.plugin, 0, 20);
    }
}
