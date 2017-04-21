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

import me.Salt.SaltAPI.Util.Interface.Describable;
import me.Salt.SaltAPI.Util.Interface.Identifiable;

import java.util.List;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 09/04/2017.
 */
public class Permission implements Identifiable, Describable {

    private String permission;
    private Perm permEnum;
    private Range range;
    private List<Permission> subperms;
    private String description;
    private long id;

    public Permission(String permission, Perm permEnum, Range range, List<Permission> subperms, String description, long id) {

        this.permission = permission;
        this.permEnum = permEnum;
        this.range = range;
        this.subperms = subperms;
        this.description = description;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permission='" + permission + '\'' +
                ", permEnum=" + permEnum +
                ", range=" + range +
                ", subperms=" + subperms.toString() +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }

    public String getPermission() {
        return permission;
    }

    public Perm getPermEnum() {
        return permEnum;
    }

    public Range getRange() {
        return range;
    }

    public List<Permission> getSubperms() {
        return subperms;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public long getId() {
        return id;
    }

    public enum Range {
        USER_WIDE,
        GUILD_WIDE,
        GLOBAL_WIDE
    }
}