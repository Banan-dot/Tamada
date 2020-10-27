package com.urfu.Tamada.command;

import net.dv8tion.jda.api.entities.Guild;


public class Pair {
    private final net.dv8tion.jda.api.entities.Member member;
    private final Guild guild;

    public Pair(net.dv8tion.jda.api.entities.Member first, Guild second) {
        this.member = first;
        this.guild = second;
    }

    public net.dv8tion.jda.api.entities.Member getMember() {
        return member;
    }

    public Guild getGuild() {
        return guild;
    }
}