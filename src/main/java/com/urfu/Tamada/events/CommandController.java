package com.urfu.Tamada.events;

import com.urfu.Tamada.Config;
import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.CommandFactory;
import com.urfu.Tamada.command.Pair;
import com.urfu.Tamada.command.alias.Alias;
import com.urfu.Tamada.command.crocodile.Crocodile;
import com.urfu.Tamada.command.permissionCommands.*;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Objects;


public class CommandController extends ListenerAdapter {
    private Crocodile crocodile;
    private Alias alias;
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        System.out.println(event.getMessage().getContentRaw());


        var message = event.getMessage().getContentRaw();
        if (Crocodile.Active){
            if (message.equalsIgnoreCase(crocodile.word))
            {
                Sender.send(event, "Да. Ты угадал, поздравляю!");
            }
            if (message.equals("я хочу")){
                crocodile.sendMessageToPrivateChannel(event);
            }
        }
        var commandName = message.split(" ")[0];
        if (commandName.startsWith(Config.getPrefix()))
        {
            var command = new CommandFactory().getCommand(commandName.substring(1));
            if (commandName.equals("!croc")) crocodile = (Crocodile) command;
            try {
                command.execute(event);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Pair getMemberFromEvent(GuildMessageReceivedEvent event){
        var guild = event.getJDA().getGuildById(Objects.requireNonNull(event.getMember()).getGuild().getId());
        var mess = event.getMessage().getContentRaw();
        assert guild != null;
        var id = Long.parseLong(new GetIdFromString().getIdFromString(mess));
        var member = event.getGuild().getMemberById(String.valueOf(id));
        assert member != null;
        return new Pair(member, guild);
    }
}