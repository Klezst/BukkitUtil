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
     * @author Klezst
     * @param amount the amount to change the balance (can be negative).
     * @param name name of the account.
     * @throws NullPointerException if the economy isn't loaded yet.
     */
    public static void deltaBalance(double amount, String name) throws NullPointerException {
        economy.depositPlayer(name, amount);
    }

    /**
     * Returns amount in display format.
     * 
     * @author Klezst
     * @param amount the amount to be formatted.
     * @return amount in display format.
     * @throws NullPointerException if the economy isn't loaded yet.
     */
    public static String format(double amount) throws NullPointerException {
        return economy.format(amount);
    }

    /**
     * Returns an economy account's balance.
     * 
     * @author Klezst
     * @param name the name of the account.
     * @return the balance of the account called name.
     * 
     * @throws NullPointerException if the economy isn't loaded yet.
     */
    public static int getBalance(String name) throws NullPointerException {
        return (int) economy.getBalance(name);
    }
    
    /**
     * Returns the singular version of the currency name.
     * 
     * @author Klezst
     * @return the singular version of the currency name.
     * @throws NullPointerException if the economy is not loaded yet.
     */
    public static String getCurrencyNameSingular() {
        return economy.currencyNameSingular();
    }
    
    /**
     * Returns the singular version of the currency name.
     * 
     * @author Klezst
     * @return the singular version of the currency name.
     * @throws NullPointerException if the economy is not loaded yet.
     */
    public static String getCurrencyNamePlural() {
        return economy.currencyNamePlural();
    }
    
    /**
     * Returns an economy account's balance in display format.
     * 
     * @author Klezst
     * @param name the name of the account.
     * @return the balance of the account called name in display format.
     * @throws NullPointerException if the economy isn't loaded yet.
     */
    public static String getFormattedBalance(String name) throws NullPointerException {
        return economy.format(getBalance(name));
    }

    /**
     * Returns whether or not the economy is loaded yet.
     * 
     * @author Klezst
     * @return true, If the economy is loaded.
     */
    public static boolean isEnabled() {
        if (economy == null) {
            return false;
        }
        return economy.isEnabled();
    }
}
