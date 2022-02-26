package eu.kenexar.core.logger;

import eu.kenexar.core.Main;
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

    public static Logger get() {
        return LOGGER;
    }
}
