package me.salt.config.entities

import com.fasterxml.jackson.annotation.JsonProperty
import me.salt.GroupPermission
import me.salt.SelfAssignableRole
import me.salt.UserPermission

class PermissionMap {
    @JsonProperty("Groups")
    val groups: List<GroupPermission>?
    @JsonProperty("Users")
    val users: List<UserPermission>?

    constructor(groups: List<GroupPermission>?, users: List<UserPermission>?) {
        this.groups = groups
        this.users = users
    }
}

class RolesMap {
    var roles: List<SelfAssignableRole>?

    constructor(roles: List<SelfAssignableRole>?) {
        this.roles = roles
    }
}

/*
No requirement for multiple RolesMap types, as they're consistent across a SaltRolesMap, Guild and TextChannel alternatives
 */

class LevellingMap
class FilteringMap
