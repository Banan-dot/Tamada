package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ChatMute extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var messArr = event.getMessage().getContentRaw().split(" ");
        var mess = event.getMessage();
        var pair = CommandController.getMemberFromEvent(event);
        var member = pair.getMember();
        var guild = pair.getGuild();
        event.getChannel().sendMessage(String.format("Пользователь %s замьючен", member.getNickname())).queue();
    }
}
