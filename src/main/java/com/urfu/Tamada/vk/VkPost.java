package com.urfu.Tamada.vk;

public class VkPost {
    private final String text;
    private final String photoUrl;
    private final int groupId;

    public VkPost(String text, String photoUrl, int groupId){
        this.text = text;
        this.photoUrl = photoUrl;
        this.groupId = groupId;
    }

    public String getText(){
        return text;
    }

    public String getPhotoUrl(){
        return photoUrl;
    }

    public int getGroupId() { return groupId; }
}
