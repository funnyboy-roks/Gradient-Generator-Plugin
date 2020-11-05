package com.funnyboyroks.gradientgenerator;

import com.anomal.RainbowVis.HomogeneousRainbowException;
import com.anomal.RainbowVis.InvalidColourException;
import com.anomal.RainbowVis.NumberRangeException;
import com.anomal.RainbowVis.Rainbow;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class GradientMethods {
    public static final Pattern hexadecimalRegex = Pattern.compile("#[a-fA-F0-9]{6}");
//    public static ArrayList<String> createLinearGradient(int count, ArrayList<String> colours) {
//        Rainbow rainbow = new Rainbow();
//
//        for (int i = 0; i < colours.size(); ++i) {
//            String[] colour = {
//                    colours.get(i).substring(1, 3),
//                    colours.get(i).substring(3, 5),
//                    colours.get(i).substring(6, 7),
//            };
//            colours.set(i, "#");
//        }
//    }

    public static ArrayList<String> createGradient(int count, String[] colours) {
        Rainbow rainbow = new Rainbow();

        try {
            rainbow.setNumberRange(1, count);
            rainbow.setSpectrum(colours);
        } catch (HomogeneousRainbowException | InvalidColourException | NumberRangeException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> hexCodes = new ArrayList<String>();
        for (int i = 1; i <= count; i++) {
            hexCodes.add("#" + rainbow.colourAt(i));
        }
        return hexCodes;
    }

    public static String createGradFromString(String name, String[] colours) {
        int count = name.length();
        if (Math.min(count, colours.length) < 2) {
            return name;
        }

        ArrayList<String> cols = createGradient(count, colours);

        String colourCodes = "";
        for (int i = 0; i < cols.size(); i++) {
            colourCodes += ChatColor.of(cols.get(i)) + "" + name.charAt(i);
        }
        return colourCodes;
    }

    public static boolean isHexList(List<String> colours) {
        for (String i : colours) {
            if(!i.matches("#[a-fA-F0-9]{6}")){
                return false;
            }
        };
        return true;
    }

    public static boolean valueIn(Object[] arr, Object value) {
        for (Object o : arr) {
            if(o == value){
                return true;
            }
        }
        return false;
    }
}
