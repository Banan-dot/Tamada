package com.urfu.Tamada.command.zen;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.database.subscribes.SubscribesDB;
import com.urfu.Tamada.vk.VK;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;
import java.util.HashSet;


public class Subscriber extends Command {
    public static SubscribesDB subsBD;
    
    public Subscriber(){
        subsBD = new SubscribesDB();
    }

    public static void removeFromSubs(long guildId, int groupId) {
        Subscriber.subsBD.removeGroupId(guildId, groupId);
    }

    public static void addToSubs(long guildId, int groupId) {
        Subscriber.subsBD.addGroupId(guildId, groupId);
    }

    public static HashSet<Integer> getSubsByGuildId(long guildId){
        return Subscriber.subsBD.getAllSubsByGuildId(guildId);
    }

    public static boolean isInSubs(long guildId, int groupId) {
        return Subscriber.subsBD.isInBanList(guildId, groupId);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException {
        var contentRaw = event.getMessage().getContentRaw();
        var grouId = contentRaw.split(" ")[1];
        if (Integer.parseInt(grouId) >= 0 && !isGroupId(grouId)){
            Sender.send(event, "Отправьте корректный id группы. id < 0");
            return;
        }
        Subscriber.addToSubs(event.getGuild().getIdLong(), Integer.parseInt(grouId));
        Sender.send(event, String.format("Группа с id = %s успешно добавлена", grouId));
    }

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, "Подписка в дзен");
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
