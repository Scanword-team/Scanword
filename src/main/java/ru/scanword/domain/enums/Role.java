package ru.scanword.domain.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(new HashSet<Permission>(){{
        add(Permission.READ);
    }}),
    GUEST(new HashSet<Permission>(){{
        add(Permission.READ);
    }}),
    ADMIN(new HashSet<Permission>(){{
        add(Permission.READ);
        add(Permission.WRITE);
    }});

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
