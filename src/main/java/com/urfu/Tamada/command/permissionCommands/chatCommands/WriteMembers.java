package com.urfu.Tamada.command.permissionCommands.chatCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class WriteMembers extends Command {

    private final String help = "Выводит всех пользователей на канле. Если у вас есть для этого права, конечно.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var members = event.getGuild().getMembers();
        members.forEach(i -> event.getChannel().sendMessage(
                i + " " + i.getNickname() + " " + i.getEffectiveName()).queue());
    }
}
