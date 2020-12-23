package com.urfu.Tamada.command.permissions;

import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;


public abstract class PermissionCommand extends Command {
    public abstract void execute(GuildMessageReceivedEvent event) throws IOException, InterruptedException;
}