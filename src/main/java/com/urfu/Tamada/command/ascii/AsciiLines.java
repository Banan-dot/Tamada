package com.urfu.Tamada.command.ascii;

import com.urfu.Tamada.command.CommandData;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.File;

public class AsciiLines {
    String savePathOriginal = "./src\\main\\java\\com\\urfu\\Tamada\\command\\ascii\\original.jpg";
    String pathAscii = "./src\\main\\java\\com\\urfu\\Tamada\\command\\ascii\\ascii_lines_original.jpg";
    String pathToPython = "./src\\main\\java\\com\\urfu\\Tamada\\command\\ascii\\Ascii_lines/ascii_main.py";

    public void saveOriginalPhoto(CommandData commandData, GuildMessageReceivedEvent event){
        try {
            var photo = event
                    .getMessage()
                    .getAttachments()
                    .get(0);
            photo.downloadToFile(savePathOriginal).join();
            var ext = photo.getFileName().split("\\.")[1];
            if (!photo.isImage() || ext.equals("gif")) {
                event.getChannel().sendMessage("Отправьте изображение, пожалуйста.").queue();
                return;
            }
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        saveAsciiArt();
        sendPhotoToChannel(event);
    }

    public void sendPhotoToChannel(GuildMessageReceivedEvent event)
    {
        File file = new File(pathAscii);
        event.getChannel().sendFile(file).queue();
    }
    public void saveAsciiArt(){
        try {
            String[] cmd = {"cmd", "/c", "python "+ pathToPython};
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
