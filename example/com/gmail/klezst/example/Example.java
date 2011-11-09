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

/*	Note that you can put the library inside your .jar,
	you could put my compiled code inside your compiled code,
	you could have Maven compile my code inside your code,
	you could create a MANIFEST.MF with a classpath to the root directory of the server or lib folder
	(the user would put my compiled code into any of those folder),
	or you could FORCE the user to setup his server with a lib folder. // BAD idea.
	
	I suggest you use a MANIFEST.MF as shown in the example. When exporting from Eclipse on the fourth page
	choose "use existing manifest from workspace" and select the MANIFEST.MF.
	This will make Eclipse compile the MANIFEST.MF for you.
	
	Also note that this example has not been tested.
*/
package com.gmail.klezst.example;

import com.gmail.klezst.example.Setting;
import com.gmail.klezst.util.settings.InvalidSettingsException;
import com.gmail.klezst.util.settings.Settings;
import com.gmail.klezst.util.settings.Validatable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin
{
	// Statics
    private static final Logger log = Logger.getLogger("Minecraft");
    
    // Settings
    private Settings settings;
    private String defaultShopAccount;
    private boolean defaultShopAccountFree;
    
    // Utilities
    private File directory;
    
    @Override
    public void onDisable()
    {
        log(Level.INFO, "Disabled.");
    }
    
    @Override
    public void onEnable()
    {
		// Set up directory.
    	directory = getDataFolder();
    	directory.mkdirs();
        
        // Extract files.
        if (!extract("config.yml")) // The DEFAULT config.yml must be inside your jar in a folder entitled resources.
        {
        	getServer().getPluginManager().disablePlugin(this); // Occurs, iff errors when reading or writing the config.yml.
        	return;
        }
        
        // Load & Validate settings.
    	try
    	{
    		settings = new Settings(getConfig(), Setting.values()); // Tells Settings to validate the config.yml.
    	}
    	catch (InvalidSettingsException e)
    	{
    		log(Level.SEVERE, "Invalid config.yml:");
    		e.printExceptions(log, "[" + getDescription().getName() + "]\t"); // Prints the errors with the config.yml, so the user knows what to fix and how to fix it.
    		pm.disablePlugin(this);
    		return;
    	}
    	
    	// Set settings.
    	defaultShopAccount = getSetting(Setting.ACCOUNT_NAME, String.class);
    	defaultShopAccountFree = getSetting(Setting.ACCOUNT_FREE, Boolean.class);
        
        log(Level.INFO, "Enabled."); // YaY, Success!
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
		// Whatever you want to do on command.
		return true; // You have to return something or it doesn't compile.
    }
    
    /**
    * Copy files from the .jar.
    * 
    * @param names, Names of the files to be copied
    * @author sk89q, Klezst
    */
    private boolean extract(String... names)
    {
	   for (String name : names)
	   {
		   // Check, if file already exists.
	       File actual = new File(directory, name);
	       if (!actual.exists())
	       {
	    	   // Get input.
	    	   InputStream input;
		       	try
		    	{
		    		JarFile file = new JarFile(getFile());
		    		ZipEntry copy = file.getEntry("resources/" + name);
		    		if (copy == null)
		    		{
		    			log(Level.SEVERE, "Unable to find INTERNAL file " + name + ".");
		    			return false;
		    		}
		    		input = file.getInputStream(copy);
		    	}
		    	catch (IOException e)
		    	{
		    		log(Level.SEVERE, "Unable to read INTERNAL file " + name + ".");
		    		return false;
		    	}
		       	
	           if (input == null)
	           {
	        	   log(Level.SEVERE, "Unable to get InputStream for INTERNAL file " + name + ".");
	        	   return false;
	           }
	           
	           // Get & write to output
               FileOutputStream output = null;
               try
               {
                   output = new FileOutputStream(actual);
                   byte[] buf = new byte[8192];
                   int length = 0;
                   while ((length = input.read(buf)) > 0)
                   {
                       output.write(buf, 0, length);
                   }
                   
                   log(Level.INFO, "Resource " + name + " successfully extracted.");
               }
               catch (IOException e)
               {
                   log(Level.SEVERE, "Unable to write file " + name + ".");
                   e.printStackTrace();
                   return false; // Finally will still try to close the files
               }
               
               // Close files.
               finally
               {
                   try
                   {
                       if (input != null)
                       {
                           input.close();
                       }
                   }
                   catch (IOException e)
                   {
                	   log(Level.WARNING, "Unable to close INTERNAL file " + name + ".");
                   }

                   try
                   {
                       if (output != null)
                       {
                           output.close();
                       }
                   }
                   catch (IOException e)
                   {
                	   log(Level.WARNING, "Unable to close file " + name + ".");
                   }
               }
	       }
	   }
	   return true;
    }
	
	public <T> T getSetting(Validatable setting, Class<T> type) // This method can be used by ANY plugin to get your settings.
	{
		return settings.getSetting(setting, type);
	}
	
	protected void log(Level level, String message) // This method can be used by any of YOUR classes with a reference to this.
	{
		log.log(level, "[" + getDescription().getName() + "] " + message);
	}
}