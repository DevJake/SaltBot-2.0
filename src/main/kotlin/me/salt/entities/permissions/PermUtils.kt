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

import me.salt.entities.config.Configs
import me.salt.entities.config.Handler
import me.salt.entities.config.entities.PermissionMap
import me.salt.entities.objects.Action
import me.salt.entities.objects.getConfig
import me.salt.util.events.PermissionCheckEvent
import me.salt.util.events.PermissionRegisterEvent
import me.salt.util.events.PermissionUnregisterEvent
import me.salt.util.events.fireEvent
import me.salt.util.exception.ConfigMissingValueException
import me.salt.util.exception.exception
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.entities.User

object PermUtils {
    private val permissions: MutableList<Node> = mutableListOf()

    fun registerPermission(vararg nodes: Node) {
        this.permissions.addAll(nodes)
        fireEvent(PermissionRegisterEvent(nodes.toList()))
    }

    fun unregisterPermission(vararg nodes: Node) {
        this.permissions.removeAll(nodes)
        fireEvent(PermissionUnregisterEvent(nodes.toList()))
    }

    private fun getAuthHandler(level: Authority.Level, entityId: String?): Handler {
        val tempEntityId: String =
                if (entityId == null && level != Authority.Level.BOT) {
                    exception(ConfigMissingValueException()); ""
                } else entityId.toString()

        return when (level) {
            Authority.Level.BOT -> Configs.salt.PERMISSIONS_MAP
            Authority.Level.GUILD -> Configs.guild(tempEntityId).PERMISSIONS_MAP
            Authority.Level.CHANNEL -> Configs.textChannel(tempEntityId).PERMISSIONS_MAP
            else -> {
                exception(ConfigMissingValueException())
                return Handler(emptyList(), null)
            } //TODO Replace with exception
        }
    }

    private fun checkHasPerms(level: Authority.Level, nodes: List<Node>, entityId: String?, user: User): Boolean {
        fireEvent(PermissionCheckEvent(level, nodes, entityId, user))
        //TODO check that group permissions are checked across a group's specified super groups
        //TODO check asterisk modifier on permissions works
        var hasNodes = false

        val permMap = getAuthHandler(level, entityId).getConfig(PermissionMap::class.java)

        if (permMap?.users?.any { it.userId == user.id } == true) {

            val userPerm: UserPermission = permMap.users?.first { it.userId == user.id } ?: return false

            if (userPerm.permissions != null) {
                hasNodes =
                        userPerm.permissions.mapTo(mutableListOf(), { it.node })
                                .containsAll(nodes.mapTo(mutableListOf(), { it.node }))
            }
            //If the user has a series of permissions, determine if it contains all of the specified nodes

            if (!hasNodes) { //Time to check groups
                if (userPerm.groups == null) return false //No groups; no perms
                val groupsToCheck = permMap?.groups?.filter { userPerm.groups.contains(it.groupName) }

                val indistinctPermList = nodes.mapTo(mutableListOf(), { it.node })
                indistinctPermList.removeAll(userPerm.permissions?.mapTo(mutableListOf(), { it.node }) ?: return false)

                /*
                Get the permissions the user -should- have, and remove all of those they actually -do- have.
                This gives us another list of permissions they're still missing.
                 */

                groupsToCheck?.forEach { group ->
                    indistinctPermList
                            .removeAll(group.permissions?.mapTo(mutableListOf(), { it.node }) ?:
                                    { exception(ConfigMissingValueException()); emptyList<String>() } ())
                }
                return indistinctPermList.isEmpty()
                /*
                After removing the assigned permissions from each group,
                our list of still-required permissions should be empty.
                 */
            }
        }
        return hasNodes
    }

    fun hasGuildPermissions(user: User, guild: Guild, nodes: List<Node>, checkGroups: Boolean = true): Boolean =
            checkHasPerms(Authority.Level.GUILD, nodes, guild.id, user)

    fun hasChannelPermissions(user: User, channel: TextChannel, nodes: List<Node>, checkGroups: Boolean = true): Boolean =
            checkHasPerms(Authority.Level.CHANNEL, nodes, channel.id, user)

    fun hasBotPermissions(user: User, nodes: List<Node>, checkGroups: Boolean = true): Boolean =
            checkHasPerms(Authority.Level.BOT, nodes, null, user)

    fun hasGuildAuthority(user: User, guild: Guild, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
            hasGuildPermissions(user,
                    guild,
                    authority.getAuthorityNodes().mapTo(mutableListOf(), { Node(it) }),
                    checkGroups)

    fun hasChannelAuthority(user: User, channel: TextChannel, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
            hasChannelPermissions(user,
                    channel,
                    authority.getAuthorityNodes().mapTo(mutableListOf(), { Node(it) }),
                    checkGroups)

    fun hasBotAuthority(user: User, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
            hasBotPermissions(user, authority.getAuthorityNodes().mapTo(mutableListOf(), { Node(it) }), checkGroups)

    fun canPerformGuildAction(user: User, guild: Guild, action: Action, checkGroups: Boolean = true) =
            hasGuildPermissions(user, guild, listOf(action.node), checkGroups)

    fun canPerformChannelAction(user: User, channel: TextChannel, action: Action, checkGroups: Boolean = true) =
            hasChannelPermissions(user, channel, listOf(action.node), checkGroups)

    fun canPerformBotAction(user: User, action: Action, checkGroups: Boolean = true) =
            hasBotPermissions(user, listOf(action.node), checkGroups)
}