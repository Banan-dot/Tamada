package com.urfu.Tamada.command.permissions;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class PermissionCommandWithMembers extends PermissionCommand {
    public abstract void execute(GuildMessageReceivedEvent event);
}