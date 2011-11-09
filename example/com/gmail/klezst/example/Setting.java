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
	// Do not use primitive data types or null
	ACCOUNT_NAME("default-shop-account.name", String.class),
	ACCOUNT_FREE("default-shop-account.is-free", Boolean.class),
	BRACKET_COLOR("text-color.bracket", String.class)
	{
		@Override
		public Object validate(Object value) // This is a custom validation script that is run after the value has been validated to exist and that it is of the class specified by getType()
		{
			return Validation.getChatColor(this.getKey(), (String)value); // A library function provided to check if a String is a ChatColor
		}
	},
	COMMAND_COLOR("text-color.command", String.class)
	{
		@Override
		public Object validate(Object value)
		{
			return Validation.getChatColor(this.getKey(), (String)value);
		}
	},
	ERROR_COLOR("text-color.error", String.class)
	{
		public Object validate(Object value)
		{
			return Validation.getChatColor(this.getKey(), (String)value);
		}
	},
	NORMAL_COLOR("text-color.normal", String.class)
	{
		public Object validate(Object value)
		{
			return Validation.getChatColor(this.getKey(), (String)value);
		}
	},
	PARAM_COLOR("text-color.param", String.class)
	{
		public Object validate(Object value)
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
	SHOP_TAG("shop-tag", String.class),
	ITEMS_MAX_PER_PURCHASE("items-max-per.purchase", Integer.class),
	ITEMS_MAX_PER_SALE("items-max-per.sale", Integer.class),
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
