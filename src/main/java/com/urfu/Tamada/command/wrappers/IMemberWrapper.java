package com.urfu.Tamada.command.wrappers;

import net.dv8tion.jda.api.Permission;

import java.util.EnumSet;

public interface IMemberWrapper {
    EnumSet<Permission> getPermissions();
}