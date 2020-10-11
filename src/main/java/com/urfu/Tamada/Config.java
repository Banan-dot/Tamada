package com.urfu.Tamada;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getToken(String key) {
        return dotenv.get(key);
    }
}
