package com.urfu.Tamada.command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException;

    public String getName() {
        return getClass().getAnnotation(CommandInformation.class).name();
    }

    public String getUsageInformation() {
        return getClass().getAnnotation(CommandInformation.class).information();
    }

    public String getDetailedInformation() {
        return getClass().getAnnotation(CommandInformation.class).detailedInformation();
    }
}
