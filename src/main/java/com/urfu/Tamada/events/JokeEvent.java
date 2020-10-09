package com.urfu.Tamada.events;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import com.urfu.Tamada.Command.JokeCommand;

import javax.annotation.Nonnull;
import java.util.Objects;


public class JokeEvent extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        var message = event.getMessage().getContentRaw();
        var part = message.split("(?<=\\D)(?=\\d)");
        message = part[0].replaceAll("\\s+", "");
        var count = "1";
        if (message.equalsIgnoreCase("анек") ||
                message.equalsIgnoreCase("анек-")){
            if (part.length == 2) {
                count = part[1].replaceAll("\\s+", "");
                if (!check(part))
                    event.getChannel().sendMessage("some problems.").queue();
                else {
                    System.out.println(message);
                    sendJokes(Integer.parseInt(count), event);
                }
            }
            else
                sendJokes(Integer.parseInt(count), event);
        }
    }

    private boolean check(String[] part){
        var number = part[1];
        var message = part[0];
        return !message.contains("-") && Integer.parseInt(number) <= 26;
    }

    private void sendJokes(Integer count, @Nonnull GuildMessageReceivedEvent event){
        var joke = new JokeCommand();
        var margin = "\n-------------------";
        for (var i = 0; i < count; i++)
            if (!Objects.requireNonNull(event.getMember()).getUser().isBot())
                event.getChannel().sendMessage(joke.execute() + margin).queue();
    }
}

