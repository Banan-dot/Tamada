package com.urfu.Tamada.Command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class KickMember extends Command {
    @Override
    public void execute(CommandData commandData, GuildMessageReceivedEvent event) {
        Objects.requireNonNull(event.getMember())
                .kick("Вы нам не понравились, обращайтесь в службу поддержи. До свидания!")
                .queue();
    }
}
