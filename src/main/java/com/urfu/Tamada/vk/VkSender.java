package com.urfu.Tamada.vk;

import com.urfu.Tamada.Config;
import com.urfu.Tamada.IO.Reader;
import com.urfu.Tamada.IO.Writer;
import com.urfu.Tamada.command.zen.Subscriber;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.*;

public class VkSender extends TimerTask{
    private int lastPostId;
    private final String zenChannel = "zen";

    public void execute() throws ClientException, ApiException {
        var data = new HashMap<Long, Subscriber>();
        var guilds = getGuilds();
        var isBreak = false;
        for (var guild : guilds) {
            var guildId = guild.getIdLong();
            var subs = Subscriber.getSubsByGuildId(guildId);
            for (var sub : subs.keySet()) {
                var posts = VK.getPostsById(sub, 2);
                for (var i : posts) {
                    if (i.getId().equals(subs.get(sub))){
                        break;
                    }
                    var curr_text = i.getText();
                    try {
                        var curr_url = i.getAttachments().get(0).getPhoto().getPhoto1280();
                        if (!data.containsKey(guildId)) {
                            data.put(guildId, new Subscriber(guildId));
                        }
                        data.get(guildId).addPost(new VkPost(curr_text, curr_url, sub));
                    } catch (Exception e) {
                        if (!data.containsKey(guildId))
                            data.put(guildId, new Subscriber(guildId));
                        data.get(guildId).addPost(new VkPost(curr_text, VK.getGroupById(sub).getPhoto200(), sub));
                    }
                    data.get(guildId).getSubs().put(sub, posts.get(0).getId());
                }
            }
        }

        sendPost(data);
        addLastId(data);
    }

    private void addLastId(HashMap<Long, Subscriber> data){
        for (var subscriber : data.values()){
            var subs = subscriber.getSubs();
            for(var sub: subs.keySet())
                Subscriber.addLastId(sub, subs.get(sub));
        }
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
    }

    private List<Guild> getGuilds(){
        return VK.getJda().getGuilds();
    }

    private void sendPost(HashMap<Long, Subscriber> data) throws ClientException, ApiException {
        var builder = new EmbedBuilder();
        for(var guild : getGuilds()){
            var zen = guild.getTextChannelsByName(zenChannel, true).get(0);
            var id = guild.getIdLong();
            if (data.containsKey(id))
                for (var post : data.get(id).getPosts())
                    try {
                        builder.setAuthor(VK.getNameById(post.getGroupId()))
                                .setTitle(post.getText())
                                .setImage(post.getPhotoUrl())
                                .build();
                        zen.sendMessage(builder.build()).queue();
                    }
                    catch (Exception ignored){}
        }
    }
}
