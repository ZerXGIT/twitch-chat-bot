package de.zerx.core.console;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 24.12.2021 02:32
 */
public class ConsoleArgsParser {

    private String inputString = "";

    public ConsoleArgsParser(String str) {
        inputString = str;
    }

    public String getCommandString() {
        return inputString.split(" ")[0];
    }

    public String[] getCommandArgs() {
        try {
            return inputString.substring(getCommandString().length() + 1).split(" ");
        } catch (IndexOutOfBoundsException e) {
            return new String[0];
        }
    }
}
