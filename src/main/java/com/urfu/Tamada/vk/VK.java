package com.urfu.Tamada.vk;

import com.urfu.Tamada.Config;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.WallPostFull;
import com.vk.api.sdk.queries.wall.WallGetFilter;
import net.dv8tion.jda.api.JDA;

import java.util.List;

public class VK {
    private static JDA jda;
    private static VkApiClient client;
    private static ServiceActor serviceActor;

    public VK(JDA jda){
        VK.jda = jda;
        var transportClient = HttpTransportClient.getInstance();
        client = new VkApiClient(transportClient);
        serviceActor = new ServiceActor(Config.getVkAppId(), Config.getVkAccessToken("VK_TOKEN"));
    }

    public static JDA getJda() {
        return jda;
    }

    public static VkApiClient getClient() {
        return client;
    }

    public static ServiceActor getServiceActor() {
        return serviceActor;
    }

    public static List<WallPostFull> getPostsById(Integer id, Integer count) throws ClientException, ApiException {
        return client.wall().get(VK.getServiceActor())
                .ownerId(id)
                .count(count)
                .offset(1)
                .filter(WallGetFilter.valueOf("ALL"))
                .execute()
                .getItems();
    }
}
