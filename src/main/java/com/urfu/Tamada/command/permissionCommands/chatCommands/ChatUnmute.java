package com.urfu.Tamada.command.permissionCommands.chatCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class ChatUnmute extends Command {

    private final String help = "Анмьютит чат для указанного пользователя. Если у вас есть для этого права, конечно.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var pair = CommandController.getMemberFromEvent(event);
        var member = pair.getFirst();
        if (Objects.requireNonNull(member).hasPermission(Permission.MESSAGE_WRITE)) {
            event
                .getGuild()
                .getTextChannels()
                .forEach(i -> i.getManager()
                        .getChannel()
                        .putPermissionOverride(member)
                        .setAllow(Permission.MESSAGE_WRITE)
                        .queue());
            Sender.send(event, String.format("Пользователь %s размьючен", member.getNickname()));
        }
    }
}
