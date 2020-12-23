package com.urfu.Tamada;

import com.urfu.Tamada.command.Translator;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


public class Sender {
    private static String language = "en";

    public Sender(String language) {
        Sender.setLanguage(language);
    }

    public static void send(GuildMessageReceivedEvent event, String str) {
        event.getChannel().sendMessage(Translator.translate(Sender.getLanguage(), str)).queue();
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        Sender.language = language;
    }
}
