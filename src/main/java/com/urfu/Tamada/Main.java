package com.urfu.Tamada;

import com.urfu.Tamada.command.DailyAnecdote;
import com.urfu.Tamada.command.Translator;
import com.urfu.Tamada.command.database.Data;
import com.urfu.Tamada.command.database.DataBase;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.sound.midi.Track;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        var database = new DataBase(Config.getUrl());
        Data.setDatabase(database);
        new BanList().fillBanList();
        new Translator().fillHashMap();
        new Sender("ru");
        var bot = JDABuilder.createDefault(Config.getToken("TOKEN"))
                .setChunkingFilter(ChunkingFilter.ALL) // enable member chunking for all guilds
                .setMemberCachePolicy(MemberCachePolicy.ALL) // ignored if chunking enabled
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new CommandController())
                .setActivity(Activity.watching("аниме."))
                .build();
        setTimer(bot);
    }

    private static void setTimer(JDA jda){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 7);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Timer timer = new Timer();
        timer.schedule(new DailyAnecdote(jda),
                today.getTime(),
                TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS));
    }
}