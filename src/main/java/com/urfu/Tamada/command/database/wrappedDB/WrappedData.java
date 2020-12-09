package com.urfu.Tamada.command.database.wrappedDB;

import com.urfu.Tamada.command.Pair;

import java.util.HashSet;

public class WrappedData {
    protected static HashSet<Pair<Long, Long>> database;

    public static void setDatabase(HashSet<Pair<Long, Long>> database) {
        WrappedData.database = database;
    }
}
