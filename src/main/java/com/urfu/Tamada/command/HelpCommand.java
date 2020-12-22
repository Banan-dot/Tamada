package com.urfu.Tamada.command;

import com.urfu.Tamada.Sender;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class HelpCommand extends Command {
    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, "Выводит help по командам.");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var message = event.getMessage().getContentRaw().split(" ");
        if (message.length > 1) {
            var commandToHelp = message[1];
            new CommandFactory().getCommand(commandToHelp).getHelp(event);
        }
        if (message.length == 1)
            getBasicHelp(event);
    }

    private void getBasicHelp(GuildMessageReceivedEvent event) {
        var allCommands = """
                Список команд:
                bl
                help
                anec
                mute
                tr
                unmute
                rename
                kick
                mute_t
                ascii
                sl
                hd
                ping, p
                mm
                ll
                unmute_t
                addsmile
                importemotes
                delete
                croc, crocodile
                alias
                ban
                unban
                meme, mem
                panthers
                Для более подробной информации: !help [command name]
                """;
        Sender.send(event, allCommands);
    }
}