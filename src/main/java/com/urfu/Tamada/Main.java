package com.urfu.Tamada;

import com.google.gson.Gson;
import com.urfu.Tamada.IO.Reader;
import com.urfu.Tamada.IO.Writer;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.DailyAnecdote;
import com.urfu.Tamada.command.Translator;
import com.urfu.Tamada.command.database.Data;
import com.urfu.Tamada.command.database.DataBase;
import com.urfu.Tamada.events.CommandController;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.GroupAuthResponse;
import com.vk.api.sdk.objects.UserAuthResponse;
import net.dv8tion.jda.api.JDA;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import netscape.javascript.JSObject;

import javax.sound.midi.Track;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Main {
    public static void main(String[] args) throws Exception {

        var bot = JDABuilder.createDefault(Config.getToken("DS_TOKEN"))
                .setChunkingFilter(ChunkingFilter.ALL) // enable member chunking for all guilds
                .setMemberCachePolicy(MemberCachePolicy.ALL) // ignored if chunking enabled
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new CommandController())
                .setActivity(Activity.watching("апас."))
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
        new Reader();
        new Writer();
        bot.getGuilds().stream().filter(ch -> ch.getTextChannelsByName("zen", true) != null)
        .forEach(ch -> ch.createTextChannel("zen").queue());
        setTimerAnecdote(bot);
        setTimerZen(bot);
        new VkProgramMemes(bot).execute();
    }

    private static void setTimerZen(JDA bot){
        new Timer().schedule(new VkProgramMemes(bot), MILLISECONDS.convert(10, MINUTES));
    }

    private static void setTimerAnecdote(JDA jda){
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