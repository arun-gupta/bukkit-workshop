package org.devoxx4kids.bukkit.plugins.scheduler;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author arungupta
 */
public class MyTask extends BukkitRunnable {

    private final JavaPlugin plugin;
    private int counter;

    public MyTask(JavaPlugin plugin, int counter) {
        System.out.println("MyTask.ctor: " + counter);
        this.plugin = plugin;
        if (counter < 1) {
            throw new IllegalArgumentException("counter must be greater than 1");
        } else {
            this.counter = counter;
        }
    }

    @Override
    public void run() {
        System.out.println("run: " + counter);
        if (counter > 0) {
            plugin.getServer().broadcastMessage("Commence greeting in " + counter--);
        } else {
            plugin.getServer().broadcastMessage("Welcome to Bukkit! Remember to read the documentation!");
            this.cancel();
        }
    }

}
