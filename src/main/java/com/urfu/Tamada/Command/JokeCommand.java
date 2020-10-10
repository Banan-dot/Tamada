package com.urfu.Tamada.Command;

import java.sql.*;
import java.util.*;
import com.urfu.Tamada.DebugUtil;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;

@CommandName(name = "Anek")
public class JokeCommand extends Command {
    private static final String url = "jdbc:sqlite:.\\Aneks0.db";

    private String getRandomAnecdote()
    {
        var rnd_at = new Random().nextInt(130256);
        var anecdote = "";
        try (var connection = DriverManager.getConnection(url)){
                var sql = String.format("SELECT * FROM anek WHERE id = %d", rnd_at);
                var stmt  = connection.createStatement();
                var rs    = stmt.executeQuery(sql);
                anecdote = rs.getString("text");
            }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        anecdote = parseRawString(anecdote);
        new DebugUtil()
                .simpleLogger(anecdote, rnd_at);
        return anecdote;
    }

    private String parseRawString(String rawJoke){
        return rawJoke
                .replace("&quot;", "\"")
                .replace("\"\"", "\"")
                .replace("\\n", "\n")
                .replaceAll("\n$", "");
    }

    public String execute(CommandData commandData) {
        return getRandomAnecdote();
    }
}
