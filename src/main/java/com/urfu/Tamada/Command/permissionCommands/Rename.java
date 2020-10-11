package com.urfu.Tamada.Command.permissionCommands;

import com.urfu.Tamada.Command.Command;
import com.urfu.Tamada.Command.CommandData;
import com.urfu.Tamada.events.JokeEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class Rename extends Command {
    public void execute(CommandData commandData,  GuildMessageReceivedEvent event){
        var messArr = event.getMessage().getContentRaw().split(" ");
        var mess = event.getMessage();
        var pair = JokeEvent.getMemberFromEvent(event);
        var member = pair.getMember();
        var guild = pair.getGuild();
        var newName = messArr[2];
        Objects.requireNonNull(guild.getMemberById(member.getId())).modifyNickname(newName).queue();

    }
}
