package ru.splat.tm;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Иван on 08.02.2017.
 */
public class LoggerGlobal {
    private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void log(String message) {
        log.log(Level.INFO, message);
    }
}
