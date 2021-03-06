package com.urfu.Tamada.command.permissionCommands.chatCommands;

import com.urfu.Tamada.BanList;
import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.CommandInformation;
import com.urfu.Tamada.command.permissions.PermissionCommandWithMembers;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

@CommandInformation(name = "ban", information = "Банит указанного участника. Если у вас есть для этого права, конечно.",
        detailedInformation = "[@MemberName]")
public class BanMember extends PermissionCommandWithMembers {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var pair = CommandController.getMemberFromEvent(event);
        var id = pair.getFirst().getIdLong();
        var guildId = event.getGuild().getIdLong();
        if (id == Objects.requireNonNull(event.getMember()).getIdLong()) {
            Sender.send(event, "Самобан? АХАХХАХАХА");
            return;
        }

        if (BanList.isInBanList(guildId, id))
            Sender.send(event, "Данный человек уже в бане");
        BanList.addToBanList(event.getGuild().getIdLong(), id);
    }
}
