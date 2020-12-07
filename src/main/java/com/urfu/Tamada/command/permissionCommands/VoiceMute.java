package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


public class VoiceMute extends Command {

    private final String help = "Мьютит микрофон пользователя в канале в котором вы находитесь. Если у вас есть для этого права, конечно.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        new VoiceCommand().voiceCommand(event, true);
    }
}
