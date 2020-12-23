package com.urfu.Tamada.command.permissionCommands.chatCommands;

import com.urfu.Tamada.BanList;
import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


@CommandInformation(name = "bl", information = "Выдает бан-лист канала.")
public class ViewBanList extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var banList = BanList.getBanList();
        var guildId = event.getGuild().getIdLong();
        if (banList.size() == 0) {
            Sender.send(event, "Черный список пуст");
            return;
        }
        var result = new StringBuilder().append("Черный список:\n");
        for (var el : banList)
            if (el.getSecond() == guildId)
                result.append(event.getGuild().getMemberById(el.getFirst())).append("\n");
        Sender.send(event, result.toString());
    }
}
