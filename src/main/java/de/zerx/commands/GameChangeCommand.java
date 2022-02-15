package de.zerx.commands;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.helix.domain.ChannelInformation;
import com.github.twitch4j.helix.domain.Game;
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
 * This file is created at 22.05.2021 15:48
 */
public class GameChangeCommand {

    public GameChangeCommand(SimpleEventHandler simple) {
        simple.onEvent(ChannelMessageEvent.class, this::onChannelMessageEvent);
    }

    public void onChannelMessageEvent(ChannelMessageEvent event) {
        if (event.getMessage().startsWith("!game")) {
            var twitchClient = UserHandler.getTwitchClient();

            var channelName = event.getChannel().getName();

            var user = new UserObject();

            List<String> modList = new ArrayList<>();
            var moderatorList = twitchClient.getHelix().getModerators(user.getToken(channelName), event.getChannel().getId(), null, null).execute();

            moderatorList.getModerators().forEach(str -> modList.add(str.getUserName().toLowerCase(Locale.ROOT)));

            if (modList.contains(event.getUser().getName()) || event.getUser().getName().equals(channelName)) {

                try {
                    String inputSting = event.getMessage().substring(6);
                    List<Game> result = twitchClient.getHelix().searchCategories(user.getToken(channelName), inputSting, 1, null).execute().getResults();

                    if (result != null) {
                        Game game = result.get(0);
                        twitchClient.getHelix().updateChannelInformation(user.getToken(channelName), event.getChannel().getId(), new ChannelInformation().withGameId(game.getId())).execute();
                        event.getTwitchChat().sendMessage(channelName, "@" + event.getUser().getName() + " -> Das Game wurde auf: " + game.getName() + " gesetzt.");
                    } else {
                        event.getTwitchChat().sendMessage(channelName, "@" + event.getUser().getName() + " -> Fehler: Das Game konnte nicht gefunden werden.");

                    }
                } catch (Exception ex) {
                    event.getTwitchChat().sendMessage(channelName, "@" + event.getUser().getName() + " -> Fehler: Du hast kein Game angegeben!");
                }
            }
        }
    }
}