package org.devoxx4kids.bukkit.plugins.newblock;

import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    
    public static boolean isElevatorOn = true;

    @Override
    public void onLoad() {
        getLogger().log(Level.INFO, "{0}.onLoad()", this.getClass().getName());
    }

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
        getServer().getPluginManager().registerEvents(new ElevatorListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(cmd.getName().equals("elevator"))) {
            return false;
        }
        if (!(sender instanceof Player)) {
            return false;
        }
        if(args.length != 1){
            ((Player) sender).sendMessage(ChatColor.BLUE + "This command should have only one argument. You put " + 
                    ChatColor.AQUA + args.length + ChatColor.BLUE + " arguments in. Off with your head!");
            ((Player) sender).sendMessage(ChatColor.GOLD + "Usage: /elevator [help:version:toggle]");
        }
        if("help".equals(args[1])){
            ((Player) sender).sendMessage(ChatColor.GREEN + "~~~~~~ Elevator Help ~~~~~~");
            ((Player) sender).sendMessage(ChatColor.GOLD + "When you place a redstone block down, you have made an elevator."
                    + " To use the elevator, you must have a redstone dust in your inventory. Stand on the elevator"
                    + "and type '/up' to go up to the closest elevator upwards from your current elevator."
                    + " To go down, type '/down' and you will be taken"
                    + "to the nearest elevator underneath the one you are standing on.");
        }else if("version".equals(args[1])){
            ((Player) sender).sendMessage(ChatColor.GOLD + "ElevatorPlugin version " + ChatColor.BLUE + "1.0" + ChatColor.GOLD
                    + " by Aditya Gutpa");
        }else if("toggle".equals(args[1])){
            if(isElevatorOn == true){
                isElevatorOn = false;
            }else{
                isElevatorOn = true;
            }
        }
        return true;
    }
}
