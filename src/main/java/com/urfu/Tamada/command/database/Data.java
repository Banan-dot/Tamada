package com.urfu.Tamada.command.database;

public abstract class Data {
    protected static DataBase database;

    public static void setDatabase(DataBase database) {
        Data.database = database;
    }
}
