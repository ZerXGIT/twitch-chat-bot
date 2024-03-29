package eu.kenexar.commands.runnable;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.helix.domain.ChannelInformation;
import eu.kenexar.commands.CommandExecutor;
import eu.kenexar.commands.CommandProperties;
import org.apache.commons.lang.StringUtils;

@CommandProperties(
        trigger = "title",
        restricted = true
)
public class TitleChangeCommand implements CommandExecutor {
    private static final short MAX_LENGTH = 141;

    public void onCommand(ChannelMessageEvent event, String authToken, String[] args, TwitchClient twitchClient) {
        var channelName = event.getChannel().getName();
        var chat = twitchClient.getChat();
        var userName = event.getUser().getName();
        var argsString = StringUtils.join(args, " ");

        if (args.length <= 0 || argsString == null) {
            chat.sendMessage(channelName, "@" + userName + " -> Der Title ist momentan: \n" + "TODO: GET TITLE" + "\"");
            return;
        }

        if (argsString.length() > MAX_LENGTH) {
            chat.sendMessage(channelName, "@" + userName + " -> Du hast die maximale Anzahl von Zeichen überschritten!");
            return;
        }

        twitchClient.getHelix().updateChannelInformation(authToken, event.getChannel().getId(), new ChannelInformation().withTitle(argsString)).execute();
        chat.sendMessage(channelName, "@" + userName + " -> Der Titel wurde auf: \"" + argsString + "\" geändert!");
    }
}