package com.pcwang.entity;

import java.io.Serializable;

public class Role implements Serializable {

    private String roleNo;

    private String roleName;

    private String isUse;

    public Role() {
    }

    public Role(String roleNo, String roleName, String isUse) {
        this.roleNo = roleNo;
        this.roleName = roleName;
        this.isUse = isUse;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleNo='" + roleNo + '\'' +
                ", roleName='" + roleName + '\'' +
                ", isUse='" + isUse + '\'' +
                '}';
    }
}
