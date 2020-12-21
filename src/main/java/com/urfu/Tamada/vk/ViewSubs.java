package com.urfu.Tamada.vk;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.zen.Subscriber;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

public class ViewSubs extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException, ClientException, ApiException {
        var listSubs = Subscriber.getSubsByGuildId(event.getGuild().getIdLong());
        if (listSubs.size() == 0){
            Sender.send(event, "Список подписок пуст");
            return;
        }
        Sender.send(event, "Список подписок:");
        var c = 0;
        var sb = new StringBuilder();
        for(var sub : listSubs)
             sb
             .append(++c)
             .append(". ")
             .append(getNameById(sub))
             .append(" ::: id = ")
             .append(sub)
             .append("\n");
        Sender.send(event, sb.toString());
    }


    public static String getNameById(Integer id) throws ClientException, ApiException {
        return VK
                .getClient()
                .groups()
                .getById(VK.getServiceActor())
                .groupId(String.valueOf(-id))
                .execute()
                .get(0)
                .getName();
    }
    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, "Показывает список подписок групп в вк");
    }
}
