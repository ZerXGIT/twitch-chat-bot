package eu.kenexar.commands.runnable;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.helix.domain.ChannelInformation;
import com.github.twitch4j.helix.domain.Game;
import eu.kenexar.commands.CommandExecutor;
import eu.kenexar.commands.CommandProperties;
import eu.kenexar.userhandler.UserObject;

import java.util.List;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: Twitchbot(1)
 * This file is created at 22.05.2021 15:48
 */

@CommandProperties(
        trigger = "game",
        restricted = true
)
public class GameChangeCommand implements CommandExecutor {

    @Override
    public void onCommand(ChannelMessageEvent event, String[] args, TwitchClient twitchClient) {
        var channelName = event.getChannel().getName();
        var user = new UserObject();

        String inputSting = null;

        try {
            inputSting = event.getMessage().substring(6);
            List<Game> result = twitchClient.getHelix().searchCategories(user.getToken(channelName), inputSting, 1, null).execute().getResults();

            if (result != null) {
                Game game = result.get(0);
                twitchClient.getHelix().updateChannelInformation(user.getToken(channelName), event.getChannel().getId(), new ChannelInformation().withGameId(game.getId())).execute();
                event.getTwitchChat().sendMessage(channelName, "@" + event.getUser().getName() + " -> Das Game wurde auf: " + game.getName() + " gesetzt.");
            } else
                event.getTwitchChat().sendMessage(channelName, "@" + event.getUser().getName() + " -> Fehler: Das Game konnte nicht gefunden werden.");
        } catch (Exception ex) {
            event.getTwitchChat().sendMessage(channelName, "@" + event.getUser().getName() + " -> Fehler: Du hast kein Game angegeben oder es wurde nicht gefunden!");
        }
    }
}