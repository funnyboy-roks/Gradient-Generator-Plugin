package com.funnyboyroks.gradientgenerator;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

import static org.bukkit.Bukkit.getServer;

public class ChatControls {

    public static void sendChatMessage(Player player, String msg){
        Collection<? extends Player> players = getServer().getOnlinePlayers();

        String fMessage = String.format("%s: %s", player.getDisplayName(), ChatColor.translateAlternateColorCodes('&', msg));

        getServer().getConsoleSender().sendMessage(fMessage);

//        player.sendMessage(msg);
        players.forEach((i) -> {
            i.sendMessage(fMessage);
        });
    }

}
