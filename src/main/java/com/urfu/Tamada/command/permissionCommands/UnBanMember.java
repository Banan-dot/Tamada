package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Config;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


public class UnBanMember extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event){
        var pair = CommandController.getMemberFromEvent(event);
        var id = pair.getMember().getIdLong();
        var bl = Config.getBanList();
        if (bl.isInBanList(id)){
            Config.getBanList().removeFromBanList(id);
        }
    }
}
