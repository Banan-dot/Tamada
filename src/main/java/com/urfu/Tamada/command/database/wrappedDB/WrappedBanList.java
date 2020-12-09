package com.urfu.Tamada.command.database.wrappedDB;

import com.urfu.Tamada.BanList;
import com.urfu.Tamada.command.Pair;
import com.urfu.Tamada.command.database.banList.BanTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WrappedBanList extends BanTable {
    private final HashMap<Long, List<Long>> banList;

    public WrappedBanList(HashMap<Long, List<Long>> banList){
        this.banList = banList;
    }

    @Override
    public void addToBanList(Long id, Long guildId){
        if (banList.containsKey(id)){
            var copyArr = new ArrayList<>(banList.get(id));
            copyArr.add(guildId);
            banList.put(id, copyArr);
        }

        else
            banList.put(id, List.of(guildId));
    }

    public HashMap<Long, List<Long>> getBanTable(){
        assert banList != null;
        return banList;
    }

    @Override
    public void removeFromBanList(Long id, Long guildId){
        if (banList.containsKey(id))
            banList.get(id).remove(guildId);
    }

    @Override
    public boolean isInBanList(Long id, Long guildId){
        return banList.containsKey(id) && banList.get(id).contains(guildId);
    }
}
