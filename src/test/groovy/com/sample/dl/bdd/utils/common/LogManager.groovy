package com.sample.dl.bdd.utils.common

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class LogManager {

    private final static List PERMIT_LEVELS = Arrays.asList("INFO", "WARN", "ERROR");

    /**
     * Only PERMIT_LEVEL can populate the log level
     **/
    static void setLogLevel() {
        // Init config properties before Spring load
        def prop = new Properties();
        def inputStream = ClassLoader.getSystemResourceAsStream("config.properties")
        prop.load(inputStream);
        def logLevel = prop.getProperty("log.level")

        // Set log level based on config properties
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory()

        if (!PERMIT_LEVELS.any { it.equalsIgnoreCase(logLevel) }) {
            loggerContext.stop()
        } else {
            ch.qos.logback.classic.Logger logger = loggerContext.getLogger(this.class)
            logger.setLevel(Level.toLevel(logLevel))
        }
    }
}
