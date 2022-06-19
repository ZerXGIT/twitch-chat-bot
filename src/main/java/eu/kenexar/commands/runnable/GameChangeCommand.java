package eu.kenexar.commands.runnable;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.helix.domain.ChannelInformation;
import eu.kenexar.commands.CommandExecutor;
import eu.kenexar.commands.CommandProperties;
import org.apache.commons.lang.StringUtils;

@CommandProperties(
        trigger = "game",
        restricted = true
)
public class GameChangeCommand implements CommandExecutor {

    @Override
    public void onCommand(ChannelMessageEvent event, String authToken, String[] args, TwitchClient twitchClient) {
        var channelName = event.getChannel().getName();
        var userName = event.getUser().getName();
        var chat = twitchClient.getChat();
        var argsString = StringUtils.join(args, " ");

        if (args.length < 1) {
            chat.sendMessage(channelName, "@" + userName + " -> Fehler: Du hast kein Game angegeben!");
            return;
        }

        var result = twitchClient.getHelix().searchCategories(authToken, argsString, 1, null).execute().getResults();

        if (result == null) {
            chat.sendMessage(channelName, "@" + userName + " -> Fehler: Das Game konnte nicht gefunden werden.");
            return;
        }

        var game = result.get(0);
        twitchClient.getHelix().updateChannelInformation(authToken, event.getChannel().getId(), new ChannelInformation().withGameId(game.getId())).execute();
        chat.sendMessage(channelName, "@" + userName + " -> Das Game wurde auf: " + game.getName() + " gesetzt.");
    }

}