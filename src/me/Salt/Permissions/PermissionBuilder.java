package me.Salt.Permissions;

import me.Salt.SaltAPI.Util.Interface.Buildable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15122390 on 21/04/2017.
 */
public class PermissionBuilder implements Buildable {
    private String permission;
    private Perm permEnum;
    private Permission.Range range;
    private List<Permission> subperms = new ArrayList<>();
    private String description;
    private long id;

    public PermissionBuilder(Permission permission) {
        this.permission = permission.getPermission();
        this.permEnum = permission.getPermEnum();
        this.range = permission.getRange();
        this.subperms = permission.getSubperms();
        this.description = permission.getDescription();
        this.id = permission.getId();
    }

    public PermissionBuilder() {

    }

    public PermissionBuilder setPermission(String permission) {
        this.permission = permission.toLowerCase();
        //TODO add algorithm to determine what permissions this permission is nested under
        return this;
    }

    public PermissionBuilder setPermEnum(Perm permEnum) {
        this.permEnum = permEnum;
        return this;
    }

    public PermissionBuilder setRange(Permission.Range range) {
        this.range = range;
        return this;
    }

    public PermissionBuilder addSubperm(Permission subperm) {
        if (!this.subperms.contains(subperm)) this.subperms.add(subperm);
        return this;
    }

    public PermissionBuilder removeSubperm(Permission subperm) {
        if (this.subperms.contains(subperm)) this.subperms.remove(subperm);
        return this;
    }

    public PermissionBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Permission build() {
        return new Permission(permission, permEnum, range, subperms, description, id);
    }
}
