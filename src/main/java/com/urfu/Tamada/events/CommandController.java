package com.urfu.Tamada.events;

import com.urfu.Tamada.Config;
import com.urfu.Tamada.command.CommandFactory;
import com.urfu.Tamada.command.Pair;
import com.urfu.Tamada.command.permissionCommands.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import javax.annotation.Nonnull;
import java.util.Objects;


public class CommandController extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        
        var message = event.getMessage().getContentRaw();
        var commandName = message.split(" ")[0];
        if (commandName.startsWith(Config.getPrefix()))
        {
            var command = new CommandFactory().getCommand(commandName.substring(1));
            command.execute(event);
        }
    }

    public static Pair getMemberFromEvent(GuildMessageReceivedEvent event){
        var guild = event.getJDA().getGuildById(Objects.requireNonNull(event.getMember()).getGuild().getId());
        var mess = event.getMessage().getContentRaw();
        assert guild != null;
        var id = Long.parseLong(new GetIdFromString().getIdFromString(mess));
        var member = guild.getMemberById(id);
        assert member != null;
        return new Pair(member, guild);
    }
}