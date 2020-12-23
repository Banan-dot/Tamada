package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.CommandInformation;
import com.urfu.Tamada.command.permissions.PermissionCommandWithMembers;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;

import static java.util.Objects.requireNonNull;

@CommandInformation(name = "rename", information = "Меняет имя указанного пользователя на канале. Если у вас есть для этого права, конечно.",
        detailedInformation = "[@MemberName] [New name]")
public class Rename extends PermissionCommandWithMembers {
     @Override
    public void execute(GuildMessageReceivedEvent event) {
        var messArr = event.getMessage().getContentRaw().split(" ");
        var pair = CommandController.getMemberFromEvent(event);
        var member = pair.getFirst();
        System.out.println(member);
        var guild = pair.getSecond();
        var newName = messArr[2];
        try {
            requireNonNull(guild.getMemberById(member.getId())).modifyNickname(newName).queue();
        } catch (NullPointerException e) {
            Sender.send(event, "Ну не могу найти я мембера \\*\\*\\*\\*\\*, извини....");
        } catch (HierarchyException e) {
            Sender.send(event, "Не могу изменить ник мембера, у которого роль выше моей");
        }
    }
}
