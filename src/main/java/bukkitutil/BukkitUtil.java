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

import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.plugin.PluginManager;

import bukkitutil.util.IO;

public class BukkitUtil extends BukkitUtilJavaPlugin {
    public BukkitUtil() {
	super("[BukkitUtil]");
    }
    
    public static void log(Level level, String message) {
	log(level, message);
    }
    
    @Override
    public void onEnable() {
	PluginManager pm = this.getServer().getPluginManager();
	
	// Extract resources.
	try {
		IO.extract(this, "LICENSE.txt");
	} catch (IOException e) {
		log(Level.SEVERE, "Error extracting resources!");
		pm.disablePlugin(this);
		return;
	}
	
	super.onEnable();
    }
}
