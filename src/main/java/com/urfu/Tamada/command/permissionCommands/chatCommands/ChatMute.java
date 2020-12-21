package com.urfu.Tamada.command.permissionCommands.chatCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.permissions.PermissionCommandWithMembers;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ChatMute extends PermissionCommandWithMembers {
    private final String help = "Мьютит чат для указанного пользователя. Если у вас есть для этого права, конечно.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var pair = CommandController.getMemberFromEvent(event);
        var member = pair.getFirst();
        event
                .getGuild()
                .getTextChannels()
                .forEach(i -> i.getManager()
                        .getChannel()
                        .putPermissionOverride(member)
                        .deny(Permission.MESSAGE_WRITE)
                        .queue());
        Sender.send(event, String.format("Пользователь %s замьючен", member.getNickname()));
    }
}
