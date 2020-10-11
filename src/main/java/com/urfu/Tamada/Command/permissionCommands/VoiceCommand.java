package com.urfu.Tamada.Command.permissionCommands;

import com.urfu.Tamada.events.JokeEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class VoiceCommand {
    // unmute, mute, rename, ban, kick, giveRole, chatMute, chatUnmute
    public void voiceCommand(GuildMessageReceivedEvent event, boolean muteOrNot) {
        var pair = JokeEvent.getMemberFromEvent(event);
        var member = pair.getMember();
        var guild = pair.getGuild();
        System.out.println(member.getNickname());
        var mess = event.getMessage();
        if (member.getId().equals(Objects.requireNonNull(mess.getMember()).getId())){
            event.getChannel().sendMessage("Дурак что-ли?").queue();
        }
        else
            guild.mute(member, muteOrNot).queue();
    }
}
