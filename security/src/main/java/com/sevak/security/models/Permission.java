package com.sevak.security.models;

public enum Permission {
    PERMISSION_READ("user:read"),
    PERMISSION_WRITE("user:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
