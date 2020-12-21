package com.urfu.Tamada.command.permissionCommands.voiceCommands;

import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class VoiceCommand {
    public void voiceCommand(GuildMessageReceivedEvent event, boolean muteOrNot) {
        var pair = CommandController.getMemberFromEvent(event);
        var member = pair.getFirst();
        var guild = pair.getSecond();
        guild.mute(member, muteOrNot).queue();
    }
}
