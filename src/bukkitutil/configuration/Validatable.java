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

package bukkitutil.configuration;

/**
 * Represents a Class that can be validated.
 * 
 * @author Klezst
 */
public interface Validatable {
    /**
     * Expected to return a settings key.
     * 
     * @return Key of a setting.
     * 
     * @author Klezst
     */
    public String getKey();

    /**
     * Expected to return the required class of a settings value.
     * 
     * @return Required class of a settings value, should not be a primitive data type.
     * 
     * @author Klezst
     */
    public Class<?> getType();

    /**
     * Called, if a setting has been validated for existence and proper class. Allows further and custom validation. Must return the value to be stored for the setting or throw an
     * InvalidSettingException.
     * 
     * @param value
     *            The value to be further validated.
     * 
     * @return The value to be stored for the setting.
     * 
     * @throws InvalidSettingException
     *             If value is invalid.
     * @throws InvalidSettingsException
     *             If value is invalid.
     * 
     * @author Klezst
     */
    public Object validate(Object value) throws InvalidSettingException,
	    InvalidSettingsException;
}