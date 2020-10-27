package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class Rename extends Command {
    public void execute(GuildMessageReceivedEvent event){
        var messArr = event.getMessage().getContentRaw().split(" ");
        var mess = event.getMessage();
        var pair = CommandController.getMemberFromEvent(event);
        var member = pair.getMember();
        var guild = pair.getGuild();
        var newName = messArr[2];
        Objects.requireNonNull(guild.getMemberById(member.getId())).modifyNickname(newName).queue();

    }
}
