package com.urfu.Tamada.Command.permissionCommands;

import com.urfu.Tamada.Command.Command;
import com.urfu.Tamada.Command.CommandData;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


public class VoiceMute extends Command {
    @Override
    public void execute(CommandData commandData, GuildMessageReceivedEvent event) {
        new VoiceCommand().voiceCommand(event, true);
    }
}
