/*
    BukkitUtil
    Copyright (C) 2011 Klezst

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.gmail.klezst.bukkit.bukkitutil.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Provides convenience functions for messages.
 *
 * @author Klezst
 */
public class Messaging {
    private static final String TAG_OPEN = "{";
    private static final String TAG_CLOSE = "}";
    
    private static Map<String, String> chatColors = new HashMap<String, String>();
    
    static {
        // Get the names of the ChatColors.
        for (ChatColor chatColor : ChatColor.values()) {
            chatColors.put(chatColor.name(), chatColor.toString());
        }
    }

    /**
     * Adds a new color tag for use with parseColor(String).
     * 
     * @author Klezst
     * @param name the color tag.
     * @param color the color. Must be a color tag that is already registered for use with parseColor(String).
     * @throws IllegalArgumentException if there is no color tag already registered for use with parseColor(String) by the name of color.
     */
    public static void addColor(String name, String color) throws IllegalArgumentException {
        String chatColor = chatColors.get(color);    
        if (chatColor == null)
        {
            throw new IllegalArgumentException(color + " is not a valid ChatColor!");
        }
        
        chatColors.put(name, chatColor);
    }
    /**
     * Adds a new color tag for use with parseColor(String).
     * 
     * @author Klezst
     * @param name the color tag.
     * @param chatColor the ChatColor for name to represent.
     */
    public static void addColor(String name, ChatColor chatColor) {
        chatColors.put(name, chatColor.toString());
    }
    
    /**
     * Returns a Map<String, String> with even indexed values as the keys and odd indexed values as the values.
     * If values ends on an even index, that value will not be included in the Map.
     * 
     * @author Klezst
     * @param values the keys and values to be put into the Map<String, String>.
     * @return a Map<String, String> with even indexed values as the keys and odd indexed values as the values.
     * @throws NullPointerException if values is null.
     */
    public static Map<String, String> buildContext(String... values) {
        Map<String, String> context = new HashMap<String, String>();
        for (int i = 0; i < values.length - 1; i += 2)
        {
            context.put(values[i], values[i + 1]);
        }
        return context;
    }
    
    /**
     * Returns a String with all elements of args separated by separator.
     * 
     * @author Klezst the separator to be used between elements of args.
     * @param args the objects to be separated by separator.
     * @return a String with all elements of args separated by separator.
     * @throws NullPointerException tf args is null.
     */
    public static String combine(String separator, Object... args) throws NullPointerException {
        String line = "";
        for (Object arg : args) {
            line += arg + separator;
        }
        return line.substring(0, line.length() - separator.length()); // Remove the extra separator.
    }

    /**
     * Returns a String with text occurring times times.
     * 
     * @author Klezst
     * @param text the string to repeat.
     * @param times the number of times to repeat text.
     * @return a String with text occurring times times.
     */
    public static String repeat(String text, int times) {
        String result = "";
        for (int i = 0; i < times; i++) {
            result += text;
        }
        return result;
    }

    /**
     * Replaces all instances of values's keys with it's corresponding value.
     * 
     * @author Klezst
     * @param text the original String.
     * @param values the keys to be replaced with values.
     * @return text with all instances of map keys replaced with it's corresponding value.
     * @throws NullPointerException if text is null or values is null.
     */
    public static String replace(String text, Map<String, String> values) throws NullPointerException {
        return replace(text, values, "", "");
    }

    /**
     * Replaces all instances of values's keys with it's corresponding value.
     * 
     * @author Klezst
     * @param text the original String.
     * @param values the keys to be replaced with values.
     * @param startMarker a String that must precede each key for any instances of that key to be replaced.
     * @param endMarker a String that must succeed each key for any instances of that key to be replaced.
     * @return text with all instances of map keys replaced with it's corresponding value.
     * @throws NullPointerException if text is null or values is null or startMarker is null or endMarker is null.
     */
    public static String replace(String text, Map<String, String> values, String startMarker, String endMarker) throws NullPointerException {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            text = text.replace(startMarker + entry.getKey() + endMarker,
                entry.getValue());
        }
        return text;
    }

    /**
     * Sends the messages to recipient. This allows '\n' and places a '\n' between each element of messages.
     * This automatically parses color.
     * 
     * @author Klezst
     * @param recipient the CommandSender to receive the messages.
     * @param messages the messages to send.
     * @throws NullPointerException if recipient is null or messages is null.
     */
    public static void send(CommandSender recipient, String... messages) throws NullPointerException {
        for (String message : messages) {
            for (String line : message.split("\n")) {
                recipient.sendMessage(parseColor(line));
            }
        }
    }

    /**
     * Replaces color tags with color codes.
     * 
     * @author Klezst
     * @param message the message to add color to.
     * @return a String with color tags replaced with color codes.
     * @throws NullPointerException if message is null.
     */
    public static String parseColor(final String message) throws NullPointerException {
        String colored = message;
        for (Entry<String, String> entry : chatColors.entrySet()) {
            colored = colored.replace(TAG_OPEN + entry.getKey() + TAG_CLOSE, entry.getValue());
        }
        return colored;
    }
    
    /**
     * Removes color codes and tags from a message.
     * 
     * @author Klezst
     * @param message the message to strip color from.
     * @return a String without color codes and tags.
     * @throws NullPointerException if message is null.
     */
    // TODO: Test this method.
    public static String stripColor(final String message) {
        String colorless = message;
        for (Entry<String, String> entry : chatColors.entrySet()) {
            colorless = colorless.replace(TAG_OPEN + entry.getKey() + TAG_CLOSE, "");
            colorless = colorless.replace(entry.getValue(), "");
        }
        return colorless;
    }
}
