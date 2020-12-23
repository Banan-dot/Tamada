package com.urfu.Tamada.command.paste;

import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

@CommandInformation(name = "rmPaste", information = "Удаляет пасту.", detailedInformation = "[\"Paste name\"]")
public class RemovePasteCommand extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var pasteCommandInformation = new PasteCommandInformation(event);
        var pasteDB = PasteCommandInformation.getPastesDB();
        var guildId = event.getGuild().getIdLong();
        pasteDB.removePaste(guildId, pasteCommandInformation.getPasteName());
        //
    }
}
