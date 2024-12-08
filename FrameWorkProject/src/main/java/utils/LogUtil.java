package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {

    // Logger instance
    private static final Logger logger = LogManager.getLogger(LogUtil.class);

    // Log info messages
    public static void info(String message) {
        logger.info(message);
    }

    // Log error messages
    public static void error(String message) {
        logger.error(message);
    }

    // Log debug messages
    public static void debug(String message) {
        logger.debug(message);
    }

    // Log warnings
    public static void warn(String message) {
        logger.warn(message);
    }

    // Log fatal messages
    public static void fatal(String message) {
        logger.fatal(message);
    }
}

