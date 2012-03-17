package bukkitutil;

import java.util.logging.Level;

import bukkitutil.util.Logging;

public class BukkitUtil {
    private static final String PREFIX = "[BukkitUtil] ";
    
    public static void log(Level level, String... messages) {
	Logging.prefixLog(level, PREFIX, messages);
    }
}
