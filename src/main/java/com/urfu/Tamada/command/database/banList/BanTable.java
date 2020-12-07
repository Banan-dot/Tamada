package com.urfu.Tamada.command.database.banList;

import com.urfu.Tamada.BanList;
import com.urfu.Tamada.command.Pair;
import com.urfu.Tamada.command.database.Data;

import java.sql.SQLException;
import java.util.HashSet;

public class BanTable extends Data {
    public void addToBanList(Long id, Long guildId){
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("INSERT INTO `ban_list` (id, guild) VALUES (%d, %d);", id, guildId);
            statement.executeUpdate(sql);
        } catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    public void getBannedUsers(){
        try (var statement = database.getConnection().createStatement()) {
            var sql = "SELECT * FROM ban_list;";
            var resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                var id = resultSet.getLong("id");
                var guildId = resultSet.getLong("guild");
                BanList.banList.add(Pair.create(id, guildId));
            }
        } catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    public void removeFromBanList(Long id, Long guildId){
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("DELETE FROM `ban_list` WHERE id=%d AND guild=%d;", id, guildId);
            statement.executeUpdate(sql);
        } catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    public boolean isInBanList(Long id, Long guildId){
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("SELECT * FROM `ban_list` WHERE id=%d AND guild=%d;", id, guildId);
            var result = statement.executeQuery(sql);
            return result.next() && result.getInt(1) > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
