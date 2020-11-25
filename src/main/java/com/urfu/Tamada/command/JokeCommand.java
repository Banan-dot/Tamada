package com.urfu.Tamada.command;

import java.sql.*;
import java.util.*;

import com.urfu.Tamada.DebugUtil;
import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.database.Database;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

@CommandName(name = "Anek")
public class JokeCommand extends Command {
    private String getRandomAnecdote() {
        var rnd_at = new Random().nextInt(130256);
        var anecdote = new Database().getAnecdoteById(rnd_at);
        anecdote = parseRawString(anecdote);
        new DebugUtil().simpleLogger(anecdote, rnd_at);
        return anecdote;
    }

    private String parseRawString(String rawJoke) {
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

    public void execute(GuildMessageReceivedEvent event) {
        var messArr = event.getMessage().getContentRaw().split(" ");
        var anecdoteCount = getAnecdoteCount(messArr, event);
        var margin = "\n-------------------";
        for (var i = 0; i < anecdoteCount; i++)
            if (!Objects.requireNonNull(event.getMember()).getUser().isBot())
                Sender.send(event, getRandomAnecdote() + margin);
    }

    private int getAnecdoteCount(String[] arr, GuildMessageReceivedEvent event) {
        var count = 1;
        var integer = arr.length == 1 ? "1" : arr[1];
        if (tryParseInt(integer))
            count = Integer.parseInt(integer);
        else {
            Sender.send(event, "Write number ∈[1; 10]");
            return 0;
        }
        if (count <= 0 || count > 10) {
            Sender.send(event, "Write correct number ∈[1; 10]");
            return 0;
        }
        return count;
    }
}
