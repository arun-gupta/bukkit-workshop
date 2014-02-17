/**
 * Copyright: Aditya Gupta
 */
package org.devoxx4kids.bukkit.plugins.slots;

import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * @author Aditya Gupta
 */
public class SlotsListener implements Listener {
    
    public static double money = 0.00;
    private double cost = 0.00;

    @EventHandler
    public void doSlots(BlockDamageEvent event) throws InterruptedException {
        Player player = event.getPlayer();
        
        if(event.getBlock().getTypeId() != 68){
            return;
        }
        Sign sign = (Sign) event.getBlock().getState();
        if (!("SLOTS".equals(sign.getLine(0)) && "***".equals(sign.getLine(3)))){
            return;
        }
        event.setCancelled(true);
        cost = Double.parseDouble(sign.getLine(1));

        if(money >= cost){
            money-=cost;

            int slot1;
            int slot2;
            int slot3;

            Random seed1 = new Random();
            slot1 = seed1.nextInt(20);

            Random seed2 = new Random();
            slot2 = seed2.nextInt(20);

            Random seed3 = new Random();
            slot3 = seed3.nextInt(20);

            player.sendMessage(ChatColor.GREEN + "You got these numbers: " + slot1 + ", " + slot2 + ", and " + slot3 + ".");

            if(slot1 == slot2 || slot2 == slot3 || slot1 == slot3){
                money += cost * 20;
                double moneyWon = cost * 20;
                player.sendMessage(ChatColor.GOLD + "You won $" + String.format("%1$.2f", moneyWon));
            }else if(slot1 == slot2 && slot2 == slot3){
                money += cost * 100;
                double moneyWon = cost * 100;
                player.sendMessage(ChatColor.GOLD + "You won $" + String.format("%1$.2f", moneyWon));
            }
        }else{
            player.sendMessage(ChatColor.RED + "You don't have enough money to use this slot machine!");
        }
    }
    
    @EventHandler
    public void awardMoney(EntityDeathEvent event){
        if(event.getEntity().getLastDamageCause().getCause() == DamageCause.ENTITY_ATTACK){
            EntityType entityType = event.getEntity().getType();
            Player player = event.getEntity().getKiller();
   
//            Work on this later.
            
//            switch (entityType.ordinal()){
//                
//                case EntityType.ZOMBIE:
//                    money += 1.00;
//                    break;
//            }
            
            if(entityType == EntityType.ZOMBIE || entityType == EntityType.SKELETON
               || entityType == EntityType.SPIDER || entityType == EntityType.CREEPER){
                money += 1.00;
            }else if(entityType == EntityType.ENDERMAN){
                money += 2.50;
            }else if(entityType == EntityType.PIG_ZOMBIE){
                money += 3.00;
            }else if(entityType == EntityType.SLIME){
                money += 0.50;
            }else if(entityType == EntityType.MAGMA_CUBE || entityType == EntityType.CAVE_SPIDER){
                money += 1.50;
            }else if(entityType == EntityType.GHAST){
                money += 5.00;
            }else if(entityType == EntityType.SHEEP || entityType == EntityType.PIG
                     || entityType == EntityType.COW){
                money += 0.10;
            }else if(entityType == EntityType.CHICKEN){
                money += 0.05;
            }else if(entityType == EntityType.ENDER_DRAGON){
                money += 500.00;
            }else if(entityType == EntityType.ENDER_CRYSTAL){
                money += 20.00;
            }else if(entityType == EntityType.IRON_GOLEM){
                money += 10.00;
            }
            
            player.sendMessage(ChatColor.AQUA + "You now have: $" + String.format("%1$.2f", money));
        }
    }
}
