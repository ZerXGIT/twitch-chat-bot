package eu.kenexar.commands.runnable;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.helix.domain.CreateClipList;
import eu.kenexar.commands.CommandExecutor;
import eu.kenexar.commands.CommandProperties;

import java.util.ArrayList;

@CommandProperties(
        trigger = "clip",
        restricted = true
)
public class CreateClipCommand implements CommandExecutor {

    @Override
    public void onCommand(ChannelMessageEvent event, String authToken, String[] args, TwitchClient twitchClient) {
        var channelId = event.getChannel().getId();
        var channelName = event.getChannel().getName();
        var chat = twitchClient.getChat();
        var userName = event.getUser().getName();

        try {
            var clipData = twitchClient.getHelix().createClip(authToken, channelId, true).execute();

            var firstClip = clipData.getData().get(0);

            chat.sendMessage(channelName,"@" + userName + " -> Der Clip wurde erfolgreich erstellt! " + "https://clips.twitch.tv/" + firstClip.getId());
        } catch(Exception e) {
            chat.sendMessage(channelName,"@" + userName + " -> Fehler: es ist ein Fehler aufgetreten!");
        }

    }
}
