package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.CommandInformation;
import com.urfu.Tamada.command.Translator;
import com.urfu.Tamada.command.permissions.PermissionCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;


@CommandInformation(name = "sl", information = "Switch language.",
        detailedInformation = "[language]")
public class SwitchLanguage extends PermissionCommand {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws IOException {
        var messages = event.getMessage().getContentRaw().split(" ");
        if (messages.length == 1)
            Sender.send(event, "Добавьте, пожалуйста, язык.");
        var language = messages[1];
        if (Translator.languages.containsKey(language))
            Sender.setLanguage(language);
        else {
            var sb = new StringBuilder();
            Sender.send(event, "Данный язык отсутствует");
            for (var key : Translator.languages.keySet())
                sb.append(key)
                        .append(" ")
                        .append(Translator.languages.get(key))
                        .append("\n");
            Sender.send(event, sb.substring(0, sb.length() / 2));
            Sender.send(event, sb.substring(sb.length() / 2, sb.length() - 1));
        }
    }
}
