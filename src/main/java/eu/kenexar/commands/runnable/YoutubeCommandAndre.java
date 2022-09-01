package eu.kenexar.commands.runnable;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import eu.kenexar.commands.CommandExecutor;
import eu.kenexar.commands.CommandProperties;

@CommandProperties(
        trigger = "youtube",
        alias = {"yt"},
        channelOnly = "andredelive"
)
public class YoutubeCommandAndre implements CommandExecutor {

    @Override
    public void onCommand(ChannelMessageEvent event, String authToken, String[] args, TwitchClient twitchClient) {
        twitchClient.getChat().sendMessage(event.getChannel().getName(), "Mein Youtube Kanal: https://www.youtube.com/channel/UCq9RJnQpD4uhhvl1ssIFvNg");
    }
}
