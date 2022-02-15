package de.zerx.core.console.commands;

import de.zerx.core.console.ConsoleCommandExecutor;
import de.zerx.core.logger.MainLogger;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 23.12.2021 01:25
 */
public class ExitCommand implements ConsoleCommandExecutor {

    @Override
    public void onCommand(String cmd, String[] args) {
        if (cmd.equalsIgnoreCase("exit")) {
            MainLogger.get().info("Exiting...");
            System.exit(0);
        }
    }
}
