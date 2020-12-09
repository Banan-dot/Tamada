package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.permissions.PermissionCommand;
import com.urfu.Tamada.command.permissions.PermissionCommandWithMembers;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class AddNewEmoji extends PermissionCommand {
    public final String help = "Добавляет эмоции с картинки в сообщении с командой.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var mess = event.getMessage().getContentRaw().split(" ");
        if (mess.length == 1) {
            Sender.send(event, "Напишите название смайлика, пожалуйста.");
            return;
        }
        Message.Attachment photo;
        try {
            photo = event
                    .getMessage()
                    .getAttachments()
                    .get(0);
        } catch (IndexOutOfBoundsException e) {
            Sender.send(event, "Отправьте фото, пожалуйста");
            return;
        }
        var name = mess[1];
        var icon = photo.retrieveAsIcon().join();
        try {
            var t = event.getGuild().getEmotesByName(name, false).get(0);
            Sender.send(event, "Смайл с таким именем уже существует");
        } catch (IndexOutOfBoundsException e) {
            event.getGuild().createEmote(name, icon).queue();
            Sender.send(event, "Смайл с именем " + name + " создан");
        }
    }
}
