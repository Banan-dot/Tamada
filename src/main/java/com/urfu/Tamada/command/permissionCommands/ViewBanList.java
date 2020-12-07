package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ViewBanList extends Command {
    private final String help = "Выдает бан-лист канала. Если у вас есть для этого права, конечно.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }


    @Override
    public void execute(GuildMessageReceivedEvent event){

    }
}
