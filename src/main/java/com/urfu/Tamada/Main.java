package com.urfu.Tamada;

import com.urfu.Tamada.IO.Reader;
import com.urfu.Tamada.IO.Writer;
import com.urfu.Tamada.command.DailyAnecdote;
import com.urfu.Tamada.command.database.Data;
import com.urfu.Tamada.command.database.DataBase;
import com.urfu.Tamada.command.translator.Translator;
import com.urfu.Tamada.command.zen.Subscriber;
import com.urfu.Tamada.events.CommandController;
import com.urfu.Tamada.vk.VK;
import com.urfu.Tamada.vk.VkProgramMemes;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.util.Calendar;
import java.util.Timer;

import static java.util.concurrent.TimeUnit.*;

public class Main {
    public static void main(String[] args) throws Exception {
        var bot = JDABuilder.createDefault(Config.getToken("DS_TOKEN"))
                .setChunkingFilter(ChunkingFilter.ALL) // enable member chunking for all guilds
                .setMemberCachePolicy(MemberCachePolicy.ALL) // ignored if chunking enabled
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new CommandController())
                .setActivity(Activity.watching("ооп."))
                .build()
                .awaitReady();
        init(bot);
    }

    private static void init(JDA bot) throws Exception {
        var database = new DataBase(Config.getUrl());
        Data.setDatabase(database);
        new BanList().fillBanList();
        new Translator().fillHashMap();
        new Sender("ru");
        new Subscriber();
        new Reader();
        new Writer();
        new VK(bot);
        bot.getGuilds().stream().filter(ch -> {
            ch.getTextChannelsByName("zen", true);
            return false;
        })
                .forEach(ch -> ch.createTextChannel("zen").queue());
        new VkProgramMemes(bot).execute();
        //setTimerAnecdote(bot);
        setTimerZen(bot);
    }

    private static void setTimerZen(JDA bot) {
        var fiveMinutes = 60 * 1000 * 5;
        new Timer().schedule(new VkProgramMemes(bot), fiveMinutes);
    }

    private static void setTimerAnecdote(JDA jda) {
        var hour = 8;
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, hour);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        new Timer().schedule(new DailyAnecdote(jda),
                today.getTime(),
                MILLISECONDS.convert(1, DAYS));
    }
}