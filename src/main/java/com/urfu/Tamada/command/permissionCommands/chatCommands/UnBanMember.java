package com.urfu.Tamada.command.permissionCommands.chatCommands;

import com.urfu.Tamada.BanList;
import com.urfu.Tamada.Config;
import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.permissions.PermissionCommandWithMembers;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


public class UnBanMember extends PermissionCommandWithMembers {

    private final String help = "Убирает указанного пользователя из бан-листа на канале. Если у вас есть для этого права, конечно.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }


    @Override
    public void execute(GuildMessageReceivedEvent event){
        var id = CommandController
                    .getMemberFromEvent(event)
                    .getFirst()
                    .getIdLong();
        var guildId = event.getGuild().getIdLong();
        if (BanList.isInBanList(guildId, id))
            BanList.removeFromBanList(guildId, id);
    }
}
