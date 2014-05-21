/**
 * Copyright: Aditya Gupta
 */
package org.devoxx4kids.bukkit.plugins.blockmoney;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Aditya Gupta
 */
class MoneyListener implements Listener {

    float moneyToReward = 0.10F;
    
    HashMap<Player, Float> money = new HashMap();
            
    public MoneyListener() {
    }
    
    @EventHandler
    public void checkForNullMoney(PlayerJoinEvent event){
        if(money.get(event.getPlayer()) == null){
            money.put(event.getPlayer(), 0F);
        }
    }
    
    @EventHandler
    public void rewardBlockPlace(BlockPlaceEvent event){
        money.put(event.getPlayer(), 
                money.get(event.getPlayer()) 
                + moneyToReward);
        event.getPlayer().sendMessage(ChatColor.GOLD + "You now have " 
                + ChatColor.DARK_GREEN + "$" + String.format("%.2f", money.get(event.getPlayer())));
    }
    
    @EventHandler
    public void rewardBlockBreak(BlockBreakEvent event){
        money.put(event.getPlayer(),
                money.get(event.getPlayer()) 
                + moneyToReward);
        event.getPlayer().sendMessage(ChatColor.GOLD + "You now have " 
                + ChatColor.DARK_GREEN + "$" + String.format("%.2f", money.get(event.getPlayer())));
    }

}
