package com.urfu.Tamada.command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException;
    public abstract void getHelp(GuildMessageReceivedEvent event);
}
