package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.command.Command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


public class VoiceUnmute extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        new VoiceCommand().voiceCommand(event, false);
    }
}
