package com.urfu.Tamada;

import com.urfu.Tamada.events.JokeEvent;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class JDABot {
    public  static void main(String[] args) throws Exception{
        var token = "NzYzMDUxOTYxNTAyMTM4NDEw.X3yFhA.p-NCvNHK3ug_4qhvBLQjlmZv-Qk";
        new JDABuilder()
                .setToken(token)
                .addEventListeners(new JokeEvent())
                .setActivity(Activity.watching("Работает"))
                .build();
    }
}
