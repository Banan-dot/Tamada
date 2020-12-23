package com.urfu.Tamada.command;

import com.urfu.Tamada.command.zen.Subscriber;
import com.urfu.Tamada.vk.VK;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

import static com.urfu.Tamada.Sender.send;

@CommandInformation(
        name = "sub",
        information = "Подписаться на уведомление группы из вк",
        detailedInformation = "!sub [id], id < 0")
public class SubscriberCommand extends Command{
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException {
        var contentRaw = event.getMessage().getContentRaw();
        var grouId = contentRaw.split(" ")[1];
        if (Integer.parseInt(grouId) >= 0 || !isGroupId(grouId)){
            send(event, "Отправьте корректный id группы. id < 0");
            return;
        }

        if (Subscriber.isInSubs(event.getGuild().getIdLong(), Integer.parseInt(grouId))){
            send(event, "Вы подписаны уже на эту группу");
            return;
        }
        Subscriber.addToSubs(event.getGuild().getIdLong(), Integer.parseInt(grouId), 0);
        send(event, String.format("Группа с id = %s успешно добавлена", grouId));
    }

    private boolean isGroupId(String id){
        try{
            var groupId = Integer.parseInt(id);
            var vk = VK.getClient();
            var items = VK.getPostsById(groupId, 2).get(0);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
