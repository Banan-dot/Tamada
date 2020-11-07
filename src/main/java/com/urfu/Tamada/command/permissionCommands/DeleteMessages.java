package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;
import java.util.Objects;

public class DeleteMessages extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event){
        var arr = event.getMessage().getContentRaw().split(" ");
        if (arr.length == 1){
            event.getChannel().sendMessage("Напишите, сколько сообщений нужно удалить").queue();
            return;
        }
        var n = Integer.parseInt(arr[1]);
        if (n > 50 || n <= 0) {
            event.getChannel().sendMessage("Введите число от 1 до 50").queue();
            return;
        }
        var member = Objects.requireNonNull(event.getMember()).getIdLong();
        var messages = event.getChannel().getHistory().retrievePast(100).complete();
        for(var i:messages){
            if (Objects.requireNonNull(i.getMember()).getIdLong() == member && n > 0){
                i.delete().queue();
                System.out.println("No");
                n--;
            }
            else if(n == 0){
                return;
            }
        }
    }
}
