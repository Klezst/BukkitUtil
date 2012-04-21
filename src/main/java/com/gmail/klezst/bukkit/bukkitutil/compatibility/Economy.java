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

package com.gmail.klezst.bukkit.bukkitutil.compatibility;

import org.bukkit.Bukkit;

/**
 * Controls communication with economy plugins.
 * 
 * @author Klezst
 */
public class Economy {
    public static net.milkbowl.vault.economy.Economy economy = null;
    static {
	try {
	    economy = Bukkit.getServer().getServicesManager()
		    .getRegistration(net.milkbowl.vault.economy.Economy.class)
		    .getProvider();
	} catch (NullPointerException e) {
	    // Nothing needs to be done, since whatever method was called will throw a NullPointerException.
	}
    }

    /**
     * Changes an economy account's balance.
     * 
     * @param amount
     *            Amount to change the balance (can be negative).
     * @param name
     *            Name of the account.
     * 
     * @throws NullPointerException
     *             If the economy isn't loaded yet.
     * 
     * @author Klezst
     */
    public static void deltaBalance(double amount, String name)
	    throws NullPointerException {
	economy.depositPlayer(name, amount);
    }

    /**
     * Returns amount in display format.
     * 
     * @param amount
     *            Amount to be formatted.
     * 
     * @return amount in display format.
     * 
     * @throws NullPointerException
     *             If the economy isn't loaded yet.
     * 
     * @author Klezst
     */
    public static String format(double amount) throws NullPointerException {
	return economy.format(amount);
    }

    /**
     * Returns an economy account's balance.
     * 
     * @param name
     *            Name of the account.
     * 
     * @return Balance of the account called name.
     * 
     * @throws NullPointerException
     *             If the economy isn't loaded yet.
     * 
     * @author Klezst
     */
    public static int getBalance(String name) throws NullPointerException {
	return (int) economy.getBalance(name);
    }
    
    /**
     * Returns the singular version of the currency name.
     * 
     * @return the singular version of the currency name.
     * 
     * @throws NullPointerException
     * 		   If the economy is not loaded yet.
     * 
     * @author Klezst
     */
    public static String getCurrencyNameSingular() {
	return economy.currencyNameSingular();
    }
    
    /**
     * Returns the singular version of the currency name.
     * 
     * @return the singular version of the currency name.
     * 
     * @throws NullPointerException
     * 		   If the economy is not loaded yet.
     * 
     * @author Klezst
     */
    public static String getCurrencyNamePlural() {
	return economy.currencyNamePlural();
    }
    
    /**
     * Returns an economy account's balance in display format.
     * 
     * @param name
     *            Name of the account.
     * 
     * @return Balance of the account called name in display format.
     * 
     * @throws NullPointerException
     *             If the economy isn't loaded yet.
     * 
     * @author Klezst
     */
    public static String getFormattedBalance(String name)
	    throws NullPointerException {
	return economy.format(getBalance(name));
    }

    /**
     * Returns whether or not the economy is loaded yet.
     * 
     * @return True, If the economy is loaded.
     * 
     * @author Klezst
     */
    public static boolean isEnabled() {
	if (economy == null) {
	    return false;
	}
	return economy.isEnabled();
    }
}
