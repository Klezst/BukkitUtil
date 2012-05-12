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

package com.gmail.klezst.bukkit.bukkitutil;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Provides JavaPlugins with resource extraction, config validation, and logging automagic.
 * 
 * @author Klezst
 */
public abstract class BukkitUtilJavaPlugin extends JavaPlugin {  
    /**
     * Logs messages.
     * 
     * @author Klezst
     * @param level the Level of the message.
     * @param messages the messages to log.
     * @throws NullPointerException if level, messages, or an element of messages is null.
     */
    public void log(final Level level, final String... messages) {
        Logger logger = this.getLogger();
        for (String message : messages) {
            for (String line : message.split("\n")) {
                logger.log(level, line);
            }
        }
    }
    
    /**
     * This method is called when the {@link BukkitUtilJavaPlugin} is enabled.
     * It should be used as a callback, for anything that a plugin may need to
     * do when the it is enabled.
     * 
     * @author Klezst
     */
    protected void disable() {
        // Do nothing.
    }
    
    /**
     * This method is called when the {@link BukkitUtilJavaPlugin} is disabled.
     * It should be used as a callback, for anything that the plugin may need to
     * do when the it is disabled.
     * 
     * @author Klezst
     */
    protected void enable() {
        // Do nothing.
    }
    
    /**
     * This method is called when the {@link BukkitUtilJavaPlugin} is disabled.
     * It should only be used by Bukkit. If you need to disable the plugin, use
     * Bukkit's PluginManager to disable plugins.<p>
     *
     * Calls disable() and then logs "Disabled", if disable() did not enable the plugin.
     * 
     * @author Klezst
     */
    @Override
    public final void onDisable() {
        this.disable();
        if (!this.isEnabled()) {
            this.log(Level.INFO, "Disabled");
        }
    }
    
    /**
     * This method is called when the {@link BukkitUtilJavaPlugin} is enabled.
     * It should only be used by Bukkit. If you need to enable the plugin, use
     * Bukkit's PluginManager to enable plugins.<p>
     *
     * Calls enable() and then logs "Enabled", if enable() did not disable the plugin.
     * 
     * @author Klezst
     */
    @Override
    public final void onEnable() {
        this.enable();
        if (this.isEnabled()) {
            this.log(Level.INFO, "Enabled.");
        }
    }
}
