package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ImportEmotes extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws IOException {
        var path = ".\\resources\\img.png";
        var besedu_id = "711138028462014575";
        for(var emote: Objects.requireNonNull(event.getJDA().getGuildById(besedu_id)).getEmotes()){
            var image = getIcon(emote.getImageUrl());
            ImageIO.write(image, "png", new File(path));
            File f = new File(path);
            event.getGuild().createEmote(emote.getName(), Icon.from(f)).queue();
        }
    }
    private BufferedImage getIcon(String photo_url){
        BufferedImage image = null;
        try {
            URL url = new URL(photo_url);
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
