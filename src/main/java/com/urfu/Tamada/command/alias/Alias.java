package com.urfu.Tamada.command.alias;

import com.urfu.Tamada.Config;
import com.urfu.Tamada.IO.Reader;
import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;

import java.lang.reflect.Member;
import java.util.*;

public class Alias extends Command {
    private ArrayList<String> words = new ArrayList<>();
    private int interval = 60;
    public int points = 0;
    public String channelId;
    public net.dv8tion.jda.api.entities.Message currentMessage;
    public net.dv8tion.jda.api.entities.Member leader;
    public boolean active = false;
    private final String help = "Игра в алиас.\nСтарт: !alias start\nСтать ведущим: !alias me\nЗакончить игру: !alias end";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }


    public Alias() {
        HashMap<Member, Integer> membersScore = new HashMap<>();
    }

    private void fillWords() {
        words = Reader.readWords(Config.getPathToAliasWords());
    }

    private String getRandomWord() {
        return words.get(new Random().nextInt(words.size() - 1));
    }

    private void setInterval(GuildMessageReceivedEvent event) {
        final net.dv8tion.jda.api.entities.Message[] mess = {event
                .getChannel()
                .sendMessage("Application will close in 60 seconds.")
                .complete()};
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (interval <= 0) {
                    mess[0].delete().complete();
                    Sender.send(event, "Таймер вышел\n" + leader.getNickname() + ": " + points + " points");
                    interval = 60;
                    leader = null;
                    cancel();
                }
                var id = mess[0].getIdLong();
                mess[0] = mess[0]
                        .getChannel()
                        .editMessageById(id, "Time left:" + interval-- + " seconds.")
                        .complete();
            }
        }, 0, 1000);
    }

    public void sendToPrivateMessFromGuild(GuildMessageReceivedEvent event) {
        var member = event.getMember();
        leader = member;
        assert member != null;
        var channel = member.getUser().openPrivateChannel().complete();
        var message = channel.sendMessage(getRandomWord()).complete();
        message.addReaction("\u2705").queue();
        message.addReaction("\u27A1").queue();
        currentMessage = message;
    }

    public void sendToPrivateMess(PrivateMessageReactionAddEvent event) {
        var message = event.getChannel().sendMessage(getRandomWord()).complete();
        message.addReaction("\u2705").queue();
        message.addReaction("\u27A1").queue();
        currentMessage = message;
    }

    public net.dv8tion.jda.api.entities.Member getLeader() {
        return leader;
    }

    private void sendQuestion(GuildMessageReceivedEvent event) {
        Sender.send(event, "Кто хочет быть ведущим?");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var mess = event.getMessage().getContentRaw().split(" ");

        if (mess.length == 2 && mess[1].equals("me")) {
            sendToPrivateMessFromGuild(event);
            setInterval(event);
        }

        if (mess.length == 2 && mess[1].equals("end")) {
            active = false;
            Sender.send(event, "Игра окончена.");
            return;
        }

        if (mess.length == 2 && mess[1].equals("start")) {
            fillWords();
            active = true;
            channelId = event.getGuild().getId();
            Sender.send(event, "Игра начинается");
            sendQuestion(event);
        }
    }
}
