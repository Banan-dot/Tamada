package com.urfu.Tamada.command.wrappers;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.util.EnumSet;

public class MemberWrapper implements IMemberWrapper {
    private final Member member;

    public MemberWrapper(Member member) {
        this.member = member;
    }

    public EnumSet<Permission> getPermissions() {
        return member.getPermissions();
    }
}