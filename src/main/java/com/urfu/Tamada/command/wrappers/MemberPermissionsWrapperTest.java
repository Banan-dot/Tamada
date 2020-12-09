package com.urfu.Tamada.command.wrappers;


import net.dv8tion.jda.api.Permission;

import java.util.ArrayList;
import java.util.EnumSet;

public class MemberPermissionsWrapperTest implements IMemberPermissionsWrapper {
    private ArrayList<Permission> permissions;

    public void setPermissions(ArrayList<Permission> permissions){
        this.permissions = permissions;
    }

    @Override
    public ArrayList<Permission> getPermissions() {
        return permissions;
    }
}