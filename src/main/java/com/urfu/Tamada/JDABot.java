package com.urfu.Tamada;

import com.urfu.Tamada.events.JokeEvent;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class JDABot {
    public static void main(String[] args) throws Exception {
        new JDABuilder()
                .setToken(Config.GetToken("TOKEN"))
                .addEventListeners(new JokeEvent())
                .setActivity(Activity.watching("говнокод Данила"))
                .build();
    }
}
