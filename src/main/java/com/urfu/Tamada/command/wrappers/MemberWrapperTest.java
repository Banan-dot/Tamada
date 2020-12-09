package com.urfu.Tamada.command.wrappers;


import net.dv8tion.jda.api.Permission;

import java.util.EnumSet;

public class MemberWrapperTest implements IMemberWrapper {
    private EnumSet<Permission> permissions;

    public void setPermissions(EnumSet<Permission> permissions){
        this.permissions = permissions;
    }

    @Override
    public EnumSet<Permission> getPermissions() {
        return permissions;
    }
}