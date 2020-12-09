package com.urfu.Tamada.command.database.wrappedDB;

import com.urfu.Tamada.command.database.anecdotes.Anecdotes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class WrappedAnecdote extends Anecdotes {
    private final HashMap<Integer, String> anecdotes = new HashMap<>();

    public WrappedAnecdote(ArrayList<String> strings){
        for(var i = 1; i < strings.size() + 1; i++)
            anecdotes.put(i, strings.get(i-1));
    }

    @Override
    public String getAnecdoteById(int id) {
        return anecdotes.getOrDefault(id, "");
    }

    @Override
    public String getRandomAnecdote(){
        var rnd_at = new Random().nextInt(anecdotes.size());
        return getAnecdoteById(rnd_at);
    }
}
