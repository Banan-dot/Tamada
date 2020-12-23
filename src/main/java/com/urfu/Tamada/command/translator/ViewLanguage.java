package com.urfu.Tamada.command.translator;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

@CommandInformation(name = "ll", information = "Выводит текущий язык бота.")
public class ViewLanguage extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException {
        Sender.send(event, Sender.getLanguage() + " - " + Translator.languages.get(Sender.getLanguage()) + " язык");
    }
}
