/*
 * Copyright (c) 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
