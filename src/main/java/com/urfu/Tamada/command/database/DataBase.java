package com.urfu.Tamada.command.database;

import com.urfu.Tamada.command.database.anecdotes.Anecdotes;
import com.urfu.Tamada.command.database.banList.BanTable;
import com.urfu.Tamada.command.database.pastes.PastTable;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
    private final Connection connection;

    public DataBase(String url) throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:" + url);
    }

    public Connection getConnection() {
        return connection;
    }

    public static Data getAnectodes() {
        return new Anecdotes();
    }

    public static Data getPaste() {
        return new PastTable();
    }

    public static Data getBanList() {
        return new BanTable();
    }


}