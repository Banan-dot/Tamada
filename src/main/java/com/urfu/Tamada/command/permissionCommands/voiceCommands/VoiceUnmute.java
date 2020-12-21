package com.urfu.Tamada.command.permissionCommands.voiceCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.permissions.PermissionCommandWithMembers;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


public class VoiceUnmute extends PermissionCommandWithMembers {

    private final String help = "Анмьютит микрофон пользователя в канале в котором вы находитесь. Если у вас есть для этого права, конечно.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        new VoiceCommand().voiceCommand(event, false);
    }
}
