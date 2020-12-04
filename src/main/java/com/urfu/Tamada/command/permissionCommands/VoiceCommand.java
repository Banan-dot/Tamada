package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class VoiceCommand {
    public void voiceCommand(GuildMessageReceivedEvent event, boolean muteOrNot) {
        var pair = CommandController.getMemberFromEvent(event);
        var member = pair.getMember();
        var guild = pair.getGuild();
        System.out.println(member.getNickname());
        var mess = event.getMessage();
        if (member.getId().equals(Objects.requireNonNull(mess.getMember()).getId())
            || member.hasPermission(Permission.ADMINISTRATOR)){
            event.getChannel().sendMessage("Дурак что-ли?").queue();
        }
        else
            guild.mute(member, muteOrNot).queue();
    }
}
