package com.urfu.Tamada.command.wrappers;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.util.ArrayList;
import java.util.EnumSet;

public class MemberPermissionsWrapper implements IMemberPermissionsWrapper {
    private final Member member;

    public MemberPermissionsWrapper(Member member) {
        this.member = member;
    }

    public ArrayList<Permission> getPermissions() {
        var permissionsSet = member.getPermissions();
        return new ArrayList<>(permissionsSet);
    }
}