package com.urfu.Tamada.command.ascii;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.File;

public class AsciiExecutor {
    private final String savePathOriginal;
    private final String pathAscii;
    private final String pathToPython;
    private final String answerIfNoImage = "Отправьте изображение, пожалуйста.";

    public AsciiExecutor(String savePathOriginal, String pathAscii, String pathToPython){
        this.savePathOriginal = savePathOriginal;
        this.pathAscii = pathAscii;
        this.pathToPython = pathToPython;
    }
    public void saveOriginalPhoto(GuildMessageReceivedEvent event){
        try {
            var photo = event
                    .getMessage()
                    .getAttachments()
                    .get(0);
            photo.downloadToFile(savePathOriginal).join();
            var ext = photo.getFileName().split("\\.")[1];
            if (!photo.isImage() || ext.equals("gif")) {
                event.getChannel().sendMessage(answerIfNoImage).queue();
                return;
            }
        }
        catch (IndexOutOfBoundsException e) {
            event.getChannel().sendMessage(answerIfNoImage).queue();
            return;
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