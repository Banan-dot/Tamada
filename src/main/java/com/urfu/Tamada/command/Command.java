package com.urfu.Tamada.command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {
    public abstract void execute(GuildMessageReceivedEvent event);
}
