package com.urfu.Tamada.command.translator;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

@CommandInformation(name = "ll", information = "Выводит текущий язык бота.")
public class ListLanguages extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException {
        Sender.send(event, "Список языков:");
        var sb = fillDictLanguages(new StringBuilder());
        Sender.send(event, sb.substring(0, sb.length() / 2));
        Sender.send(event, sb.substring(sb.length() / 2, sb.length() - 1));
    }

    public static String fillDictLanguages(StringBuilder sb) {
        for (var key : Translator.languages.keySet())
            sb.append(key)
                    .append(" ")
                    .append(Translator.languages.get(key))
                    .append("\n");
        return sb.toString();
    }
}
