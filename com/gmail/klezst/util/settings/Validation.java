/*
	SettingsValidation
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

package com.gmail.klezst.util.settings;

import org.bukkit.ChatColor;

public class Validation
{
	/**
	 * Returns the ChatColor corresponding to value. If no such ChatColor exists throws an InvalidSettingException
	 * @param key
	 * @param value
	 * @return
	 */
	public static ChatColor getChatColor(String key, String value)
	{
		value = value.replace(" ", "_");
		value = value.toUpperCase();
		
		ChatColor color;
		try
		{
			color = ChatColor.valueOf(value);
		}
		catch (IllegalArgumentException e)
		{
			String examples = "";
			for (ChatColor chatColor : ChatColor.values())
			{
				examples += chatColor.name() + ", ";
			}
			examples = examples.substring(0, examples.length() - 2);
			
			throw new InvalidSettingException(key, "Must be a color. Valid colors are:", "\t" + examples);
		}
		return color;
	}
}
