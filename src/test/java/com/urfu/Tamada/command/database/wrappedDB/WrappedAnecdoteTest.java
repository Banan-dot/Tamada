package com.urfu.Tamada.command.database.wrappedDB;

import com.urfu.Tamada.command.database.anecdotes.Anecdotes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class WrappedAnecdoteTest {
    private WrappedAnecdote anecdotes;

    @BeforeEach
    void setUp(){
        var strings = new ArrayList<String>();
        strings.add("lol");
        strings.add("kek");
        strings.add("cheburek");
        anecdotes = new WrappedAnecdote(strings);
    }

    @Test
    void getAnecdoteById(){
        var lol = anecdotes.getAnecdoteById(1);
        var kek = anecdotes.getAnecdoteById(2);
        var cheburek = anecdotes.getAnecdoteById(3);
        Assertions.assertEquals("lol", lol);
        Assertions.assertEquals("kek", kek);
        Assertions.assertEquals("cheburek", cheburek);
    }

    @Test
    void getRandomAnecdote(){
        var randomAnecdote = anecdotes.getRandomAnecdote();
        Assertions.assertNotNull(randomAnecdote);
        Assertions.assertNotEquals(randomAnecdote, "");
    }
}