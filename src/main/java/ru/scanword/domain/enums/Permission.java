package ru.scanword.domain.enums;

public enum Permission {
    READ("read"),
    SAVE("save"),
    CREATE("write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
