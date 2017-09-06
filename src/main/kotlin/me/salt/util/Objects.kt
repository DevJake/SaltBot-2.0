/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.util

import com.fasterxml.jackson.annotation.JsonProperty

data class Module(val name: String, val description: String?, val enabled: Boolean)
data class Admin(val name: String, val id: String)

data class SelfAssignableRole(val name: String?, val description: String?, val enabled: Boolean?, val restrictedUsers: List<String>?, var permissionGroups: List<String>?, var tiedRoles/*Roles given when this role is acquired. Offers an internal link to Discord's roles*/: List<String>?, var requiredRoles: List<String>?)

class GroupPermission {
    @JsonProperty("Group name")
    val groupName: String?
    @JsonProperty("Permissions")
    val permissions: MutableList<String>?
    @JsonProperty("Enforcements")
    val enforcements: MutableList<String>?
    @JsonProperty("Apply to all Users?")
    val applyToAllUsers: Boolean?
    @JsonProperty("Apply to all Users with role")
    val applyToUsersWithRole: MutableList<PermRole>?

    constructor(groupName: String?, permissions: MutableList<String>?, enforcements: MutableList<String>?, applyToAllUsers: Boolean?, applyToUsersWithRole: MutableList<PermRole>?) {
        this.groupName = groupName
        this.permissions = permissions
        this.enforcements = enforcements
        this.applyToAllUsers = applyToAllUsers
        this.applyToUsersWithRole = applyToUsersWithRole
    }
}

class UserPermission {
    @JsonProperty("User ID")
    val userId: String?
    @JsonProperty("Groups")
    val groups: MutableList<String>?
    @JsonProperty("Permissions")
    val permissions: MutableList<String>?
    @JsonProperty("Enforcements")
    val enforcements: MutableList<String>?

    constructor(userId: String?, groups: MutableList<String>?, permissions: MutableList<String>?, enforcements: MutableList<String>?) {
        this.userId = userId
        this.groups = groups
        this.permissions = permissions
        this.enforcements = enforcements
    }
}

data class PermRole(val roleName: String?, val requirementState: RoleRequirementState?)
enum class RoleRequirementState {
    /**
     * The individual -must- have this role
     */
    REQUIRED,
    /**
     * The individual -must not- have this role
     */
    UNREQUIRED,
    /**
     * The individual is unaffected, regardless of having this role or not
     */
    OPTIONAL,
    /**
     * The individual must have this role, if they have no other role with a state of [REQUIRED] or [REQUIRED_OPTIONAL]
     */
    REQUIRED_OPTIONAL,
    /**
     * The individual -must not- have this role, unless they also lack any other roles marked as [UNREQUIRED] or [UNREQUIRED_OPTIONAL]; of a range of roles, they must lack at least one
     */
    UNREQUIRED_OPTIONAL;
}

enum class LevelRange {
    GLOBAL,
    GUILD,
    TEXTCHANNEL;
}

enum class Colour(val red: Int, val green: Int, val blue: Int) {
    RED(255, 0, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    YELLOW(0, 255, 255);
}

enum class Entity {
    GLOBAL,
    GUILD,
    TEXTCHANNEL,
    USER;
}

data class FilterMap(val filterTerm: String, val strictCasing: Boolean, val matchRepeatLetters: Boolean, val matchFillerChars: Boolean, val matchLeetAlts: Boolean)

data class LevellingPointComponent(val pointMethod: PointMethod, val value: Int) {
    enum class PointMethod {
        STANDARD_MESSAGE,
        MESSAGE_WITH_URL,
        MESSAGE_WITH_INVITE,
        MESSAGE_WITH_EMOJI,
        URL,
        INVITE,
        EMOJI,
        IMAGE,
        GIF,
        VIDEO;
    }
}

enum class TrackableStats {
    MESSAGE_SEND,
    MESSAGE_EDIT,
    MESSAGE_DELETE,
    GUILD_JOIN,
    GUILD_LEAVE,
    REACTION_ADD,
    REACTION_REMOVE,
    TYPING_BEGIN,
    TYPING_END,
    ACTIVITY_STATUS_ONLINE,
    ACTIVITY_STATUS_OFFLINE,
    ACTIVITY_STATUS_DO_NOT_DISTURB,
    VOICECHANNEL_JOIN,
    VOICECHANNEL_LEAVE,
    PLAY_STATUS_UPDATE;
}

enum class Modules {
    //TODO a list of modules, complete with their matching Module instance
}