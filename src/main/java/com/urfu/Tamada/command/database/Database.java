package com.urfu.Tamada.command.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String url = "jdbc:sqlite:.\\resources\\Aneks0.db";

    public String getAnecdoteById(int id) {
        try (var connection = DriverManager.getConnection(url)) {
            var sql = String.format("SELECT * FROM anek WHERE id = %d", id);
            var stmt = connection.createStatement();
            var rs = stmt.executeQuery(sql);
            return rs.getString("text");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "";
        }
    }
}
