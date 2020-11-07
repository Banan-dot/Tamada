package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class ChatUnmute extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var pair = CommandController.getMemberFromEvent(event);
        var member = pair.getMember();
        if (Objects.requireNonNull(member).hasPermission(Permission.MESSAGE_WRITE))
        {
            event
                .getGuild()
                .getTextChannels()
                .forEach(i -> i.getManager()
                        .getChannel()
                        .putPermissionOverride(member)
                        .setAllow(Permission.MESSAGE_WRITE)
                        .queue());
            event
                .getChannel()
                .sendMessage(String.format("Пользователь %s размьючен", member.getNickname()))
                .queue();
        }
    }
}
