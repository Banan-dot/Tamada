package com.urfu.Tamada.events;

import com.urfu.Tamada.Config;
import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.CommandFactory;
import com.urfu.Tamada.command.Pair;
import com.urfu.Tamada.command.alias.Alias;
import com.urfu.Tamada.command.crocodile.Crocodile;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import com.urfu.Tamada.command.permissionCommands.GetIdFromString;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;


public class CommandController extends ListenerAdapter {
    private final HashMap<Long, Crocodile> crocodiles = new HashMap<>();
    private Alias alias;

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        System.out.println(event.getMessage().getContentRaw());
        var message = event.getMessage();
        var channelId = event.getGuild().getIdLong();
        var id = Objects.requireNonNull(event.getMember()).getIdLong();
        if (Config.getBanList().isInBanList(id))
            message.delete().queue();
        var messageContent = event.getMessage().getContentRaw();
        var commandName = messageContent.split(" ")[0];
        if (Crocodile.Active
                && crocodiles.get(channelId).channelId != null
                && (commandName.startsWith("!croc") || !commandName.startsWith("!"))
                && !event.getMember().getId().equals(Config.getBotId())) {
            crocodiles.get(channelId).execute(event);
            if (crocodiles.get(channelId).word.equalsIgnoreCase(messageContent)) {
                Sender.send(event, "Да. Ты угадал, поздравляю!");
                crocodiles.get(channelId).word = "";
            }
            return;
        }
        if (commandName.startsWith(Config.getPrefix())) {
            var command = new CommandFactory().getCommand(commandName.substring(1));
            if (commandName.equals("!croc")) {
                crocodiles.put(channelId, (Crocodile) command);
                crocodiles.get(channelId).execute(event);
                return;
            }
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