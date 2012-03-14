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

package bukkitutil.compatibility;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * Controls communication with permissions plugins.
 * 
 * @author Klezst
 */
public class Permission {
    public static net.milkbowl.vault.permission.Permission permission = Bukkit
	    .getServer().getServicesManager()
	    .getRegistration(net.milkbowl.vault.permission.Permission.class)
	    .getProvider(); // Will not throw NullPointerException, since Vault is a dependency.

    /**
     * Returns true, if sender has the permission node.
     * 
     * @param sender
     *            Who to check for permission.
     * @param node
     *            The permission needed.
     *            
     * @return True, if sender has the permission node.
     * 
     * @author Klezst
     */
    public static boolean hasPermission(CommandSender sender, String node) {
	return permission.has(sender, node);
    }
}
