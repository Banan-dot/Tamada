package com.urfu.Tamada.command.paste;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import com.urfu.Tamada.command.database.PastTable;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


@CommandInformation(name = "paste", information = "Добавляет пасту.",
        detailedInformation = "[\"Paste name\"] [Paste text]")
public class AddPasteCommand extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var pasteCommandInformation = new PasteCommandInformation(event);
        var pasteDB = PasteCommandInformation.getPastesDB();
        var guildId = event.getGuild().getIdLong();
        var pasteName = pasteCommandInformation.getPasteName();
        if (inDataBase(guildId, pasteName, pasteDB)) {
            Sender.send(event, "Паста с таким названием уже существует. Придумайте другое название или удалите уже сущесвующую и добавьте под этим названием с новым текстом");
            return;
        }
        pasteDB.makePaste(guildId, pasteCommandInformation.getPasteName(), pasteCommandInformation.getPasteText());
    }

    private boolean inDataBase(Long id, String pasteName, PastTable pasteDB) {
        return pasteDB.getPastesName(id).contains(pasteName.replace("\"", ""));
    }
}
