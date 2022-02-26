package eu.kenexar.commands.runnable;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import eu.kenexar.commands.CommandExecutor;
import eu.kenexar.commands.CommandProperties;
import eu.kenexar.userhandler.UserObject;

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

    public void onCommand(ChannelMessageEvent event, String[] args, TwitchClient twitchClient) {
        UserObject user = new UserObject();
        String channelName = event.getChannel().getName();

        String inputString = null;

        try {
            inputString = event.getMessage().substring(7);

            if (inputString.length() > MAX_LENGTH) {
                event.getTwitchChat().sendMessage(channelName, "@" + event.getUser().getName() + " -> Du hast die maximale Anzahl von Zeichen überschritten!");
                return;
            }

            twitchClient.getKraken().updateTitle(user.getToken(channelName), event.getChannel().getId(), inputString).execute();
            event.getTwitchChat().sendMessage(channelName, "@" + event.getUser().getName() + " -> Der Titel wurde auf: \"" + inputString + "\" geändert!");
        } catch (IndexOutOfBoundsException exception) {
            event.getTwitchChat().sendMessage(channelName, "@" + event.getUser().getName() + " -> Der Title ist momentan: \n" + "TODO: GET TITLE" + "\"");
            //event.getTwitchChat().sendMessage(channelName, "@" + event.getUser().getName() + " -> Es wurde kein Title angegeben!");
        }
    }
}

