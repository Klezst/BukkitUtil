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

package com.gmail.klezst.example;

import com.gmail.klezst.util.settings.Validatable;
import com.gmail.klezst.util.settings.Validation;

public enum Setting implements Validatable
{
	// Do not use primitive data types or null.
	INFINITE_FUNDING("default-shop.infinite-funding", Boolean.class), // Boolean.class is the class of the type of data you expect to get from the config.yml.
	ITEMS_MAX_PER_TRANSACTION("default-shop.items-max-per-transaction", Integer.class),
	BRACKET_COLOR("text-color.bracket", String.class)
	{
		@Override
		public Object validate(Object value) throws InvalidSettingsException // This is a custom validation script that is run after the value has been validated to exist and that it is of the class specified by getType().
		{
			return Validation.getChatColor(this.getKey(), (String)value); // A library function provided to check if a String is a ChatColor. NOTE: We return what we want stored for the setting. In this case we return the ChatColor by the name of value.
		}
	},
	COMMAND_COLOR("text-color.command", String.class)
	{
		@Override
		public Object validate(Object value) throws InvalidSettingsException
		{
			return Validation.getChatColor(this.getKey(), (String)value);
		}
	},
	ERROR_COLOR("text-color.error", String.class)
	{
		@Override
		public Object validate(Object value) throws InvalidSettingsException
		{
			return Validation.getChatColor(this.getKey(), (String)value);
		}
	},
	NORMAL_COLOR("text-color.normal", String.class)
	{
		@Override
		public Object validate(Object value) throws InvalidSettingsException
		{
			return Validation.getChatColor(this.getKey(), (String)value);
		}
	},
	PARAM_COLOR("text-color.param", String.class)
	{
		@Override
		public Object validate(Object value) throws InvalidSettingsException
		{
			return Validation.getChatColor(this.getKey(), (String)value);
		}
	},
	DATABASE_TYPE("database-type", String.class),
	ITEMS_DB_PATH("items-db-path", String.class),
	MYSQL_URL("mysql.url", String.class),
	MYSQL_USER("mysql.user", String.class),
	MYSQL_PASS("mysql.pass", String.class),
	MYSQL_ENGINE("mysql.engine", String.class),
	IMPORT_EXPORT_FILE("import-export.file", String.class),
	IMPORT_EXPORT_PATH("import-export.path", String.class),
	TRANSACTION_LOG_FILE("transaction-log.file", String.class),
	TRANSACTION_LOG_AUTOFLUSH("transaction-log.autoflush", Boolean.class),
	DEBUG("debug", Boolean.class);
	
	private String key;
	private Class<?> type;
	
	private Setting(String key, Class<?> type)
	{
		this.key = key;
		this.type = type;
	}
	
	@Override
	public String getKey()
	{
		return key;
	}
	
	@Override
	public Class<?> getType()
	{
		return type;
	}
	
	@Override
	public Object validate(Object value)
	{
		return value;
	}
}
