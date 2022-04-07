package eu.kenexar.commands;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

public interface CommandExecutor {

     void onCommand(ChannelMessageEvent event, String authToken, String[] args, TwitchClient twitchClient);

}
