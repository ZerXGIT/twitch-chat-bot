package eu.kenexar.core.utils;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 24.12.2021 02:32
 */
public class CommandArgsParser {

    private String inputString = "";

    private static String prefix = "";

    public CommandArgsParser(String str) {
        inputString = str;
    }

    public CommandArgsParser(String str, String prefix) {
        inputString = str;
        CommandArgsParser.prefix = prefix;
    }

    public String getCommandString() {
        var splitString = inputString.split(" ")[0];
        if (prefix == null) {
            if (splitString.startsWith(prefix))
                return splitString.substring(1);
        }

        return splitString;
    }

    public String[] getCommandArgs() {
        try {
            return inputString.substring(getCommandString().length() + 1).split(" ");
        } catch (IndexOutOfBoundsException e) {
            return new String[0];
        }
    }
}
