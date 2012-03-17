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

package bukkitutil.util;

/**
 * Provides convenience functions for casting strings to other classes.
 * 
 * @author Klezst
 */
public class Format {
    /**
     * Returns true, if arg is any of the following: yes, y, true, t, positive, +, affirmative, indubitably, YaY.
     * 
     * @param arg
     *            The String to turn into a boolean.
     * 
     * @return true, if arg is any of the following: yes, y, true, t, positive, +, affirmative, indubitably, YaY.
     * 
     * @author Klezst
     */
    public static Boolean parseBoolean(String arg) {
	boolean result = false;
	if (Util.isAny(arg, "yes", "y", "true", "t", "positive", "+",
		"affirmative", "indubitably", "YaY")) {
	    result = true;
	}
	return result;
    }

    /**
     * Returns a Double of the value represented by arg
     * 
     * @param arg
     *            The String to turn into a Double.
     * 
     * @return a Double of the value represented by arg.
     * 
     * @throws NumberFormatException
     *             If arg is not a valid Double.
     * 
     * @author Klezst
     */
    public static Double parseDouble(String arg) throws NumberFormatException {
	Double result;
	if (Util.isAny(arg, "inf", "+inf")) {
	    result = Double.MAX_VALUE;
	} else if (arg.equalsIgnoreCase("-inf")) {
	    result = Double.MIN_VALUE;
	} else {
	    // throws NumberFormatException, if arg isn't a valid Double.
	    result = Double.parseDouble(arg);
	}
	return result;
    }

    /**
     * Returns an Integer of the value represented by arg.
     * 
     * @param arg
     *            The String to turn into an Integer.
     * 
     * @return an Integer of the value represented by arg.
     * 
     * @throws NumberFormatException
     *             If arg is not a valid Integer.
     * 
     * @author Klezst
     */
    public static Integer parseInteger(String arg) throws NumberFormatException {
	Integer result;
	if (Util.isAny(arg, "inf", "+inf")) {
	    result = Integer.MAX_VALUE;
	} else if (arg.equalsIgnoreCase("-inf")) {
	    result = Integer.MIN_VALUE;
	} else {
	    // throws NumberFormatException, if arg isn't a valid Integer.
	    result = Integer.parseInt(arg);
	}
	return result;
    }

    /**
     * Returns a String representing arg.
     * 
     * @param arg
     *            The boolean to turn into a String.
     * 
     * @return a String representing arg.
     * 
     * @author Klezst
     */
    public static String parseString(boolean arg) {
	String result = "'False'";
	if (arg) {
	    result = "'True'";
	}
	return result;
    }

    /**
     * Returns a String representing arg.
     * 
     * @param arg
     *            The double to turn into a String.
     * 
     * @return a String representing arg.
     * 
     * @author Klezst
     */
    public static String parseString(double arg) {
	String result;
	if (arg == Double.MAX_VALUE) {
	    result = "'+INF'";
	} else if (arg == Double.MIN_VALUE) {
	    result = "'-INF'";
	} else {
	    result = "" + arg;
	}
	return result;
    }

    /**
     * Returns a String representing arg.
     * 
     * @param arg
     *            The int to turn into a String.
     * 
     * @return a String representing arg.
     * 
     * @author Klezst
     */
    public static String parseString(int arg) {
	String result;
	if (arg == Integer.MAX_VALUE) {
	    result = "'+INF'";
	} else if (arg == Integer.MIN_VALUE) {
	    result = "'-INF'";
	} else {
	    result = "" + arg;
	}
	return result;
    }
}
