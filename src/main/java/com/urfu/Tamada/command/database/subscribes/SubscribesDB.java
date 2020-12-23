package com.urfu.Tamada.command.database.subscribes;

import com.urfu.Tamada.BanList;
import com.urfu.Tamada.command.Pair;
import com.urfu.Tamada.command.database.Data;
import com.urfu.Tamada.command.zen.Subscriber;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SubscribesDB extends Data {
    public void addGroupId(long guildId, Integer groupId, Integer lastId){
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("INSERT INTO `subs` (guild, groupId, lastId) VALUES (%d, %d, %d);",
                    guildId,
                    groupId,
                    lastId);
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addLastId(Integer groupId, Integer lastId){
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("UPDATE `subs` SET lastId=%d WHERE groupId=%d;", lastId, groupId);
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeGroupId(long guildId, Integer groupId){
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("DELETE FROM `subs` WHERE groupId=%d AND guild=%d;", groupId, guildId);
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isInBanList(long guildId, Integer groupId) {
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("SELECT * FROM `subs` WHERE groupId=%d AND guild=%d;", groupId, guildId);
            var result = statement.executeQuery(sql);
            return result.next() && result.getInt(1) > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public HashMap<Integer, Integer> getAllSubsByGuildId(long guildId){
        var result = new HashMap<Integer, Integer>();
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("SELECT * FROM subs WHERE guild=%d;", guildId);
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next())
                result.put(resultSet.getInt("groupId"), resultSet.getInt("lastId"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public HashMap<Integer, Integer> getSubs(){
        var result = new HashMap<Integer, Integer>();
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("SELECT * FROM subs");
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next())
                result.put(resultSet.getInt("groupId"), resultSet.getInt("lastId"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
