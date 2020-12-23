package com.urfu.Tamada.command.permissionCommands.voiceCommands;

import com.urfu.Tamada.command.CommandInformation;
import com.urfu.Tamada.command.permissions.PermissionCommandWithMembers;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


@CommandInformation(name = "mute", information = "Мьютит микрофон пользователя в канале в котором вы находитесь. Если у вас есть для этого права, конечно.",
        detailedInformation = "[@MemberName]")
public class VoiceMute extends PermissionCommandWithMembers {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        new VoiceCommand().voiceCommand(event, true);
    }
}
