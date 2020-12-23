package com.urfu.Tamada.command.ascii;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

@CommandInformation(name = "ascii", information = "Делает аски-арт из приккрепленной картинки.")
public class Ascii extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException {
        String savePathOriginal = "./src/main/java/com/urfu/Tamada/command/ascii/original.jpg";
        String pathToPython = "./src/main/java/com/urfu/Tamada/command/ascii/ascii/AsciiGenerator.py";
        String pathAscii = "./src/main/java/com/urfu/Tamada/command/ascii/ascii_original.jpg";
        new AsciiExecutor(savePathOriginal, pathAscii, pathToPython).saveOriginalPhoto(event);
    }
}