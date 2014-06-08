/**
 * Copyright: Aditya Gupta
 */
package org.aditya.bukkit.plugins.fishingdrops;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Aditya Gupta
 */
class FishingListener implements Listener {

    Material material = null;
    int percentage = 0;
    String[] splitarray = new String[]{"", ""};
    HashMap<Material, Integer> map = new HashMap<>();

    @EventHandler
    public void changeFishingDrops(PlayerFishEvent e) throws IOException, InterruptedException {
        if (e.getState() != PlayerFishEvent.State.CAUGHT_FISH) {
            return;
        }
        if (!(e.getCaught() instanceof Item)) {
            return;
        }

        File fishingconfig = new File("fishingconfig.txt");
        if (!fishingconfig.exists()) {
            fishingconfig.createNewFile();
        }

        BufferedReader reader = new BufferedReader(new FileReader("fishingconfig.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            splitarray = line.split(",");
            material = Material.getMaterial(splitarray[0]);
            percentage = Integer.parseInt(splitarray[1]);
            
            map.put(material, percentage);
        }

        Random random = new Random();
        int randomnumber;

        for (int j = 0; j < map.keySet().size(); j++) {
            randomnumber = random.nextInt(100);
            System.out.println("Random number: " + randomnumber);
            System.out.println("Percentage: " + map.get(map.keySet().toArray(new Material[0])[j]));
            if (randomnumber <= map.get(map.keySet().toArray(new Material[0])[j])) {
                Item i = (Item) e.getCaught();
                ItemStack is = i.getItemStack();
                is.setType(map.keySet().toArray(new Material[0])[j]);
                i.setItemStack(is);
            }
        }
    }
}
