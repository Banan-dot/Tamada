package com.urfu.Tamada.Command;
import java.sql.*;
import java.util.*;

import org.sqlite.JDBC;

public class JokeCommand extends Command {
    private static final String url = "jdbc:sqlite:.\\Aneks0.db";

    private String getRandomAnecdote()
    {
        var rnd_at = new Random().nextInt(130256);
        var aneks = new ArrayList<String>();
        //System.out.println(JDBC.PREFIX);
        //Class.forName("org.sqlite.JDBC");
        try (Connection connection = DriverManager.getConnection(url)){
                String sql = String.format("SELECT * FROM anek WHERE id = %d", rnd_at);
                Statement stmt  = connection.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);

                while (rs.next()) {
                    System.out.println(rs.getString("text"));
                    aneks.add(rs.getString("text"));
                }
            }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        var rnd = new Random().nextInt(aneks.size());
        var result = aneks.get(rnd);
        return result.replace("\\n", "\n");
    }
    public String execute() {
        return getRandomAnecdote();
    }
}
