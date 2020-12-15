package diplomski.autoceste.security;

public enum AutocesteUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ORGANIZATION_READ("organization:read"),
    ORGANIZATION_WRITE("organization:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    AutocesteUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return  this.permission;
    }
}
