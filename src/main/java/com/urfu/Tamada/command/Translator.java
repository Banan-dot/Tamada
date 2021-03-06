package com.urfu.Tamada.command;

import com.darkprograms.speech.translator.GoogleTranslate;
import com.urfu.Tamada.Config;
import com.urfu.Tamada.Sender;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


@CommandInformation(
        name = "tr",
        information = "Переводит данную строку на язык, который вам нужен.",
        detailedInformation = "[language] text")
public class Translator extends Command {
    public static HashMap<String, String> languages = new HashMap<>();

    public void fillHashMap() {
        try {
            var reader = new BufferedReader(new FileReader(Config.getPathToLanguages()));
            var line = reader.readLine();
            while (line != null) {
                var arr = line.split("\t");
                languages.put(arr[0], arr[1]);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String translate(String language, String text) {
        try {
            if (!Translator.languages.containsKey(language))
                return "";
            return GoogleTranslate.translate(language, text);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException {
        var messages = event.getMessage().getContentRaw().split(" ");
        if (messages.length < 3) {
            Sender.send(event, "Введите корректные данные");
            return;
        }
        var str = event
                .getMessage()
                .getContentRaw()
                .replace(messages[0] + " " + messages[1], "");
        event.getChannel().sendMessage(Translator.translate(messages[1], str)).queue();
    }
}
