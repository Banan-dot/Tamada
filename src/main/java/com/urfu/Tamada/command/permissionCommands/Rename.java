package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.permissions.PermissionCommandWithMembers;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;

import static java.util.Objects.*;

public class Rename extends PermissionCommandWithMembers {

    private final String help = "Меняет имя указанного пользователя на канале. Если у вас есть для этого права, конечно.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event){
        var messArr = event.getMessage().getContentRaw().split(" ");
        var pair = CommandController.getMemberFromEvent(event);
        var member = pair.getFirst();
        System.out.println(member);
        var guild = pair.getSecond();
        var newName = messArr[2];
        try {requireNonNull(guild.getMemberById(member.getId())).modifyNickname(newName).queue();}
        catch (NullPointerException e){
            Sender.send(event, "Ну не могу найти я мембера \\*\\*\\*\\*\\*, извини....");
        }
        catch (HierarchyException e) {
            Sender.send(event,"Не могу изменить ник мембера, у которого роль выше моей");
        }
    }
}
