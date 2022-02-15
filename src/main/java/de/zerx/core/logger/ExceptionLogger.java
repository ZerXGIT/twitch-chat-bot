package de.zerx.core.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 23.12.2021 18:16
 */
public class ExceptionLogger {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ExceptionLogger.class);

    public static Logger get() {
        return LOGGER;
    }
}
