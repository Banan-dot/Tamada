package com.urfu.Tamada.command.permissions;

import com.urfu.Tamada.command.wrappers.IMemberPermissionsWrapper;
import com.urfu.Tamada.command.wrappers.MemberPermissionsWrapperTest;
import com.urfu.Tamada.command.permissionCommands.KickMember;
import net.dv8tion.jda.api.Permission;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class PermissionCheckTest {

    public IMemberPermissionsWrapper getMemberByPermission(Permission permission) {
        var member = new MemberPermissionsWrapperTest();
        var memberPermissions = new ArrayList<Permission>();
        memberPermissions.add(permission);
        member.setPermissions(memberPermissions);
        return member;
    }

    @Test
    void testNormalSituation() {
        var activeMember = getMemberByPermission(Permission.ADMINISTRATOR);
        var passiveMember = getMemberByPermission(Permission.VIEW_CHANNEL);
        var permissionsCheck = new PermissionCheck(new KickMember());
        permissionsCheck.CheckActiveMember(activeMember);
        permissionsCheck.CheckPassiveMember(passiveMember);
        var checkState = permissionsCheck.getPermissionCheckState();
        assertEquals(StatesOfPermissionsCheck.OK, checkState);
    }

    @Test
    void testPassiveAdministrator() {
        var activeMember = getMemberByPermission(Permission.VIEW_CHANNEL);
        var passiveMember = getMemberByPermission(Permission.ADMINISTRATOR);
        var permissionsCheck = new PermissionCheck(new KickMember());
        permissionsCheck.CheckActiveMember(activeMember);
        permissionsCheck.CheckPassiveMember(passiveMember);
        var checkState = permissionsCheck.getPermissionCheckState();
        assertEquals(StatesOfPermissionsCheck.PASSIVE_MEMBER_IS_ADMINISTRATOR, checkState);
    }

    @Test
    void testActiveAndPassiveAdministrators() {
        var member = getMemberByPermission(Permission.ADMINISTRATOR);
        var permissionsCheck = new PermissionCheck(new KickMember());
        permissionsCheck.CheckActiveMember(member);
        permissionsCheck.CheckPassiveMember(member);
        var checkState = permissionsCheck.getPermissionCheckState();
        assertEquals(StatesOfPermissionsCheck.PASSIVE_MEMBER_IS_ADMINISTRATOR, checkState);
    }

    @Test
    void testAllNotAdministrators() {
        var member = getMemberByPermission(Permission.VIEW_CHANNEL);
        var permissionsCheck = new PermissionCheck(new KickMember());
        permissionsCheck.CheckActiveMember(member);
        permissionsCheck.CheckPassiveMember(member);
        var checkState = permissionsCheck.getPermissionCheckState();
        assertEquals(StatesOfPermissionsCheck.ACTIVE_MEMBER_NOT_ADMINISTRATOR, checkState);
    }
}