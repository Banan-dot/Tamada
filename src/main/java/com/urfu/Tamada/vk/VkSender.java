package com.urfu.Tamada.vk;

import com.urfu.Tamada.Config;
import com.urfu.Tamada.IO.Reader;
import com.urfu.Tamada.IO.Writer;
import com.urfu.Tamada.command.zen.Subscriber;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.*;

public class VkSender extends TimerTask{
    private final String zenChannel = "zen";

    public void execute() throws Exception {
        var data = new HashMap<Long, Subscriber>();
        var guilds = getGuilds();
        for (var guild : guilds) {
            var guildId = guild.getIdLong();
            var subs = Subscriber.getSubsByGuildId(guildId);
            for (var sub : subs.keySet()) {
                var posts = VK.getPostsById(sub, 30);
                for (var i : posts) {
                    if (i.getId().equals(subs.get(sub))){
                        break;
                    }
                    var curr_text = i.getText();
                    try {
                        var curr_url = i.getAttachments().get(0).getPhoto().getPhoto604();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Guild> getGuilds(){
        return VK.getJda().getGuilds();
    }

    private void sendPost(HashMap<Long, Subscriber> data) throws Exception {
        var builder = new EmbedBuilder();
        for(var guild : getGuilds()){
            var zen = guild.getTextChannelsByName(zenChannel, true).get(0);
            var id = guild.getIdLong();
            if (data.containsKey(id))
            for (var post : data.get(id).getPosts()) {
                var text = normalizedString(post.getText());
                builder.setAuthor(VK.getNameById(post.getGroupId()))
                        .setTitle(text)
                        .setImage(post.getPhotoUrl())
                        .build();
                zen.sendMessage(builder.build()).queue();
            }
    }
}

    private String normalizedString(String str){
        if (str.equals(""))
            return "Только фотография";
        if (str.length() >= 255)
            return str.substring(0, 255);
        return str;
    }
}
