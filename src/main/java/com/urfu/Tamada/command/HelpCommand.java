package com.urfu.Tamada.command;

import com.urfu.Tamada.Sender;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class HelpCommand extends Command {
    private final String help = "Выводит help по командам.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event){
        var message = event.getMessage().getContentRaw().split(" ");
        if (message.length > 1) {
            var commandToHelp = message[1];
            new CommandFactory().getCommand(commandToHelp).getHelp(event);
        }
        if (message.length == 1)
            getBasicHelp(event);
    }

    private void getBasicHelp(GuildMessageReceivedEvent event) {
        var reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.urfu"))
                .setScanners(new SubTypesScanner())
                .filterInputsBy(new FilterBuilder().includePackage("com.urfu")));
        var classes = reflections.getSubTypesOf(com.urfu.Tamada.command.Command.class);
        assert classes != null;
        for (var aClass : classes)
            try {
                aClass.getMethod("getHelp", GuildMessageReceivedEvent.class).invoke(aClass, event);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
    }
}