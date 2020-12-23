package com.urfu.Tamada.command;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.database.anecdotes.Anecdotes;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

@CommandInformation(
        name = "anec",
        information = "Выдает рандомный анекдот.",
        detailedInformation = "[Number] Number - количество (от 1 до 10) анекдотов, которое вы хотите получить.")
public class JokeCommand extends Command {
    private String getRandomAnecdote() {
        var anecdote = new Anecdotes().getRandomAnecdote();
        anecdote = parseRawString(anecdote);
        return anecdote;
    }

    public static String parseRawString(String rawJoke) {
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
        if (arr.length == 1)
            return 1;
        var integer = arr[1];
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
