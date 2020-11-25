package com.urfu.Tamada;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String prefix = "!";
    public static String getPrefix() { return prefix; }
    public static String getToken(String key) { return dotenv.get(key); }
}
