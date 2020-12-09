package com.urfu.Tamada.command.permissions;

import com.urfu.Tamada.command.wrappers.IMemberWrapper;
import com.urfu.Tamada.command.Command;
import net.dv8tion.jda.api.Permission;

public class PermissionCheck {
    private final Command command;
    private StatesOfPermissionsCheck permissionCheckState;

    public PermissionCheck(Command command) {
        this.command = command;
        permissionCheckState = StatesOfPermissionsCheck.UNDEFINED;
    }

    public void CheckMaster(IMemberWrapper member) {
        var memberPermissions = member.getPermissions();
        if (command instanceof PermissionCommand)
            if (!memberPermissions.contains(Permission.ADMINISTRATOR))
                permissionCheckState = StatesOfPermissionsCheck.MASTER_NOT_ADMINISTRATOR;

    }

    public void CheckSlave(IMemberWrapper member) {
        var memberPermissions = member.getPermissions();
        if (command instanceof PermissionCommand)
            if (memberPermissions.contains(Permission.ADMINISTRATOR))
                permissionCheckState = StatesOfPermissionsCheck.SLAVE_IS_ADMINISTRATOR;
    }

    public StatesOfPermissionsCheck getPermissionCheckState() {
        if (permissionCheckState == StatesOfPermissionsCheck.UNDEFINED)
            permissionCheckState = StatesOfPermissionsCheck.OK;
        return permissionCheckState;
    }
}