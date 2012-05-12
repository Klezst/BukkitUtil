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

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Represents a Class that can be validated.
 * 
 * @author Klezst
 */

public interface Validatable<E> {
    /**
     * Expected to return the FileConfiguration to validate.
     * 
     * @author Klezst
     * @return the FileConfiguration to validate.
     */
    public FileConfiguration getConfig();
    
    /**
     * Expected to return a Map with keys of the settings' keys, and values of the settings' valid class.
     * 
     * @author Klezst
     * @return a Map containing the keys of the settings and their required classes.
     */
    public Map<String, Class<?>> getTypes();

    /**
     * Called, if a setting has been validated for existence and proper class. Allows further and custom validation.
     * Should be used to set the variables that will contain the setting.
     * May optionally throw IllegalArgumentException containing the error message.
     * 
     * @author Klezst
     * @param key the key of the setting.
     * @param value the value to be further validated.
     * @return null, if value was a valid setting; otherwise, a String representing errors.
     * @throws IllegalArgumentException if value is invalid.
     */
    public String set(String key, E value);
}