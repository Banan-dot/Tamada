package com.urfu.Tamada.events;

import com.urfu.Tamada.BanList;
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
    private final HashMap<Long, Alias> aliases = new HashMap<>();

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        var channelId = event.getGuild().getIdLong();
        if (BanList.isInBanList(event.getGuild().getIdLong(), event.getMember().getIdLong()))
            event.getMessage().delete().queue();
        var messageContent = event.getMessage().getContentRaw();
        var commandName = messageContent.split(" ")[0];
        if (isCrocodileActivate(channelId, commandName, event)) {
            crocodiles.get(channelId).execute(event);
            if (crocodiles.get(channelId).word.equalsIgnoreCase(messageContent)) {
                Sender.send(event, "Да. Ты угадал, поздравляю!");
                crocodiles.get(channelId).word = "";
            }
            return;
        }

        if (commandName.startsWith(Config.getPrefix()))
            handleCommand(commandName, event, channelId);
    }

    private void handleCommand(String commandName, GuildMessageReceivedEvent event, Long channelId){
        var command = new CommandFactory().getCommand(commandName.substring(1));
        if (commandName.equals("!croc")) {
            crocodiles.put(channelId, (Crocodile) command);
            crocodiles.get(channelId).execute(event);
            return;
        }
        try { command.execute(event); }
        catch (InterruptedException | IOException e) { e.printStackTrace();}
    }

    private boolean isCrocodileActivate(
            Long channelId, String commandName, GuildMessageReceivedEvent event){
        return Crocodile.Active
                && crocodiles.get(channelId).channelId != null
                && (commandName.startsWith("!croc") || !commandName.startsWith("!"))
                && !Objects.requireNonNull(event.getMember()).getId().equals(Config.getBotId());
    }

    public static Pair<net.dv8tion.jda.api.entities.Member, net.dv8tion.jda.api.entities.Guild>
    getMemberFromEvent(GuildMessageReceivedEvent event){
        var guild = event.getJDA().getGuildById(Objects.requireNonNull(event.getMember()).getGuild().getId());
        var mess = event.getMessage().getContentRaw();
        assert guild != null;
        var id = Long.parseLong(new GetIdFromString().getIdFromString(mess));
        var member = event.getGuild().getMemberById(String.valueOf(id));
        assert member != null;
        return Pair.create(member, guild);
    }
}