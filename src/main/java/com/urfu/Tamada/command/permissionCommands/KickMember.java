package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.CommandInformation;
import com.urfu.Tamada.command.permissions.PermissionCommandWithMembers;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;


@CommandInformation(name = "kick", information = "Кикает указанного мембера. Если у вас есть для этого права, конечно.",
        detailedInformation = "[@MemberName]")
public class KickMember extends PermissionCommandWithMembers {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var pair = CommandController.getMemberFromEvent(event);
        var member = pair.getFirst();
        Objects.requireNonNull(member)
                .kick()
                .queue();
        Sender.send(event,
                "Пользователь" + member.getEffectiveName() + "кикнут.");
    }
}
