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

import java.util.List;
import java.util.logging.Logger;

/**
 * Represents invalid settings.
 * 
 * @author Klezst
 */
public class InvalidSettingsException extends RuntimeException {
    private static final long serialVersionUID = -4808725946544992759L;

    private List<InvalidSettingException> exceptions;

    public InvalidSettingsException(List<InvalidSettingException> errors) {
	super("Invalid config.yml");
	this.exceptions = errors;
    }

    public List<InvalidSettingException> getExceptions() {
	return this.exceptions;
    }

    /**
     * Prints the exception to log.
     * 
     * @param log
     *            The Logger to print to.
     * @param prefix
     *            A String that precedes each line printed. Should have a whitespace as last character, if not empty string.
     *            
     * @author Klezst
     */
    public void printExceptions(Logger log, String prefix) {
	for (InvalidSettingException exception : this.exceptions) {
	    exception.printException(log, prefix);
	}
    }
}
