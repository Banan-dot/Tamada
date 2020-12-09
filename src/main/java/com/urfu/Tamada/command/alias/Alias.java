package com.urfu.Tamada.command.alias;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.lang.reflect.Member;
import java.util.*;

public class Alias extends Command {
    private final ArrayList<String> words = new ArrayList<>();
    private int interval = 60;
    private Timer timer;
    private final int pointer = 0;
    private net.dv8tion.jda.api.entities.Member leading;

    private final String help = "Игра в алиас.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }


    public Alias(){
        HashMap<Member, Integer> membersScore = new HashMap<>();
    }
    private void fillWords(){
        try {
            String pathToWords = "./resources/alias_words.txt";
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

    private int setInterval(GuildMessageReceivedEvent event){
        if (interval == 1) {
            timer.cancel();
            event.getChannel().sendMessage("Таймер вышел").queue();
        }
        return --interval;
    }

    private void sendToPrivateMess(GuildMessageReceivedEvent event){
        var member = event.getMember();
        assert member != null;
        member.getUser().openPrivateChannel().queue((channel) ->
                channel.sendMessage(getRandomWord()).queue());
    }

    @Override
    public void execute(GuildMessageReceivedEvent event){
        fillWords();
        sendToPrivateMess(event);
    }
}
