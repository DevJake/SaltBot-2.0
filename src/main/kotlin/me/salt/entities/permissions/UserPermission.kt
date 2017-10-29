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

package me.salt.entities.permissions

import com.fasterxml.jackson.annotation.JsonProperty

class UserPermission {
    @JsonProperty("User ID")
    val userId: String
    @JsonProperty("Groups")
    val groups: List<String>?
    @JsonProperty("Permissions")
    val permissions: List<Node>?
    @JsonProperty("Enforcements")
    val enforcements: List<Node>?

    constructor(userId: String, groups: List<String>?, permissions: List<Node>?, enforcements: List<Node>?) {
        this.userId = userId
        this.groups = groups
        this.permissions = permissions
        this.enforcements = enforcements

        permissions?.forEach { p -> p.type = Node.NodeType.PERMISSION }
        enforcements?.forEach { e -> e.type = Node.NodeType.ENFORCEMENT }
    }

    override fun toString(): String {
        return "UserPermission(userId='$userId', groups=$groups, permissions=$permissions, enforcements=$enforcements)"
    }

}