package com.urfu.Tamada.command.paste;

import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

@CommandInformation(name = "viewPastes", information = "Выводит названия всех паст на сервере.")
public class GetAllPastesCommand extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException {
        var pasteDB = PasteCommandInformation.getPastesDB();
        var guildId = event.getGuild().getIdLong();
        var pastesNames = pasteDB.getPastesName(guildId);
        var names = new StringBuilder();
        var c = 0;
        for (var pasteName : pastesNames) {
            names.append(++c).append(". ").append(pasteName).append("\n");
        }
        event.getChannel().sendMessage(names.toString()).queue();
    }
}
