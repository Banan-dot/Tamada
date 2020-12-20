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
import java.util.TimerTask;

public class VkProgramMemes extends TimerTask {
    private final JDA jda;
    private int lastPostId;
    private final String zenChannel = "zen";
    private final String sourceName = "๖ۣۜХауди ๖ۣۜХо™ - Просто о мире IT!";
    private final VkApiClient vk;
    private final ServiceActor serviceActor;

    public VkProgramMemes(JDA jda){
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
                if (curr_url != null && curr_text != null)
                    data.add(Pair.create(curr_url, sourceName + "\n" + curr_text));
            }
            catch (Exception ignored){}
        }

        try {
            data
            .stream()
            .map(post -> new EmbedBuilder().setTitle(post.getSecond()).setImage(post.getFirst()))
            .forEach(builder -> jda
                                .getGuilds()
                                .forEach(guild -> guild
                                                .getTextChannelsByName(zenChannel, true)
                                                .forEach(x -> x
                                                            .sendMessage(builder.build())
                                                            .queue())));
        } catch (Exception ignored) {}
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

    private List<WallPostFull> getPosts() throws ClientException, ApiException {
        return vk.wall().get(serviceActor)
                .ownerId(Config.getHaudiHoGroupId())
                .count(50)
                .offset(1)
                .filter(WallGetFilter.valueOf("ALL"))
                .execute()
                .getItems();
    }
}
