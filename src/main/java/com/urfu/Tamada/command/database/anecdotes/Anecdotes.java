package com.urfu.Tamada.command.database.anecdotes;

import com.urfu.Tamada.command.database.Data;

import java.sql.SQLException;
import java.util.Random;

public class Anecdotes extends Data {
    public String getAnecdoteById(int id) {
        try (var statement = database.getConnection().createStatement()) {
            var sql = String.format("SELECT * FROM anek WHERE id = %d;", id);
            return statement.executeQuery(sql).getString("text");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "";
        }
    }

    public String getRandomAnecdote() {
        var rnd_at = new Random().nextInt(130256);
        return getAnecdoteById(rnd_at);
    }
}
