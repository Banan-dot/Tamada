package com.urfu.Tamada.command.ascii;

import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Ascii extends Command {

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        String savePathOriginal = "./src\\main\\java\\com\\urfu\\Tamada\\command\\ascii\\original.jpg";
        String pathToPython = "./src\\main\\java\\com\\urfu\\Tamada\\command\\ascii\\ascii/AsciiGenerator.py";
        String pathAscii = "./src\\main\\java\\com\\urfu\\Tamada\\command\\ascii\\ascii_original.jpg";
        new AsciiExecutor(savePathOriginal, pathAscii, pathToPython).saveOriginalPhoto(event);
    }
}
