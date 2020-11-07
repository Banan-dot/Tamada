package com.urfu.Tamada.command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class NoCommand extends Command{

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("Такой команды нет.").queue();
    }
}
