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

import me.salt.exception.BuilderValueException
import java.util.regex.Pattern

class UserPermissionBuilder(val userId: String) {

    init {
        if (Pattern.compile("\\D").matcher(userId).matches())
            throw BuilderValueException("The specified userId contains non-numerical characters!")
    }

    private var groups = mutableListOf<String>()
        private set
    private var permissions = mutableListOf<Node>() //TODO default permissions, such as permission to send a message to any channel?
        private set
    private var enforcements = mutableListOf<Node>()
        private set

    fun build(): UserPermission = UserPermission(userId, groups, permissions, enforcements)

    fun addGroups(vararg groupTitles: String) = apply { groups.addAll(groupTitles) }
    fun removeGroups(vararg groupTitles: String) = apply { groups.removeAll(groupTitles) }

    fun addPermissions(vararg nodes: Node) = apply { permissions.addAll(nodes) }
    fun removePermissions(vararg nodes: Node) = apply { permissions.removeAll(nodes) }

    fun addEnforcements(vararg nodes: Node) = apply { enforcements.addAll(nodes) }
    fun removeEnforcements(vararg nodes: Node) = apply { enforcements.removeAll(nodes) }
}

class GroupPermissionBuilder {

}