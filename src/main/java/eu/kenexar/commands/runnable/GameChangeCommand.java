package eu.kenexar.commands.runnable;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.helix.domain.ChannelInformation;
import com.github.twitch4j.helix.domain.Game;
import eu.kenexar.commands.CommandExecutor;
import eu.kenexar.commands.CommandProperties;
import eu.kenexar.userhandler.UserObject;
import org.apache.commons.lang.StringUtils;

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
    public void onCommand(ChannelMessageEvent event, String authToken, String[] args, TwitchClient twitchClient) {
        var channelName = event.getChannel().getName();
        var userName = event.getUser().getName();
        var argsString = StringUtils.join(args, " ");

        if(args.length < 1) {
            event.getTwitchChat().sendMessage(channelName, "@" + userName + " -> Fehler: Du hast kein Game angegeben!");
            return;
        }

        List<Game> result = twitchClient.getHelix().searchCategories(authToken, argsString, 1, null).execute().getResults();

        if (result == null) {
            event.getTwitchChat().sendMessage(channelName, "@" + userName + " -> Fehler: Das Game konnte nicht gefunden werden.");
            return;
        }

        Game game = result.get(0);
        twitchClient.getHelix().updateChannelInformation(authToken, event.getChannel().getId(), new ChannelInformation().withGameId(game.getId())).execute();
        event.getTwitchChat().sendMessage(channelName, "@" + userName + " -> Das Game wurde auf: " + game.getName() + " gesetzt.");
    }

}