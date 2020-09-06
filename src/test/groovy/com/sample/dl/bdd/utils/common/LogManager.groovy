package com.sample.dl.bdd.utils.common

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import org.slf4j.LoggerFactory

class LogManager {

    private final static List PERMIT_LEVELS = Arrays.asList("INFO", "WARN", "ERROR");
    private final static LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory()
    private static ch.qos.logback.classic.Logger log
    /**
     * Only PERMIT_LEVEL can populate the log level
     **/
    static void setLogLevel() {
        def logLevel = ConfigHandler.init().getLogLevel()

        if (!PERMIT_LEVELS.any { it.equalsIgnoreCase(logLevel) }) {
            loggerContext.stop()
        } else {
            log = loggerContext.getLogger(this.class)
            log.setLevel(Level.toLevel(logLevel))
        }
    }

    static void info(String msg) {
        log.info(msg)
    }
}
