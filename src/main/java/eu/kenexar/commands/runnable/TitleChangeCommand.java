package eu.kenexar.commands.runnable;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.helix.domain.ChannelInformation;
import eu.kenexar.commands.CommandExecutor;
import eu.kenexar.commands.CommandProperties;
import eu.kenexar.userhandler.UserObject;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: Twitchbot(1)
 * This file is created at 19.05.2021 16:38
 */

@CommandProperties(
        trigger = "title",
        restricted = true
)
public class TitleChangeCommand implements CommandExecutor {

    private static final short MAX_LENGTH = 141;

    public void onCommand(ChannelMessageEvent event, String authToken, String[] args, TwitchClient twitchClient) {
        var channelName = event.getChannel().getName();
        var userName = event.getUser().getName();
        var argsString = StringUtils.join(args, " ");

        if(args.length < 1) {
            event.getTwitchChat().sendMessage(channelName, "@" + userName + " -> Der Title ist momentan: \n" + "TODO: GET TITLE" + "\"");
            return;
        }

        if (argsString.length() > MAX_LENGTH) {
            event.getTwitchChat().sendMessage(channelName, "@" + userName + " -> Du hast die maximale Anzahl von Zeichen überschritten!");
            return;
        }

        twitchClient.getHelix().updateChannelInformation(authToken, event.getChannel().getId(), new ChannelInformation().withTitle(argsString)).execute();
        event.getTwitchChat().sendMessage(channelName, "@" + userName + " -> Der Titel wurde auf: \"" + argsString + "\" geändert!");
    }
}

