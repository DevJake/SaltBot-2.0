package me.Salt.Permissions;

import me.Salt.SaltAPI.Util.Interface.Buildable;

import java.util.List;

/**
 * Created by 15122390 on 21/04/2017.
 */
public class PermissionBuilder implements Buildable {
    private String permission;
    private Perm permEnum;
    private Permission.Range range;
    private List<Permission> subperms;
    private String description;
    private long id;

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setPermEnum(Perm permEnum) {
        this.permEnum = permEnum;
    }

    public void setRange(Permission.Range range) {
        this.range = range;
    }

    public void addSubperm(Permission subperm) {
        if (!this.subperms.contains(subperm)) this.subperms.add(subperm);
    }

    public void removeSubperm(Permission subperm) {
        if (this.subperms.contains(subperm)) this.subperms.remove(subperm);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Permission build() {
        return new Permission(permission, permEnum, range, subperms, description, id);
    }
}
