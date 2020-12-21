package com.urfu.Tamada;

import com.urfu.Tamada.IO.Reader;
import com.urfu.Tamada.IO.Writer;
import com.urfu.Tamada.command.Pair;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.WallPostFull;
import com.vk.api.sdk.queries.wall.WallGetFilter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TimerTask;

public class VkProgramMemes extends TimerTask {
    private final JDA jda;
    private int lastPostId;
    private final String zenChannel = "zen";
    private final String sourceName = "๖ۣۜХауди ๖ۣۜХо™ - Просто о мире IT!";
    private final VkApiClient vk;
    private final ServiceActor serviceActor;
    private final String deaultUrl = "https://sun9-70.userapi.com/impg/Veza5-3DlyS-TOnGgi60Zjq-luiZL-2D_dSB-g/8PTbf5Bl2Uc.jpg?size=512x512&quality=96&proxy=1&sign=02d6feb636b1568411d8fa7ee1a874f8&type=album";

    public VkProgramMemes(JDA jda) {
        this.jda = jda;
        var transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
        serviceActor = new ServiceActor(Config.getVkAppId(), Config.getVkAccessToken("VK_TOKEN"));
        lastPostId = Reader.readLastId();
    }

    public void execute() throws ClientException, ApiException {
        var data = new ArrayList<Pair<String, String>>();
        var items = getPosts();
        for (var i : items) {
            if (i.getId() == lastPostId)
                break;
            try {
                var curr_url = i.getAttachments().get(0).getPhoto().getPhoto1280();
                var curr_text = i.getText();
                data.add(Pair.create(Objects.requireNonNullElse(curr_url, deaultUrl), curr_text));
            } catch (Exception ignored) {
            }
        }

        sendPost(data);

        lastPostId = items.get(0).getId();
        Writer.writeLastIdToFile(lastPostId);
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPost(ArrayList<Pair<String, String>> data) {
        var builder = new EmbedBuilder();
        try {
            data.forEach(post -> jda
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
        } catch (Exception ignored) {
        }
    }

    private List<WallPostFull> getPosts() throws ClientException, ApiException {
        return vk.wall().get(serviceActor)
                .ownerId(Config.getHaudiHoGroupId())
                .count(20)
                .offset(1)
                .filter(WallGetFilter.valueOf("ALL"))
                .execute()
                .getItems();
    }
}
