package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;


@CommandInformation(name = "p, ping", information = "Простая реализация игры в пинг-понг на c++")
public class Ping extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException {
        Sender.send(event, "Простая реализация игры в пинг-понг на c++");
    }
}
