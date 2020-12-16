package com.urfu.Tamada;

import com.urfu.Tamada.command.Translator;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


public class Sender {
    public static String language = "ru";

    public static void send(GuildMessageReceivedEvent event, String str){
        event.getChannel().sendMessage(Translator.translate(Sender.language, str)).queue();
    }
}
