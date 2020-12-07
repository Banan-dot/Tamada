package com.urfu.Tamada;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String prefix = "!";
    private static final String botId = "763051961502138410";
    public static String getBotId(){ return botId;}
    public static String getPrefix() { return prefix; }
    public static String getToken(String key) { return dotenv.get(key); }
}
