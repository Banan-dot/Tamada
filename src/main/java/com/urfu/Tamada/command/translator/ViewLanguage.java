package com.urfu.Tamada.command.translator;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

public class ViewLanguage extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException {
        Sender.send(event, Sender.getLanguage() + " - " + Translator.languages.get(Sender.getLanguage()) + " язык");
    }

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {

    }
}
