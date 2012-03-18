package bukkitutil.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides convenience functions for logging.
 * 
 * @author Klezst
 */
public class Logging {
    private static final Logger logger = Logger.getLogger("Minecraft");
    
    /**
     * Returns the logger used for logging.
     * 
     * @returns the logger used for logging.
     * 
     * @author Klezst
     */
    public static Logger getLogger() {
	return logger;
    }
    
    /**
     * Logs messages.
     * 
     * @param level
     *            The Level of the message.
     * @param messages
     *            The messages to log.
     * 
     * @author Klezst
     */
    public static void log(final Level level, final String message) {
	logger.log(level, message);
    }
    
    /**
     * Logs messages. Each line will have PREFIX added before it.
     * 
     * @param level
     *            The Level of the message.
     * @param messages
     *            The messages to log.
     * 
     * @author Klezst
     */
    public static void prefixLog(final Level level, final String prefix, final String... messages) {
	for (String message : messages) {
	    for (String line : message.split("\n")) {
		log(level, prefix + line);
	    }
	}
    }
}
