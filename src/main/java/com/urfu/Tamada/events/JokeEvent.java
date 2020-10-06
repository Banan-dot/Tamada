package com.urfu.Tamada.events;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import com.urfu.Tamada.Command.JokeCommand;

import javax.annotation.Nonnull;
import java.util.Objects;



public class JokeEvent extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event){
        var message = event.getMessage().getContentRaw();
        System.out.println(message);
        var joke = new JokeCommand();
        if (message.equalsIgnoreCase("анек"))
            if(!Objects.requireNonNull(event.getMember()).getUser().isBot()){
                event.getChannel().sendMessage(joke.execute()).queue();
            }
    }
}
