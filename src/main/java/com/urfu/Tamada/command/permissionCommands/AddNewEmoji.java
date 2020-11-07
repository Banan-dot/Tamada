package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class AddNewEmoji extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var mess = event.getMessage().getContentRaw().split(" ");
        if (mess.length == 1) {
            event.getChannel().sendMessage("Напишите название смайлика, пожалуйста.").queue();
            return;
        }
        Message.Attachment photo;
        try {
            photo = event
                    .getMessage()
                    .getAttachments()
                    .get(0);
        } catch (IndexOutOfBoundsException e) {
            event.getChannel().sendMessage("Отправьте фото, пожалуйста").queue();
            return;
        }
        System.out.println("Name is " + mess[1]);
        var name = mess[1];
        var icon = photo.retrieveAsIcon().join();
        try {
            var t = event.getGuild().getEmotesByName(name, false).get(0);
            event.getChannel().sendMessage("Смайл с таким именем уже существует").queue();
        } catch (IndexOutOfBoundsException e) {
            event.getGuild().createEmote(name, icon).queue();
            event.getChannel().sendMessage("Смайл с именем " + name + " создан").queue();
        }
    }
}
