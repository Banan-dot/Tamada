package com.urfu.Tamada.command.zen;

import com.urfu.Tamada.command.database.subscribes.SubscribesDB;
import com.urfu.Tamada.vk.VkPost;

import java.util.ArrayList;
import java.util.HashMap;


public class Subscriber{
    public static SubscribesDB subsBD = new SubscribesDB();;
    private final Long guildId;
    private HashMap<Integer, Integer> subs;
    private final ArrayList<VkPost> posts;
    
    public Subscriber(long guildID){
        guildId = guildID;
        subs = Subscriber.getSubsFromDb();
        posts = new ArrayList<>();
        fillSubs();
    }

    public void addPost(VkPost post){
        posts.add(post);
    }

    public ArrayList<VkPost> getPosts(){
        return posts;
    }

    public HashMap<Integer, Integer> getSubs(){
        return subs;
    }

    private void fillSubs(){
        subs = subsBD.getAllSubsByGuildId(guildId);
    }

    public static HashMap<Integer, Integer> getSubsFromDb(){
        return  Subscriber.subsBD.getSubs();
    }

    public static void removeFromSubs(long guildId, int groupId) {
        Subscriber.subsBD.removeGroupId(guildId, groupId);
    }

    public static void addToSubs(long guildId, int groupId, int lastId) {
        Subscriber.subsBD.addGroupId(guildId, groupId, lastId);
    }

    public static void addLastId(int groupId, int lastId){
        Subscriber.subsBD.addLastId(groupId, lastId);
    }

    public static HashMap<Integer, Integer> getSubsByGuildId(long guildId){
        return Subscriber.subsBD.getAllSubsByGuildId(guildId);
    }

    public static boolean isInSubs(long guildId, int groupId) {
        return Subscriber.subsBD.isInBanList(guildId, groupId);
    }
}
