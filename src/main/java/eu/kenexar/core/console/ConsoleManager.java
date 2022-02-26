package eu.kenexar.core.console;

import eu.kenexar.core.logger.ExceptionLogger;
import eu.kenexar.core.utils.CommandArgsParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 23.12.2021 01:20
 */
public class ConsoleManager {

    private static final List<ConsoleCommandExecutor> listeners = new ArrayList<>();

    static {
        new Console();
    }

    protected static void triggerEvents(String str) {
        var parser = new CommandArgsParser(str);

        for (ConsoleCommandExecutor listener : listeners) {
            try {
                listener.onCommand(parser.getCommandString(), parser.getCommandArgs());
            } catch (ArrayIndexOutOfBoundsException e) {
                ExceptionLogger.get().error(e.getMessage());
            }
        }
    }

    public static void addListener(ConsoleCommandExecutor listener) {
        listeners.add(listener);
    }
}
