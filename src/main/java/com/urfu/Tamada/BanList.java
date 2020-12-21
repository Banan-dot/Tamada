package com.urfu.Tamada;

import com.urfu.Tamada.command.Pair;
import com.urfu.Tamada.command.database.banList.BanTable;

import java.util.HashSet;

public class BanList {
    public static HashSet<Pair<Long, Long>> banList;
    public static BanTable banListBD;

    public BanList() {
        banList = new HashSet<>();
        banListBD = new BanTable();
    }

    public void fillBanList() {
        BanList.banListBD.getBannedUsers();
    }

    public static void removeFromBanList(long channelId, long memberId) {
        BanList.banList.remove(Pair.create(memberId, channelId));
        BanList.banListBD.removeFromBanList(memberId, channelId);
    }

    public static void addToBanList(long channelId, long memberId) {
        BanList.banList.add(Pair.create(memberId, channelId));
        BanList.banListBD.addToBanList(memberId, channelId);
    }

    public static boolean isInBanList(long channelId, long memberId) {
        return BanList.banList.contains(Pair.create(memberId, channelId));
    }

    public static HashSet<Pair<Long, Long>> getBanList() {
        return BanList.banList;
    }

    public static int getCount() {
        return banList.size();
    }
}
