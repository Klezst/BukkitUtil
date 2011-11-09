package com.gmail.klezst.util.settings;

import org.bukkit.ChatColor;

public class Validation
{
	public static ChatColor getChatColor(String key, String value)
	{
		value = value.replaceFirst(" ", "_");
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
			
			throw new InvalidSettingException(key, "Must be a color. For example:", "\t" + examples);
		}
		return color;
	}
}
