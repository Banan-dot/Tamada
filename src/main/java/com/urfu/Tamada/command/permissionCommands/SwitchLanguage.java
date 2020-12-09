package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.Translator;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

public class SwitchLanguage extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException {
        var messages = event.getMessage().getContentRaw().split(" ");
        if (messages.length == 1)
            Sender.send(event, "Добавьте, пожалуйста, язык.");
        var language = messages[1];
        if (Translator.languages.containsKey(language))
            Sender.language = language;
        else{
            var sb = new StringBuilder();
            Sender.send(event, "Данный язык отсутствует");
            for(var key : Translator.languages.keySet())
                sb.append(key)
                  .append(" ")
                  .append(Translator.languages.get(key))
                  .append("\n");
            Sender.send(event, sb.toString());
        }
    }

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, "Switch language");
    }
}
