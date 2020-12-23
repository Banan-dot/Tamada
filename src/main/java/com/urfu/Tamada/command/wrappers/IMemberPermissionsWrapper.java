package com.urfu.Tamada.command.wrappers;

import net.dv8tion.jda.api.Permission;

import java.util.ArrayList;

public interface IMemberPermissionsWrapper {
    ArrayList<Permission> getPermissions();
}