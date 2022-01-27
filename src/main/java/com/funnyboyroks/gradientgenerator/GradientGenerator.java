package com.funnyboyroks.gradientgenerator;

//import com.anomal.RainbowVis.*;
import com.anomal.RainbowVis.Rainbow;
import com.funnyboyroks.utils.GradientGeneration;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class GradientGenerator extends JavaPlugin {

    private static GradientGenerator inst;

    public GradientGenerator() {
        inst = this;
    }

    public static GradientGenerator getInst() {
        return inst;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), this);
        getCommand("gradient").setExecutor((sender, command, alias, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("generateGenerator.generateGradient")) {
                    if(args.length < 3){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cMissing required parameters.  Make sure that you have at least 2 hex codes.  Usage: &b/gradient <word> [<hexCodes>]&c."));
                        return false;
                    }

                    List<String> colours = new ArrayList<>(Arrays.asList(args).subList(1, args.length));

                    String[] coloursArr = colours.toArray(new String[0]);
                    String word = args[0];

                    if(!GradientMethods.isHexList(colours)){
                        player.sendMessage(ChatColor.RED + "Please enter at least 2 hexadecimal colour values, starting with '#'");
                        return false;
                    }

                    ChatControls.sendChatMessage(player, GradientGeneration.generateGradient(word, coloursArr));

                    return true;
                }
                player.sendMessage(ChatColor.RED + "You don't have permission to use that command.");
                // Player
            } else {
                //console
                sender.sendMessage("You must be in-game to run this command.");
            }
            return true;
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
