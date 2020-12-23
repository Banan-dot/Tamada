package com.urfu.Tamada.command.paste;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

@CommandInformation(name = "getPaste", information = "Выдает указанную пасту.")
public class GetPasteCommand extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException {
        var pasteCommandInformation = new PasteCommandInformation(event);
        var pasteDB = PasteCommandInformation.getPastesDB();
        var guildId = event.getGuild().getIdLong();
        var paste = pasteDB.getPaste(guildId, pasteCommandInformation.getPasteName());
        event
        .getChannel()
        .sendMessage("Паста по ключу" + pasteCommandInformation.getPasteName() + ":\n" + paste)
        .queue();
    }
}
