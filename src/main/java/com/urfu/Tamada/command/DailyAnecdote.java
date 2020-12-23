package com.urfu.Tamada.command;


import com.urfu.Tamada.command.database.anecdotes.Anecdotes;
import net.dv8tion.jda.api.JDA;

import java.util.Objects;
import java.util.TimerTask;


public class DailyAnecdote extends TimerTask {
    private final JDA jda;

    public DailyAnecdote(JDA jda) {
        this.jda = jda;
    }

    public void sendDailyAnecdote() {
        var anecdote = "Анекдот дня:\n" + new Anecdotes().getRandomAnecdote();
        jda.getGuilds().forEach(guild ->
                Objects.requireNonNull(guild.getDefaultChannel())
                        .sendMessage(JokeCommand.parseRawString(anecdote))
                        .queue());
    }

    @Override
    public void run() {
        sendDailyAnecdote();
    }
}
