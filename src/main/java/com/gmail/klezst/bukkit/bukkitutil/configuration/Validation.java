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

package com.gmail.klezst.bukkit.bukkitutil.configuration;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Provides convenience functions for use with validating settings.
 * 
 * @author Klezst
 */
public class Validation {
    /**
     * Returns the ChatColor corresponding to value. If no such ChatColor exists throws an InvalidSettingException
     * 
     * @author Klezst
     * @param key
     * @param value
     * @return the ChatColor corresponding to value.
     * @throws InvalidSettingException if value is not a ChatColor.
     */
    // TODO: Accept color codes.
    public static ChatColor getChatColor(String key, String value) throws IllegalArgumentException {
        value = value.replace(" ", "_");
        value = value.toUpperCase();

        ChatColor color;
        try {
            color = ChatColor.valueOf(value);
        } catch (IllegalArgumentException e) {
            String examples = "";
            for (ChatColor chatColor : ChatColor.values()) {
            examples += chatColor.name() + ", ";
            }
            examples = examples.substring(0, examples.length() - 2);

            throw new IllegalArgumentException(key
                + ": Must be a color. Valid colors are:\t" + examples);
        }
        return color;
    }
    
    /**
     * Loads and validates configuration settings, invalid settings will not be saved.
     * 
     * @author Klezst
     * @param settings the settings to validate and store.
     * @return An empty String if no errors were encountered; otherwise, a String describing the errors.
     * @throws NullPointerException if settings is null or any of the implied functions from Validatable return null.
     */
    public static String validate(Validatable<?>[] settings) {
        String invalid = "";
        for (Validatable<?> setting : settings) {
            String errors = Validation.validate(setting);
            if (!errors.isEmpty()) {
            invalid += errors + "\n";
            }
        }
        return invalid;
    }
    
    /**
     * Loads and validates a configuration setting, invalid settings will not be saved.
     * 
     * @author Klezst
     * @param setting the setting to validate and store.
     * @return An empty String if no errors were encountered; otherwise, a String describing the errors.
     * @throws NullPointerException if setting is null or any of the implied functions from Validatable return null.
     */
    @SuppressWarnings("unchecked")
    public static <T> String validate(Validatable<T> setting) {
        String invalid = "";
        FileConfiguration config = setting.getConfig();
        for (Map.Entry<String, Class<?>> entry : setting.getTypes().entrySet()) {
            String key = entry.getKey();
            Object value = config.get(key);
            if (value == null) {
                invalid += key + ": Must specify a value.";
            } else {
                Class<?> type = entry.getValue();
                if (value.getClass().equals(type)) {
                    String errors = null;
                    try {
                        errors = setting.set(key, (T)value);
                    } catch (ClassCastException e) {
                        throw new IllegalArgumentException("Programmer error:\n\tYou either caused a ClassCastException, or getType() returns a different class than the type parameter you passed to Validatable.");
                    } catch (IllegalArgumentException e) {
                        errors = e.getMessage();
                    }
                    if (errors != null && !errors.isEmpty()) {
                        invalid += key + ": " + errors;
                    }
                } else {
                    invalid += key + ": Must be a " + type.getSimpleName();
                }
            }
        }
        return invalid;
    }
}
