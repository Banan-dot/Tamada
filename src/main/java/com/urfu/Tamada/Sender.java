package com.urfu.Tamada;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Sender {
    public static void send(GuildMessageReceivedEvent event, String str){
        event.getChannel().sendMessage(str).queue();
    }
}
