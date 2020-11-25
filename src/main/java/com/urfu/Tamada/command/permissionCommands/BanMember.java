package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.BanList;
import com.urfu.Tamada.Config;
import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;
import java.util.Objects;

public class BanMember extends Command {

    @Override
    public void execute(GuildMessageReceivedEvent event){
        var bl = Config.getBanList();
        var pair = CommandController.getMemberFromEvent(event);
        var id = pair.getMember().getIdLong();
        if (id == Objects.requireNonNull(event.getMember()).getIdLong()){
            Sender.send(event, "Самобан? АХАХХАХАХА");
            return;
        }
        if (bl.isInBanList(id)){
            Sender.send(event, "Данный человек уже в бане");
        }
        else{
            if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)){
                if (!pair.getMember().hasPermission(Permission.ADMINISTRATOR))
                    Config.getBanList().addToBanList(event.getGuild().getIdLong(), id);
                else
                    Sender.send(event, "Админа нельзя забанить");
            }
            else
                Sender.send(event, "Не делай так, пожалуйста!");
        }
    }
}
