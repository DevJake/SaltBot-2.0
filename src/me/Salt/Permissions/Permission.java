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

import java.util.ArrayList;
import java.util.List;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 09/04/2017.
 */
public class Permission implements Identifiable, Describable {

    private String permission;
    private Perm permEnum;
    private Range range;
    private List<Permission> subperms = new ArrayList<>();
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

    /**
     * GLOBAL_WIDE -&gt; GUILD_WIDE -&gt; TEXTCHANNEL_WIDE -&gt; USER_WIDE
     * Range.ALL will instruct the PermissionHandler to add a new permission instance for each Range.
     * <p>
     * ALL - All below ranges
     * <br>
     * GLOBAL_WIDE - Permissions that affect a global scale
     * <br>
     * GUILD_WIDE - Permissions that affect a guild-wide scale
     * <br>
     * TEXTCHANNEL_WIDE - Permissions that affect a TextChannel-wide scale
     * <br>
     * USER_WIDE - Permissions that affect a User-wide scale.
     * <p>
     * For example:
     * A guild with a permission with a range of GUILD_WIDE will have the permission applied to every user in that guild.
     * If that permission was changed to TEXTCHANNEL_WIDE, the permission would apply to all users who use a specific TextChannel.
     * If the permission was further changed to being USER_WIDE, the permission must be applied on a user-by-user basis.
     * <p>
     * Permission ranges can be applied to users, groups of users, or guilds.
     */
    public enum Range {
        /**
         * All ranges (not including this range)
         */
        ALL,

        /**
         * Permissions that affect a global scale
         */
        GLOBAL_WIDE,

        /**
         * Permissions that affect a guild-wide scale
         */
        GUILD_WIDE,

        /**
         * Permissions that affect a TextChannel-wide scale
         */
        TEXTCHANNEL_WIDE,

        /**
         * Permissions that affect a User-wide scale.
         */
        USER_WIDE,
    }
}