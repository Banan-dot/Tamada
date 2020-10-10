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
        var count = 0;
        var command = message.split(" ")[0];
        var integer = message.split(" ")[1];
        if (!command.equalsIgnoreCase("анек"))
            return;
        if (tryParseInt(integer))
            count = Integer.parseInt(integer);
        else {
            event.getChannel().sendMessage("Write number ∈[1; 10]").queue();
            return;
        }
        if (count <= 0 || count > 10) {
            System.out.println(count);
            event.getChannel().sendMessage("Write correct number ∈[1; 10]").queue();
            return;
        }
        sendJokes(count, event);
    }

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean check(String[] part){
        try {
            var number = part[1];
            var message = part[0];
            return !message.contains("-") && Integer.parseInt(number) <= 10;
        }
        catch (Exception exception){
            return false;
        }
    }

    private void sendJokes(Integer count, @Nonnull GuildMessageReceivedEvent event){
        var joke = new JokeCommand();
        var margin = "\n-------------------";
        for (var i = 0; i < count; i++)
            if (!Objects.requireNonNull(event.getMember()).getUser().isBot())
                event.getChannel().sendMessage(joke.execute() + margin).queue();
    }
}

