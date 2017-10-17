/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.permissions

import com.fasterxml.jackson.annotation.JsonProperty
import me.salt.objects.PermRole

class GroupPermission {
    @JsonProperty("Group name")
    val groupName: String
    @JsonProperty("Permissions")
    val permissions: MutableList<Node>?
    @JsonProperty("Enforcements")
    val enforcements: MutableList<Node>?
    @JsonProperty("Groups")
    val groups: MutableList<String>?
    @JsonProperty("Apply to all Users?")
    val applyToAllUsers: Boolean
    @JsonProperty("Apply to all Users with role")
    val applyToUsersWithRole: List<PermRole>?

    constructor(
            groupName: String,
            permissions: MutableList<Node>?,
            enforcements: MutableList<Node>?,
            groups: MutableList<String>?,
            applyToAllUsers: Boolean = false,
            applyToUsersWithRole: List<PermRole>?
    ) {
        this.groupName = groupName
        this.permissions = permissions
        this.enforcements = enforcements
        this.groups = groups
        this.applyToAllUsers = applyToAllUsers
        this.applyToUsersWithRole = applyToUsersWithRole

        permissions?.forEach { p -> p.type = Node.NodeType.PERMISSION }
        enforcements?.forEach { e -> e.type = Node.NodeType.ENFORCEMENT }
    }

    override fun toString(): String {
        return "GroupPermission(groupName='$groupName', permissions=$permissions, enforcements=$enforcements, groups=$groups, applyToAllUsers=$applyToAllUsers, applyToUsersWithRole=$applyToUsersWithRole)"
    }

}