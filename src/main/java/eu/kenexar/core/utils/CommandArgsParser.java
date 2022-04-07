package eu.kenexar.core.utils;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 24.12.2021 02:32
 */
public class CommandArgsParser {

    private final String inputString;

    public CommandArgsParser(String inputString) {
        this.inputString = inputString;
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
