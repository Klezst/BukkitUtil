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
     * @param key
     * @param value
     * 
     * @return The ChatColor corresponding to value.
     * 
     * @throws InvalidSettingException
     *             If value is not a ChatColor.
     * 
     * @author Klezst
     */
    // TODO: Accept color codes.
    public static ChatColor getChatColor(String key, String value)
	    throws IllegalArgumentException {
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
     * Validates and stores configuration settings, invalid settings will not be stored.
     * 
     * @param enums
     *            The settings to validate and store.
     * @param config
     *            The FileConfiguration that contains the settings.
     * 
     * @throws InvalidSettingsException
     *             Thrown, if any settings fail validation.
     * 
     * @author Klezst
     */
    @SuppressWarnings("unchecked")
    public static <T> String validate(FileConfiguration config,
	    Validatable<T>[] enums) {
	String invalid = "";
	for (Validatable<T> setting : enums) {
	    for (Map.Entry<String, Class<?>> entry : setting.getTypes().entrySet()) {
		String key = entry.getKey();
		Object value = config.get(key);
		
		// Validate
		if (value == null) {
		    invalid += key + ": Must specify a value\n";
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
			    invalid += key + ": " + errors + "\n";
			}
		    } else {
			invalid += key + ": Must be a " + type.getSimpleName() + "\n";
		    }
		}
	    }
	}

	return invalid;
    }
}
