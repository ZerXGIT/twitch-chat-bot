package de.zerx.commands;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.helix.domain.ModeratorList;
import de.zerx.userhandler.UserHandler;
import de.zerx.userhandler.UserObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: Twitchbot(1)
 * This file is created at 19.05.2021 16:38
 */

public class TitleChangeCommand {

    private static final short MAX_LENGTH = 141;
    private final TwitchClient twitchClient = UserHandler.getTwitchClient();

    public TitleChangeCommand(SimpleEventHandler simple) {
        simple.onEvent(ChannelMessageEvent.class, this::onChannelMessageEvent);
    }

    public void onChannelMessageEvent(ChannelMessageEvent event) {
        if (event.getMessage().startsWith("!title")) {

            UserObject User = new UserObject();
            String ChannelName = event.getChannel().getName();

            ModeratorList moderatorList = twitchClient.getHelix().getModerators(User.getToken(ChannelName), event.getChannel().getId(), null, null).execute();

            moderatorList.getModerators().forEach(str -> {
               if (!(event.getUser().getName().equals(ChannelName) || str.getUserName() == event.getUser().getName())) {
                   return;
               }
            });

            try {
                String inputString = event.getMessage().substring(7);

                if (inputString.length() > MAX_LENGTH) {
                    event.getTwitchChat().sendMessage(ChannelName, "@" + event.getUser().getName() + " -> Du hast die maximale Anzahl von Zeichen überschritten!");
                    return;
                }


                twitchClient.getKraken().updateTitle(User.getToken(ChannelName), event.getChannel().getId(), inputString).execute();
                event.getTwitchChat().sendMessage(ChannelName, "@" + event.getUser().getName() + " -> Der Titel wurde auf: \"" + inputString + "\" geändert!");
            } catch (IndexOutOfBoundsException exception) {


                // if(inputeString == null) event.getTwitchChat().sendMessage(ChannelName, "@" + event.getUser().getName() + " -> Es wurde kein Title angegeben!");


                //  event.getTwitchChat().sendMessage(ChannelName, "@" + event.getUser().getName() + " -> Der Title ist momentan: \n" + currentTitle + "\"");
            }
        }
    }
/*
    private boolean isAllowed(String userName, String channelName) {
        List<String> ModeratorNameList = new ArrayList<>();
        ModeratorList moderatorList = twitchClient.getHelix().getModerators(User.getToken(ChannelName), event.getChannel().getId(), null, null).execute();

        moderatorList.getModerators().forEach(str -> ModeratorNameList.add(str.getUserName().toLowerCase(Locale.ROOT)));
    }*/
}