package com.urfu.Tamada;

import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class JDABot {
    public static void main(String[] args) throws Exception {
        //var alias = new Alias();
        //alias.execute();
        JDABuilder.createDefault(Config.getToken("TOKEN"))
                .setChunkingFilter(ChunkingFilter.ALL) // enable member chunking for all guilds
                .setMemberCachePolicy(MemberCachePolicy.ALL) // ignored if chunking enabled
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new CommandController())
                .setActivity(Activity.watching("в будущее России"))
                .build();
        System.out.println("Бот запустился...");
    }
}
