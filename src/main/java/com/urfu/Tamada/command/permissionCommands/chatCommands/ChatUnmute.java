package com.urfu.Tamada.command.permissionCommands.chatCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.CommandInformation;
import com.urfu.Tamada.command.permissions.PermissionCommandWithMembers;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

@CommandInformation(name = "unmute_t", information = "Анмьютит чат для указанного пользователя. Если у вас есть для этого права, конечно.",
        detailedInformation = "[@MemberName]")
public class ChatUnmute extends PermissionCommandWithMembers {
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
                        .setAllow(Permission.MESSAGE_WRITE)
                        .queue());
        Sender.send(event, String.format("Пользователь %s размьючен", member.getNickname()));
    }
}
