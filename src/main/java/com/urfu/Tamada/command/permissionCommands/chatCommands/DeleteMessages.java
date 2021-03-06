package com.urfu.Tamada.command.permissionCommands.chatCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.CommandInformation;
import com.urfu.Tamada.command.permissions.PermissionCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;


@CommandInformation(name = "delete", information = "Удаляет указанное количество сообщений на канале. Если у вас есть для этого права, конечно.",
        detailedInformation = "[Число] число от 1 до 50")
public class DeleteMessages extends PermissionCommand {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var arr = event.getMessage().getContentRaw().split(" ");
        if (arr.length == 1) {
            Sender.send(event, "Напишите, сколько сообщений нужно удалить");
            return;
        }
        var n = Integer.parseInt(arr[1]);
        if (n > 50 || n <= 0) {
            Sender.send(event, "Введите число от 1 до 50");
            return;
        }
        var member = Objects.requireNonNull(event.getMember()).getIdLong();
        var messages = event.getChannel().getHistory().retrievePast(100).complete();
        for (var i : messages) {
            if (n > 0) {
                i.delete().queue();
                n--;
            } else if (n == 0) {
                return;
            }
        }
    }
}
