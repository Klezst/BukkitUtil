package com.gmail.haloinverse.DynamicMarket;

import com.gmail.klezst.util.settings.Validatable;
import com.gmail.klezst.util.settings.Validation;

public enum Setting implements Validatable
{
	// Do not use primitive data types or null
	ACCOUNT_NAME("default-shop-account.name", String.class),
	ACCOUNT_FREE("default-shop-account.is-free", Boolean.class),
	BRACKET_COLOR("text-color.bracket", String.class)
	{
		public Object validate(Object value) // This is a custom validation script that is run after the value has been validated to exist and that it is of the class specified by getType()
		{
			return Validation.getChatColor(this.getKey(), (String)value); // A library function provided to check if a String is a ChatColor
		}
	},
	COMMAND_COLOR("text-color.command", String.class)
	{
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
	
	public String getKey()
	{
		return key;
	}
	
	public Class<?> getType()
	{
		return type;
	}
	
	public Object validate(Object value)
	{
		return value;
	}
}
