package com.urfu.Tamada.Command;

import java.sql.*;
import java.util.*;
import com.urfu.Tamada.DebugUtil;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

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

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void execute(CommandData commandData, GuildMessageReceivedEvent event) {
        var messArr = event.getMessage().getContentRaw().split(" ");
        var anecdoteCount = getAnecdoteCount(messArr, event);
        var margin = "\n-------------------";
        for (var i = 0; i < anecdoteCount; i++)
            if (!Objects.requireNonNull(event.getMember()).getUser().isBot())
                event.getChannel().sendMessage(getRandomAnecdote() + margin).queue();
    }

    private int getAnecdoteCount(String[] arr, GuildMessageReceivedEvent event){
        var count = 1;
        var integer= arr.length == 1 ? "1" : arr[1];
        if (tryParseInt(integer))
            count = Integer.parseInt(integer);
        else {
            event.getChannel().sendMessage("Write number ∈[1; 10]").queue();
            return 0;
        }
        if (count <= 0 || count > 10) {
            event.getChannel().sendMessage("Write correct number ∈[1; 10]").queue();
            return 0;
        }
       return count;
    }
}
