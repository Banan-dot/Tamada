package com.urfu.Tamada.vk;

import com.urfu.Tamada.Config;
import com.urfu.Tamada.IO.Reader;
import com.urfu.Tamada.IO.Writer;
import com.urfu.Tamada.command.Pair;
import com.urfu.Tamada.vk.VK;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.wall.WallPostFull;
import com.vk.api.sdk.queries.wall.WallGetFilter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TimerTask;

public class VkProgramMemes extends TimerTask{
    private int lastPostId;
    private final String zenChannel = "zen";
    private final String sourceName = "๖ۣۜХауди ๖ۣۜХо™ - Просто о мире IT!";
    private final String defaultUrl = "https://sun9-70.userapi.com/impg/Veza5-3DlyS-TOnGgi60Zjq-luiZL-2D_dSB-g/8PTbf5Bl2Uc.jpg?size=512x512&quality=96&proxy=1&sign=02d6feb636b1568411d8fa7ee1a874f8&type=album";

    public VkProgramMemes(JDA jda) {
        lastPostId = Reader.readLastId();

    }

    public void execute() throws ClientException, ApiException {
        var data = new ArrayList<Pair<String, String>>();
        var items = VK.getPostsById(Config.getHaudiHoGroupId(), 100);
        for (var i : items) {
            var curr_text = i.getText();
            if (i.getId() == lastPostId)
                break;
            try {
                var curr_url = i.getAttachments().get(0).getPhoto().getPhoto1280();
                data.add(Pair.create(Objects.requireNonNullElse(curr_url, defaultUrl), curr_text));
            }
            catch (Exception e){
                data.add(Pair.create(defaultUrl, curr_text));
            }
        }
        sendPost(data);
        lastPostId = items.get(0).getId();
        Writer.writeLastIdToFile(lastPostId);
    }

    @Override
    public void run() {
        System.out.println("execute...");
        try {
            execute();
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPost(ArrayList<Pair<String, String>> data) {
        var builder = new EmbedBuilder();
        try {
            data.forEach(post -> VK.getJda()
                    .getGuilds()
                    .forEach(guild -> guild
                            .getTextChannelsByName(zenChannel, true)
                            .forEach(x -> x
                                    .sendMessage(builder
                                            .setAuthor(sourceName)
                                            .setTitle(post.getSecond())
                                            .setImage(post.getFirst())
                                            .build())
                                    .queue())));
        } catch (Exception ignored) {}
    }
}
