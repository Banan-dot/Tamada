package com.urfu.Tamada.vk;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import com.urfu.Tamada.command.zen.Subscriber;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

import static com.urfu.Tamada.Sender.*;

@CommandInformation(name = "vs", information = "Выдает список подписок данного канала(групп вк)",
        detailedInformation = "!vs")
public class ViewSubs extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException, ClientException, ApiException {
        var listSubs = Subscriber.getSubsByGuildId(event.getGuild().getIdLong());
        if (listSubs.size() == 0){
            send(event, "Список подписок пуст");
            return;
        }
        send(event, "Список подписок:");
        var c = 0;
        var sb = new StringBuilder();
        for(var sub : listSubs.keySet())
             sb
             .append(++c)
             .append(". ")
             .append(VK.getNameById(sub))
             .append(" ::: id = ")
             .append(sub)
             .append("\n");
        send(event, sb.toString());
    }
}
