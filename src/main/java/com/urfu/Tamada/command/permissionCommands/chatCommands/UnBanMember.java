package com.urfu.Tamada.command.permissionCommands.chatCommands;

import com.urfu.Tamada.BanList;
import com.urfu.Tamada.command.CommandInformation;
import com.urfu.Tamada.command.permissions.PermissionCommandWithMembers;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


@CommandInformation(name = "unban", information = "Убирает указанного пользователя из бан-листа на канале. Если у вас есть для этого права, конечно.",
        detailedInformation = "[@MemberName]")
public class UnBanMember extends PermissionCommandWithMembers {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var id = CommandController
                .getMemberFromEvent(event)
                .getFirst()
                .getIdLong();
        var guildId = event.getGuild().getIdLong();
        if (BanList.isInBanList(guildId, id))
            BanList.removeFromBanList(guildId, id);
    }
}
