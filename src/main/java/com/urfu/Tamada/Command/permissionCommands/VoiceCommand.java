package com.urfu.Tamada.Command.permissionCommands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class VoiceCommand {
    public void voiceCommand(GuildMessageReceivedEvent event, boolean muteOrNot, Integer index) {
        var guild = event.getJDA().getGuildById(Objects.requireNonNull(event.getMember()).getGuild().getId());
        var mess = event.getMessage().getContentRaw();
        assert guild != null;
        var member = guild.getMemberById(Long.parseLong(mess.substring(index, mess.length() - 1)));
        assert member != null;
        System.out.println(member.getNickname());
        guild.mute(member, muteOrNot).queue();
    }
}
