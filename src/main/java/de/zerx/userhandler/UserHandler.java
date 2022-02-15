package de.zerx.userhandler;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import de.zerx.commands.GameChangeCommand;
import de.zerx.commands.TitleChangeCommand;
import de.zerx.constants.LoginData;
import de.zerx.core.logger.MainLogger;
import feign.Logger;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: Twitchbot(1)
 * This file is created at 19.03.2021 16:00
 */

public class UserHandler {

    private static TwitchClient TwitchClient;

    public UserHandler() {
    }

    public static TwitchClient getTwitchClient() {
        return TwitchClient;
    }

    public void init() {
        TwitchClient = TwitchClientBuilder.builder()
                .withChatAccount(LoginData.CLIENT_CREDENTIAL)
                .withClientId(LoginData.CLIENT_ID)
                .withClientSecret(LoginData.CLIENT_SECRET)
                .withEnableHelix(true)
                .withEnableKraken(true)
                .withEnableChat(true)
                .withDefaultEventHandler(SimpleEventHandler.class)
                .withFeignLogLevel(Logger.Level.FULL)
                .build();


        registerEvents();
        registerStreamer();
    }

    private void registerEvents() {
        var simpleEventHandler = TwitchClient.getEventManager().getEventHandler(SimpleEventHandler.class);

        // Commands
        new TitleChangeCommand(simpleEventHandler);
        new GameChangeCommand(simpleEventHandler);
    }

    private void registerStreamer() {
        for (UserObject user : UserObject.getUsers()) {
            try {
                if (!(TwitchClient.getChat().isChannelJoined(user.getName()))) {
                    MainLogger.get().info("Joined Channel: " + user.getName());
                    TwitchClient.getChat().joinChannel(user.getName());
                }
            } catch (Throwable e) {
                System.out.println("ERROR: \"" + user.getName() + "\" token may be invalid!");
            }
        }
    }
}