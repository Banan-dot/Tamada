package com.urfu.Tamada.command;

import com.urfu.Tamada.Sender;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class NoCommand extends Command {
    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        Sender.send(event, "Такой команды нет.");
    }
}
