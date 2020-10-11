package com.urfu.Tamada.Command.permissionCommands;

import com.urfu.Tamada.Command.Command;
import com.urfu.Tamada.Command.CommandData;
import com.urfu.Tamada.events.JokeEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class KickMember extends Command {
    @Override
    public void execute(CommandData commandData, GuildMessageReceivedEvent event) {
        var pair = JokeEvent.getMemberFromEvent(event);
        var member = pair.getMember();
        var guild = pair.getGuild();
        if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)){
            Objects.requireNonNull(member)
                    .kick("Вы нам не понравились, обращайтесь в службу поддержи. До свидания!")
                    .queue();
        }
    }
}
