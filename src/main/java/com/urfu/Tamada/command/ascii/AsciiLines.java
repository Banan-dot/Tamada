package com.urfu.Tamada.command.ascii;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.CommandInformation;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
@CommandInformation(name = "hd", information = "Делает аски-арт (синусоида) из приккрепленной картинки.")
public class AsciiLines extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException {
        String pathAscii = "./src/main/java/com/urfu/Tamada/command/ascii/ascii_lines_original.jpg";
        String pathToPython = "./src/main/java/com/urfu/Tamada/command/ascii/Ascii_lines/ascii_main.py";
        String savePathOriginal = "./src/main/java/com/urfu/Tamada/command/ascii/original.jpg";
        new AsciiExecutor(savePathOriginal, pathAscii, pathToPython).saveOriginalPhoto(event);
    }
}