/**
 * Copyright: Aditya Gupta
 */
package org.aditya.bukkit.plugins.spleefminigame;

import org.bukkit.Location;

/**
 * @author Aditya Gupta
 */
public class ArenaLocations {
    Location pos1;
    Location pos2;
    String arenaName;

    public ArenaLocations(Location pos1, Location pos2, String arenaName) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.arenaName = arenaName;
    }

    public Location getPos1() {
        return pos1;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }
    
    
}
