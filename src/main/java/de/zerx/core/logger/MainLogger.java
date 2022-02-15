package de.zerx.core.logger;

import de.zerx.core.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 22.12.2021 22:11
 */
public class MainLogger {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Main.class);

    public MainLogger() {
        // ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        //root.setLevel(Level.DEBUG);
    }

    public static Logger get() {
        return LOGGER;
    }
}
