package de.zerx.core.console;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 23.12.2021 01:21
 */
public interface ConsoleCommandExecutor {

    void onCommand(String cmd, String[] args);

}