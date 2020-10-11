package com.urfu.Tamada.events;

import com.urfu.Tamada.Command.CommandData;
import com.urfu.Tamada.Command.Pair;
import com.urfu.Tamada.Command.permissionCommands.*;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import com.urfu.Tamada.Command.JokeCommand;

import javax.annotation.Nonnull;
import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Objects;


public class JokeEvent extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        System.out.println(event.getMessage().getContentRaw());
        var message = event.getMessage().getContentRaw();
        var command = message.split(" ")[0];
        if (command.equalsIgnoreCase("анек")) {
            new JokeCommand().execute(new CommandData("Anek", new String[]{"2"}), event);
        } else if (command.equalsIgnoreCase("mute")) {
            new VoiceMute().execute(new CommandData("Mute", new String[]{""}), event);
        } else if (command.equalsIgnoreCase("unmute")) {
            new VoiceUnmute().execute(new CommandData("unmute", new String[]{""}), event);
        } else if (command.equalsIgnoreCase("rename")){
            new Rename().execute(new CommandData("rename", new String[]{""}), event);
        } else if (command.equalsIgnoreCase("kick")){
            new KickMember().execute(new CommandData("kick", new String[]{""}), event);
        }
    }

    public static Pair getMemberFromEvent(GuildMessageReceivedEvent event){
        var guild = event.getJDA().getGuildById(Objects.requireNonNull(event.getMember()).getGuild().getId());
        var mess = event.getMessage().getContentRaw();
        assert guild != null;
        var id = Long.parseLong(new GetIdFromString().getIdFromString(mess));
        var member = guild.getMemberById(id);
        assert member != null;
        return new Pair(member, guild);
    }
}