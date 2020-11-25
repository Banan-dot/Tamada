package com.urfu.Tamada.command.crocodile;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Crocodile extends Command {
    private final ArrayList<String> words;
    public static boolean Active;
    public String word;
    public String channelId;
    public net.dv8tion.jda.api.entities.Member host;

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

    public void sendMessageToPrivateChannel(GuildMessageReceivedEvent event){
        word = getRandomWord();
        Objects.requireNonNull(event.getMember()).getUser().openPrivateChannel().queue((channel) ->
                channel.sendMessage(word).queue());

    }
    public void sendMessageToChannel(GuildMessageReceivedEvent event){
        String name = "крокодил";
        try {
            event
                .getGuild()
                .getTextChannelsByName(name, true)
                .get(0)
                .sendMessage(getRandomWord())
                .queue();
        }
        catch (Exception e) {
            var channel = event.getGuild().createTextChannel(name).complete();
            channel.getMembers().forEach(i -> channel.createPermissionOverride(i).deny(Permission.VIEW_CHANNEL).queue());
            channel.sendMessage(getRandomWord()).queue();
        }
    }

    private void sendQuestion(GuildMessageReceivedEvent event){
        Sender.send(event, "Кто хочет быть ведущим?");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var mess = event.getMessage().getContentRaw().split(" ");

        if (mess.length == 2 && mess[1].equals("me")){
            sendMessageToPrivateChannel(event);
        }

        if (mess.length == 2 && mess[1].equals("end")){
            Active = false;
            Sender.send(event, "Игра окончена.");
            return;
        }

        if (mess.length == 2 && mess[1].equals("start")){
            Active = true;
            channelId = event.getGuild().getId();
            Sender.send(event, "Игра начинается");
            fillWords();
            sendQuestion(event);
        }
    }
}
