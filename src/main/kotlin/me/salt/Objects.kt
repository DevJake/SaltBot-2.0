package me.salt

import com.fasterxml.jackson.annotation.JsonProperty

data class Module(val name: String?, val description: String?, val enabled: Boolean)
data class Admin(val name: String?, val id: String?)

data class SelfAssignableRole(val name: String?, val description: String?, val enabled: Boolean?, val restrictedUsers: List<String>?, var permissionGroups: List<String>?, var tiedRoles/*Roles given when this role is acquired. Offers a link to Discord's roles*/: List<String>?, var requiredRoles: List<String>?)

class GroupPermission {
    @JsonProperty("Group name")
    val groupName: String?
    @JsonProperty("Permissions")
    val permissions: List<String>?

    constructor(groupName: String?, permissions: List<String>?) {
        this.groupName = groupName
        this.permissions = permissions
    }
}

class UserPermission {
    @JsonProperty("User ID")
    val userId: String?
    @JsonProperty("Groups")
    val groups: List<String>?
    @JsonProperty("Permissions")
    val permissions: List<String>?

    constructor(userId: String?, groups: List<String>?, permissions: List<String>?) {
        this.userId = userId
        this.groups = groups
        this.permissions = permissions
    }
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