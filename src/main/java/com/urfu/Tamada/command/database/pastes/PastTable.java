package com.urfu.Tamada.command.database.pastes;

import com.urfu.Tamada.command.database.Data;

import java.sql.SQLException;
import java.util.ArrayList;

public class PastTable extends Data {
    public String getPaste(Long guildId, String pasteName) {
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("SELECT * FROM paste WHERE groupID=%d AND pasteName=%s;", guildId, pasteName);
            var result = statement.executeQuery(sql);
            if (result.next()) {
                return result.getString("text");
            }
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void makePaste(Long guildId, String pasteName, String paste) {
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("INSERT INTO `paste` (groupID, `pasteName`, `text`) VALUES (%d, %s, %s);", guildId, pasteName, paste);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removePaste(Long guildId, String pasteName) {
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("DELETE FROM `paste` WHERE groupID=%d AND `pasteName`=%s;", guildId, pasteName);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getPastesName(Long guildId) {
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("SELECT * FROM `paste` WHERE groupID=%d;", guildId);
            var result = statement.executeQuery(sql);
            var pastesList = new ArrayList<String>();
            while (result.next()) {
                pastesList.add(result.getString("pasteName"));
            }
            return pastesList;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
