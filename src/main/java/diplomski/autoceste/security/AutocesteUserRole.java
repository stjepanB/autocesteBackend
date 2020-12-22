package diplomski.autoceste.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static diplomski.autoceste.security.AutocesteUserPermission.*;


public enum AutocesteUserRole {

    ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE, USER_READ, USER_WRITE, ORGANIZATION_READ, ORGANIZATION_WRITE)),
    USER(Sets.newHashSet(USER_WRITE, USER_READ)),
    ORGANIZATION(Sets.newHashSet(ORGANIZATION_READ, ORGANIZATION_WRITE));

    private final Set<AutocesteUserPermission> permissions;

    AutocesteUserRole(Set<AutocesteUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AutocesteUserPermission> getPermissions() {
        return this.permissions;
    }

    public Set<SimpleGrantedAuthority> getGrauntedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
