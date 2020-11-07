package com.urfu.Tamada.command.ascii.crocodile;

import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Crocodile extends Command {
    private final ArrayList<String> words;

    public Crocodile(){
        words = new ArrayList<>();
    }

    private void fillWords(){
        try {
            String pathToWords = "./resources/crocodile_words.txt";
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

    private String getRandomWord(){
        return words.get(new Random().nextInt(words.size() - 1));
    }

    private void sendMessageToChannel(GuildMessageReceivedEvent event){
        try {
            event
                .getJDA()
                .getTextChannelsByName("Крокодил", true)
                .get(0)
                .sendMessage(getRandomWord())
                .queue();
        }
        catch (Exception e) {
            event.getGuild().createTextChannel("Крокодил").queue();
            event
                .getJDA()
                .getTextChannelsByName("Крокодил", true)
                .get(0)
                .sendMessage(getRandomWord())
                .queue();
        }
    }

    private void sendQuestion(GuildMessageReceivedEvent event){
        event.getChannel().sendMessage("Кто хочет быть ведущим?").queue();
    }


    private boolean isRightWord(String inputWord, String templateWord){
        return inputWord.equalsIgnoreCase(templateWord);
    }
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        fillWords();
        sendQuestion(event);

        var word  = getRandomWord();

    }
}
