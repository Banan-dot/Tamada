package com.urfu.Tamada.command.translator;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.permissions.PermissionCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

public class SwitchLanguage extends PermissionCommand {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws IOException, InterruptedException {
        var messages = event.getMessage().getContentRaw().split(" ");
        if (messages.length == 1)
            Sender.send(event, "Добавьте, пожалуйста, язык.");
        var language = messages[1];
        if (Translator.languages.containsKey(language))
            Sender.setLanguage(language);
        else {
            Sender.send(event, "Данный язык отсутствует");
            new ListLanguages().execute(event);
        }
    }

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, "Switch language");
    }
}
