package com.urfu.Tamada.command;

import com.urfu.Tamada.Sender;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.reflections.Reflections;


@CommandInformation(name = "help", information = "Выводит help по командам.",
        detailedInformation = "[Command to detailed information]")
public class HelpCommand extends Command {

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var message = event.getMessage().getContentRaw().split(" ");
        if (message.length > 1) {
            var commandToHelp = message[1];
            var command = new CommandFactory().getCommand(commandToHelp);
            var information = command.getUsageInformation();
            var detailedInformation = command.getDetailedInformation();
            var text = commandToHelp + "\n\t" + information + "\n\t" + detailedInformation;
            Sender.send(event, text);
        }
        if (message.length == 1)
            getBasicHelp(event);
    }

    private void getBasicHelp(GuildMessageReceivedEvent event) {
        var commandClasses = new Reflections(Command.class).getSubTypesOf(Command.class);
        var stringBuilder = new StringBuilder();
        stringBuilder.append("`");
        var c = 0;
        for (var commandClass : commandClasses) {
            if (isAbstractClass(commandClass))
                continue;
            var classAnnotation = commandClass.getDeclaredAnnotation(CommandInformation.class);
            var name = classAnnotation.name();
            var information = classAnnotation.information();
            stringBuilder.append(++c).append(". ").append(name).append(": ").append(information).append("\n");
        }
        stringBuilder.append("`");
        Sender.send(event, stringBuilder.toString());
    }

    private boolean isAbstractClass(Class<? extends Command> commandClass) {
        return commandClass.getDeclaredAnnotation(CommandInformation.class) == null;
    }
}