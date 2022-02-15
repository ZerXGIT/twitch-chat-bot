package de.zerx.core.console;

import de.zerx.core.logger.ExceptionLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 23.12.2021 01:20
 */
public class ConsoleManager {

    private static List<ConsoleCommandExecutor> listeners;

    public ConsoleManager() {
        new Console();
        listeners = new ArrayList<>();
    }

    protected static void triggerEvents(String str) {
        var parser = new ConsoleArgsParser(str);

        for (ConsoleCommandExecutor listener : listeners) {
            try {
                listener.onCommand(parser.getCommandString(), parser.getCommandArgs());
            } catch (ArrayIndexOutOfBoundsException e) {
                ExceptionLogger.get().error(e.getMessage());
            }
        }
    }

    public void addListener(ConsoleCommandExecutor listener) {
        listeners.add(listener);
    }
}
