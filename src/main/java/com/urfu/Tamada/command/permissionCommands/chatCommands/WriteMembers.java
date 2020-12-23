package com.urfu.Tamada.command.permissionCommands.chatCommands;

import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

@CommandInformation(name = "mm", information = "Выводит всех пользователей на канале. Если у вас есть для этого права, конечно.")
public class WriteMembers extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var members = event.getGuild().getMembers();
        members.forEach(i -> event.getChannel().sendMessage(
                i + " " + i.getNickname() + " " + i.getEffectiveName()).queue());
    }
}
