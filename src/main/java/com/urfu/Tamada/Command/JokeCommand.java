package com.urfu.Tamada.Command;

import java.sql.*;
import java.util.*;
import com.urfu.Tamada.DebugUtil;


public class JokeCommand extends Command {
    private static final String url = "jdbc:sqlite:.\\Aneks0.db";

    private String getRandomAnecdote()
    {
        var rnd_at = new Random().nextInt(130256);
        var result = "";
        //System.out.println(JDBC.PREFIX);
        //Class.forName("org.sqlite.JDBC");
        try (var connection = DriverManager.getConnection(url)){
                var sql = String.format("SELECT * FROM anek WHERE id = %d", rnd_at);
                var stmt  = connection.createStatement();
                var rs    = stmt.executeQuery(sql);
                
                while (rs.next()) {
                    System.out.println(rs.getString("text"));
                    result = rs.getString("text");
                }
            }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //var rnd = new Random().nextInt(jokes_db.size());
        //var result = jokes_db.get(rnd);
        result = parseRawString(result);
        new DebugUtil()
                .simpleLogger(result, rnd_at);
        return result;
    }

    private String parseRawString(String rawJoke){
        // &quote 74244
        // "Sparta4|PS-3"  90154
        // 103270 1258-
        rawJoke = rawJoke.replace("&quot;", "\"");
        rawJoke = rawJoke.replace("\"\"", "\"");
        rawJoke = rawJoke.replace("\\n\\n", "\n");
        rawJoke = rawJoke.replace("\\n", "\n");
        return rawJoke.replaceAll("\n$", "");
    }

    public String execute() {
        return getRandomAnecdote();
    }
}
