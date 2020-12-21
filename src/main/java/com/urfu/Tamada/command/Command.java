package com.urfu.Tamada.command;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException, ClientException, ApiException;

    public abstract void getHelp(GuildMessageReceivedEvent event);

}
