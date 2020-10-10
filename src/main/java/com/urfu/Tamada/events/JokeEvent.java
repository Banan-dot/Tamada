package com.urfu.Tamada.events;

import com.urfu.Tamada.Command.CommandData;
import com.urfu.Tamada.Command.VoiceMute;
import com.urfu.Tamada.Command.VoiceUnmute;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import com.urfu.Tamada.Command.JokeCommand;

import javax.annotation.Nonnull;
import java.util.Objects;


public class JokeEvent extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        System.out.println(event.getMessage().getContentRaw());
        var message = event.getMessage().getContentRaw();
        var command = message.split(" ")[0];
        if (command.equalsIgnoreCase("анек")) {
            new JokeCommand().execute(new CommandData("Anek", new String[]{"2"}), event);
        }
        else if (command.equalsIgnoreCase("mute")){
            new VoiceMute().execute(new CommandData("Mute", new String[]{"Людского во мне дохуя"}), event);
        }
        else if (command.equalsIgnoreCase("unmute")){
            new VoiceUnmute().execute(new CommandData("unmute", new String[]{"Людского во мне дохуя"}), event);
        }
    }
}

