package com.urfu.Tamada.command.permissions;

import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.command.wrappers.IMemberPermissionsWrapper;
import net.dv8tion.jda.api.Permission;

public class PermissionCheck {
    private final Command command;
    private StatesOfPermissionsCheck permissionCheckState;

    public PermissionCheck(Command command) {
        this.command = command;
        permissionCheckState = StatesOfPermissionsCheck.UNDEFINED;
    }

    public void CheckActiveMember(IMemberPermissionsWrapper member) {
        var memberPermissions = member.getPermissions();
        if (command instanceof PermissionCommand)
            if (!memberPermissions.contains(Permission.ADMINISTRATOR))
                permissionCheckState = StatesOfPermissionsCheck.ACTIVE_MEMBER_NOT_ADMINISTRATOR;

    }

    public void CheckPassiveMember(IMemberPermissionsWrapper member) {
        var memberPermissions = member.getPermissions();
        if (command instanceof PermissionCommand)
            if (memberPermissions.contains(Permission.ADMINISTRATOR))
                permissionCheckState = StatesOfPermissionsCheck.PASSIVE_MEMBER_IS_ADMINISTRATOR;
    }

    public StatesOfPermissionsCheck getPermissionCheckState() {
        if (permissionCheckState == StatesOfPermissionsCheck.UNDEFINED)
            permissionCheckState = StatesOfPermissionsCheck.OK;
        return permissionCheckState;
    }
}