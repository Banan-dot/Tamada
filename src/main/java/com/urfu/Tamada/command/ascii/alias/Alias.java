package com.urfu.Tamada.command.ascii.alias;

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

    private void a(GuildMessageReceivedEvent event){
        timer = new Timer();
        long period = 1000L;
        long delay = 1000L;
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                event.getMessage().editMessage(String.valueOf(setInterval(event))).queue();
            }
        }, delay, period);

    }

    private int setInterval(GuildMessageReceivedEvent event){
        if (interval == 1) {
            timer.cancel();
            event.getChannel().sendMessage("Таймер вышел").queue();
        }
        return --interval;
    }

    private void sendMessageToChannel(GuildMessageReceivedEvent event){
        try {
            event
                    .getJDA()
                    .getTextChannelsByName("Alias", true)
                    .get(0)
                    .sendMessage(getRandomWord())
                    .queue();
        }
        catch (Exception e) {
            event
                .getGuild()
                .createTextChannel("Alias")
                .addPermissionOverride(Objects.requireNonNull(event.getMember()),
                                       EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_ADD_REACTION),
                                  null)
                .addPermissionOverride(event.getGuild().getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                .queue();
//            event
//                    .getJDA()
//                    .getTextChannelsByName("Alias", true)
//                    .get(0)
//                    .sendMessage(getRandomWord())
//                    .queue();
        }
    }

    //private boolean isRightWord(String w1, String eventMess){
    //    return w1.equalsIgnoreCase(eventMess);
    //}
    private void sendTimer(GuildMessageReceivedEvent event){
//        TimerTask task = new TimerTask() {
//            public void run() {
//                event.getChannel().sendMessage("Рандомное слово: "+getRandomWord()).queue();
//            }
//        };
//        var timer = new Timer("Timer");
//        timer.scheduleAtFixedRate(task, delay, period);
        event.getChannel().sendMessage("Отгадать слово: "+getRandomWord()).queue();
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
        //sendMessageToChannel(event);
        //fillWords();
        //a(event);
        //sendTimer(event);
    }
}
