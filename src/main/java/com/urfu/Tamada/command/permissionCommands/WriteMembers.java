package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class WriteMembers extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var members = event.getGuild().getMembers();
        members.forEach(i -> event.getChannel().sendMessage(
                i + " " + i.getNickname() + " " + i.getEffectiveName()).queue());
    }
}