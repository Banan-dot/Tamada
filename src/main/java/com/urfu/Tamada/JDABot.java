package com.urfu.Tamada;

import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class JDABot {
    public static void main(String[] args) throws Exception {
        JDABuilder.createLight(Config.getToken("TOKEN"))
                .addEventListeners(new CommandController())
                .setActivity(Activity.watching("в будущее России"))
                .build();
        System.out.println("Бот запустился...");
    }
}
