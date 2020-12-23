package com.urfu.Tamada.command.zen;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import com.urfu.Tamada.vk.VK;
import com.urfu.Tamada.vk.ViewSubs;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

@CommandInformation(
        name = "unsub",
        information = "Отписка от группы с данным id",
        detailedInformation = "!unsub [id], id < 0")
public class Unsubscriber extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException, ClientException, ApiException {
        var contentRaw = event.getMessage().getContentRaw();
        var groupId = Integer.parseInt(contentRaw.split(" ")[1]);
        var guildId = event.getGuild().getIdLong();
        if (groupId >= 0){
            Sender.send(event, "Отправьте корректный id группы. id < 0");
            return;
        }

        if (Subscriber.isInSubs(guildId, groupId)){
            Subscriber.removeFromSubs(guildId, groupId);
            Sender.send(event, "Отписка от " + VK.getNameById(groupId));
        }
    }
}
