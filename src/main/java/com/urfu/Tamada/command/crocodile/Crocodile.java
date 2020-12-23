package com.urfu.Tamada.command.crocodile;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import com.urfu.Tamada.command.Translator;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


@CommandInformation(name = "croc, crocodile", information = "Игра в крокодила.",
        detailedInformation = "Старт: !croc start\n\tСтать ведущим: !croc me\n\tЗакончить игру: !croc end")
public class Crocodile extends Command {
    public final ArrayList<String> words;
    public static boolean active;
    public String word;
    public String channelId;
    public net.dv8tion.jda.api.entities.Member host;
    public String pathToWords = "./resources/crocodile_words.txt";

    public Crocodile() {
        words = new ArrayList<>();
    }

    public void fillWords() {
        try {
            var reader = new BufferedReader(new FileReader(pathToWords));
            var line = reader.readLine();
            while (line != null) {
                words.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRandomWord() {
        return words.get(new Random().nextInt(words.size() - 1));
    }

    public void sendMessageToPrivateChannel(GuildMessageReceivedEvent event) {
        word = getRandomWord();
        Objects.requireNonNull(event.getMember()).getUser().openPrivateChannel().queue((channel) ->
                channel.sendMessage(Translator.translate(Sender.getLanguage(), word)).queue());

    }

    private void sendQuestion(GuildMessageReceivedEvent event) {
        Sender.send(event, "Кто хочет быть ведущим?");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var mess = event.getMessage().getContentRaw().split(" ");

        if (mess.length == 2 && mess[1].equals("me"))
            sendMessageToPrivateChannel(event);

        if (mess.length == 2 && mess[1].equals("end")) {
            active = false;
            Sender.send(event, "Игра окончена.");
            return;
        }

        if (mess.length == 2 && mess[1].equals("start") && !active) {
            active = true;
            channelId = event.getGuild().getId();
            Sender.send(event, "Игра начинается");
            fillWords();
            sendQuestion(event);
        }
    }
}