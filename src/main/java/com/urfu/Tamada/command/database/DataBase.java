package com.urfu.Tamada.command.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private final Connection connection;

    public DataBase(String url) throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:" + url);
    }

    public Connection getConnection(){ return connection; }
}
