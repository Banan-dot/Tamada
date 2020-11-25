package com.urfu.Tamada;

import java.io.*;
import java.util.HashSet;

public class BanList {
    private final HashSet<Long> banList;
    private  final int count;
    private final String path = "./resources/ban_list.txt";

    public BanList(){
        banList = new HashSet<>();
        count = 0;
        fillBanList();
    }

    public void fillBanList(){
        try {
            var reader = new BufferedReader(new FileReader(path));
            var line = reader.readLine();
            while (line != null) {
                banList.add(Long.parseLong(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(long channelId, long memberId){
        try {
            var writer = new BufferedWriter(new FileWriter(path));
            writer.append(String.valueOf(channelId)).append(".").append(String.valueOf(memberId));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFromBanList(long l){
        banList.remove(l);
    }

    public void addToBanList(long channelId, long memberId){
        //banList.add(channelId, memberId);
        writeToFile(channelId, memberId);
    }

    public boolean isInBanList(long l){
        return banList.contains(l);
    }

    public HashSet<Long> getBanList(){
        return banList;
    }

    public int getCount(){
        return count;
    }
}
