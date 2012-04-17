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

package bukkitutil;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import bukkitutil.util.Logging;

/**
 * Provides JavaPlugins with a logging method.
 * 
 * @author Klezst
 */
public abstract class BukkitUtilJavaPlugin extends JavaPlugin {
    private final String PREFIX;

    /**
     * Creates a LoggingJavaPlugin.
     * 
     * @author Klezst
     */
    public BukkitUtilJavaPlugin() {
	PREFIX = "";
    }

    /**
     * Creates a LoggingJavaPlugin with a logging prefix.
     * 
     * @param prefix
     *            The prefix to precede messages when logging.
     * 
     * @author Klezst
     */
    public BukkitUtilJavaPlugin(final String prefix) {
	this.PREFIX = prefix + " ";
    }

    /**
     * Logs messages.
     * 
     * @param level
     *            The Level of the message.
     * @param messages
     *            The messages to log.
     * 
     * @author Klezst
     */
    public void log(final Level level, final String... messages) {
	Logging.prefixLog(level, PREFIX, messages);
    }
    
    @Override
    public void onDisable() {
	log(Level.INFO, "Disabled.");
    }
    
    @Override
    public void onEnable() {
	log(Level.INFO, "Enabled.");
    }
}
