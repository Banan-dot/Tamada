package com.urfu.Tamada.Command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {
    public abstract void execute(CommandData commandData, GuildMessageReceivedEvent event);
}
