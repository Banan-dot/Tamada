package com.urfu.Tamada;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;

public class Config {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String prefix = "!";
    private static final String botId = "763051961502138410";
    private static final String url = "./resources/Data.db";
    private static final String pathToLanguages = "./resources/languages.txt";
    private static final String pathToAliasWords = "./resources/alias_words.txt";
    public static String getPathToAliasWords() { return pathToAliasWords; }
    public static String getUrl() { return url; }
    public static String getPathToLanguages() {return pathToLanguages; }
    public static String getBotId(){ return botId;}
    public static String getPrefix() { return prefix; }
    public static String getToken(String key) { return dotenv.get(key); }
}
