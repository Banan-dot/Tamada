package com.urfu.Tamada;

import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class JDABot {
    public static void main(String[] args) throws Exception {
        var token = Config.getToken("TOKEN");
        JDABuilder.createLight(token)
                .addEventListeners(new CommandController())
                .setActivity(Activity.listening("HUI"))
                .build();
    }
}
