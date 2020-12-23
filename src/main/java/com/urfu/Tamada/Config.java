package com.urfu.Tamada;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String prefix = "!";
    private static final String botId = "763051961502138410";
    private static final String url = "./resources/Data.db";
    private static final String pathToLanguages = "./resources/languages.txt";
    private static final String pathToAliasWords = "./resources/alias_words.txt";
    private static final String pathToLastWord = "./resources/id.txt";
    public static final String pathToCrocodileWords = "./resources/crocodile_words.txt";
    private static final int appId = 7703895;
    private static final int haudiHo = -84392011;
    private static final int hardSign = -201329136;

    public static int getHardSign() {return hardSign;}

    public static int getVkAppId() {
        return appId;
    }

    public static int getHaudiHoGroupId() {
        return haudiHo;
    }

    public static String getPathToCrocodileWords() {
        return pathToCrocodileWords;
    }

    public static String getVkAccessToken(String key) {
        return dotenv.get(key);
    }

    public static String getPathToAliasWords() {
        return pathToAliasWords;
    }

    public static String getPathToLastWord() {
        return pathToLastWord;
    }

    public static String getUrl() {
        return url;
    }

    public static String getPathToLanguages() {
        return pathToLanguages;
    }

    public static String getBotId() {
        return botId;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getToken(String key) {
        return dotenv.get(key);
    }
}
