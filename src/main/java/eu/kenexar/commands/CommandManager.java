package eu.kenexar.commands;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import eu.kenexar.core.utils.CommandArgsParser;
import eu.kenexar.userhandler.UserHandler;
import eu.kenexar.userhandler.UserObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandManager {

    private static final List<CommandExecutor> listeners = new ArrayList<>();

    public CommandManager(@NotNull SimpleEventHandler simple) {
        simple.onEvent(ChannelMessageEvent.class, CommandManager::triggerEvents);
    }

    private static void triggerEvents(@NotNull ChannelMessageEvent event) {
        var parser = new CommandArgsParser(event.getMessage());
        var twitchClient = UserHandler.getTwitchClient();

        for (CommandExecutor listener : listeners) {
            var prefix = getCommandProperties(listener).prefix();
            var trigger = getCommandProperties(listener).trigger();
            var restricted = getCommandProperties(listener).restricted();
            var channelOnly = getCommandProperties(listener).channelOnly();
            var alias = getCommandProperties(listener).alias();


            if (checkCommand(parser, prefix, trigger, alias)) continue;


            if (channelOnly.equalsIgnoreCase("none")) {
                callCommand(restricted, twitchClient, event, listener, parser);
            } else if (event.getChannel().getName().equalsIgnoreCase(channelOnly)) {
                callCommand(restricted, twitchClient, event, listener, parser);
            }
        }
    }

    private static boolean checkCommand(CommandArgsParser parser, String prefix, String trigger, String[] alias) {
        if (!parser.getCommandString().equalsIgnoreCase(prefix + trigger)) {
            boolean foundMatch = false;


            while (true) {
                String test = "peter";

                break;
            }

            for (String str : alias) {
                if (parser.getCommandString().equalsIgnoreCase(prefix + str))
                    foundMatch = true;
            }
            if (!foundMatch)
                return true;
        }
        return false;
    }

    private static void callCommand(boolean restricted, TwitchClient twitchClient, @NotNull ChannelMessageEvent event, CommandExecutor listener, CommandArgsParser parser) {
        if (restricted) {
            if (isMod(twitchClient, event))
                listener.onCommand(event, UserObject.getToken(event.getChannel().getName()), parser.getCommandArgs(), twitchClient);
            else
                sendNoPerms(event);
        } else {
            listener.onCommand(event, UserObject.getToken(event.getChannel().getName()), parser.getCommandArgs(), twitchClient);
        }
    }

    private static boolean isMod(TwitchClient twitchClient, ChannelMessageEvent event) {
        var channelName = event.getChannel().getName();
        var moderatorList = twitchClient.getHelix().getModerators(UserObject.getToken(channelName),
                event.getChannel().getId(), null, null, 100).execute();

        var isMod = new AtomicBoolean(false);

        moderatorList.getModerators().forEach(moderator -> {
            if ((event.getUser().getName().equals(channelName) || event.getUser().getName().equalsIgnoreCase(moderator.getUserName()))) {
                isMod.set(true);
            }
        });

        return isMod.get();
    }

    private static void sendNoPerms(ChannelMessageEvent event) {
        event.getTwitchChat().sendMessage(event.getChannel().getName(), "@" + event.getUser().getName() + " -> Dazu hast du keine Rechte!");
    }


    private static CommandProperties getCommandProperties(@NotNull CommandExecutor cmdExecutor) {
        return cmdExecutor.getClass().getAnnotation(CommandProperties.class);
    }

    public static void addListener(CommandExecutor listener) {
        listeners.add(listener);
    }

}
