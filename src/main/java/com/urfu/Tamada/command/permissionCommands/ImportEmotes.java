package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ImportEmotes extends Command {

    private final String help = "Добавляет смайлы с указанного сервера, на котором есть этот бот. Если у вас есть для этого права, конечно.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) throws IOException {
        var path = ".\\resources\\img.png";
        var mess = event.getMessage().getContentRaw().split(" ");
        if (mess.length == 1) {
            Sender.send(event, "Требуется id канала сервера");
            return;
        }
        else if (!tryParseLong(mess[1])) {
            Sender.send(event, "Id должен быть числом");
            return;
        }
        var id = mess[1];
        for(var emote: Objects.requireNonNull(event.getJDA().getGuildById(id)).getEmotes()){
            var image = getIcon(emote.getImageUrl());
            ImageIO.write(image, "png", new File(path));
            File f = new File(path);
            event.getGuild().createEmote(emote.getName(), Icon.from(f)).queue();
        }
    }

    private boolean tryParseLong(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
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
