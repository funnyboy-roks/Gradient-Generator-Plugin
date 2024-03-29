package com.funnyboyroks.gradientgenerator;

import com.anomal.RainbowVis.Rainbow;
import com.funnyboyroks.utils.GradientGeneration;
import net.md_5.bungee.api.ChatColor;
//import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Listeners implements Listener {
    public static final Pattern hexPattern = Pattern.compile("#[a-fA-F0-9]{6}");
    public static final Pattern gradientPattern = Pattern.compile("&grad\\([^()]+( #[a-fA-F0-9]{6})+\\)");

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String msg = event.getMessage();

        Matcher gradMatch = gradientPattern.matcher(msg);
        while (gradMatch.find()) {
            String colour = msg.substring(gradMatch.start(), gradMatch.end());
            String colourArgs = colour.replace("&grad(", "");
            colourArgs = colourArgs.substring(0, colourArgs.length() - 2);
            String[] args = colourArgs.split(" ");
            List<String> colours = Arrays.asList(args).subList(1, args.length);
            String[] coloursArr = colours.toArray(new String[0]);
            String word = args[0];
            msg = msg.replace(colour, GradientGeneration.generateGradient(word, coloursArr));
            gradMatch = gradientPattern.matcher(msg);
        }

//        ChatControls.sendChatMessage(player, msg);
        event.setMessage(msg);
//        event.setCancelled(true);

    }


}
