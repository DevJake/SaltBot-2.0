/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.permissions

import me.salt.Main
import me.salt.objects.Action
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.entities.User

fun User.hasGuildPermissions(guild: Guild, vararg nodes: String, checkGroups: Boolean = true) =
        PermUtils.hasGuildPermissions(this, guild, nodes.mapTo(mutableListOf(), { Node(it) }), checkGroups)
fun User.hasGuildPermissions(guild: Guild, vararg nodes: Node, checkGroups: Boolean = true) =
        PermUtils.hasGuildPermissions(this, guild, nodes.toList(), checkGroups)
fun User.hasGuildPermissions(guildId: String, vararg nodes: String, checkGroups: Boolean = true) =
        PermUtils.hasGuildPermissions(this, Main.jda.getGuildById(guildId), nodes.mapTo(mutableListOf(), { Node(it) }), checkGroups)
fun User.hasGuildPermissions(guildId: String, vararg nodes: Node, checkGroups: Boolean = true) =
        PermUtils.hasGuildPermissions(this, Main.jda.getGuildById(guildId), nodes.toList(), checkGroups)

fun User.hasChannelPermissions(textChannel: TextChannel, vararg nodes: String, checkGroups: Boolean = true) =
        PermUtils.hasChannelPermissions(this, textChannel, nodes.mapTo(mutableListOf(), { Node(it) }), checkGroups)
fun User.hasChannelPermissions(textChannel: TextChannel, vararg nodes: Node, checkGroups: Boolean = true) =
        PermUtils.hasChannelPermissions(this, textChannel, nodes.toList(), checkGroups)
fun User.hasChannelPermissions(textChannelId: String, vararg nodes: String, checkGroups: Boolean = true) =
        PermUtils.hasChannelPermissions(this, Main.jda.getTextChannelById(textChannelId), nodes.mapTo(mutableListOf(), { Node(it) }), checkGroups)
fun User.hasChannelPermissions(textChannelId: String, vararg nodes: Node, checkGroups: Boolean = true) =
        PermUtils.hasChannelPermissions(this, Main.jda.getTextChannelById(textChannelId), nodes.toList(), checkGroups)

fun User.hasBotPermissions(vararg nodes: String, checkGroups: Boolean = true) =
        PermUtils.hasBotPermissions(this, nodes.mapTo(mutableListOf(), { Node(it) }), checkGroups)
fun User.hasBotPermissions(vararg nodes: Node, checkGroups: Boolean = true) =
        PermUtils.hasBotPermissions(this, nodes.toList(), checkGroups)



fun Guild.hasPermissions(userId: String, vararg nodes: String, checkGroups: Boolean = true) =
        PermUtils.hasGuildPermissions(Main.jda.getUserById(userId), this, nodes.mapTo(mutableListOf(), { Node(it) }), checkGroups)
fun Guild.hasPermissions(user: User, vararg nodes: String, checkGroups: Boolean = true) =
        PermUtils.hasGuildPermissions(user, this, nodes.mapTo(mutableListOf(), { Node(it) }), checkGroups)
fun Guild.hasPermissions(user: User, vararg nodes: Node, checkGroups: Boolean = true) =
        PermUtils.hasGuildPermissions(user, this, nodes.toList(), checkGroups)
fun Guild.hasPermissions(userId: String, vararg nodes: Node, checkGroups: Boolean = true) =
        PermUtils.hasGuildPermissions(Main.jda.getUserById(userId), this, nodes.toList(), checkGroups)

fun TextChannel.hasPermissions(userId: String, vararg nodes: String, checkGroups: Boolean = true) =
        PermUtils.hasChannelPermissions(Main.jda.getUserById(userId), this, nodes.mapTo(mutableListOf(), { Node(it) }), checkGroups)
fun TextChannel.hasPermissions(userId: String, vararg nodes: Node, checkGroups: Boolean = true) =
        PermUtils.hasChannelPermissions(Main.jda.getUserById(userId), this, nodes.toList(), checkGroups)
fun TextChannel.hasPermissions(user: User, vararg nodes: String, checkGroups: Boolean = true) =
        PermUtils.hasChannelPermissions(user, this, nodes.mapTo(mutableListOf(), { Node(it) }), checkGroups)
fun TextChannel.hasPermissions(user: User, vararg nodes: Node, checkGroups: Boolean = true) =
        PermUtils.hasChannelPermissions(user, this, nodes.toList(), checkGroups)

fun User.hasGuildAuthority(userId: String, guildId: String, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasGuildAuthority(Main.jda.getUserById(userId), Main.jda.getGuildById(guildId), authority, checkGroups)
fun User.hasGuildAuthority(userId: String, guild: Guild, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasGuildAuthority(Main.jda.getUserById(userId), guild, authority, checkGroups)
fun User.hasGuildAuthority(user: User, guildId: String, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasGuildAuthority(user, Main.jda.getGuildById(guildId), authority, checkGroups)
fun User.hasGuildAuthority(user: User, guild: Guild, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasGuildAuthority(user, guild, authority, checkGroups)

fun User.hasChannelAuthority(userId: String, textChannelId: String, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasChannelAuthority(Main.jda.getUserById(userId), Main.jda.getTextChannelById(textChannelId), authority, checkGroups)
fun User.hasChannelAuthority(userId: String, textChannel: TextChannel, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasChannelAuthority(Main.jda.getUserById(userId), textChannel, authority, checkGroups)
fun User.hasChannelAuthority(user: User, textChannelId: String, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasChannelAuthority(user, Main.jda.getTextChannelById(textChannelId), authority, checkGroups)
fun User.hasChannelAuthority(user: User, textChannel: TextChannel, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasChannelAuthority(user, textChannel, authority, checkGroups)

fun User.hasBotAuthority(user: User, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasBotAuthority(user, authority, checkGroups)
fun User.hasBotAuthority(userId: String, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasBotAuthority(Main.jda.getUserById(userId), authority, checkGroups)

fun Guild.hasAuthority(userId: String, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasGuildAuthority(Main.jda.getUserById(userId), this, authority, checkGroups)
fun Guild.hasAuthority(user: User, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasGuildAuthority(user, this, authority, checkGroups)

fun TextChannel.hasAuthority(userId: String, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasChannelAuthority(Main.jda.getUserById(userId), this, authority, checkGroups)
fun TextChannel.hasAuthority(user: User, authority: Authority.NodeAuthority, checkGroups: Boolean = true) =
        PermUtils.hasChannelAuthority(user, this, authority, checkGroups)

fun User.canPerformGuildAction(userId: String, guildId: String, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformGuildAction(Main.jda.getUserById(userId), Main.jda.getGuildById(guildId), action, checkGroups)
fun User.canPerformGuildAction(userId: String, guild: Guild, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformGuildAction(Main.jda.getUserById(userId), guild, action, checkGroups)
fun User.canPerformGuildAction(user: User, guildId: String, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformGuildAction(user, Main.jda.getGuildById(guildId), action, checkGroups)
fun User.canPerformGuildAction(user: User, guild: Guild, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformGuildAction(user, guild, action, checkGroups)

fun User.canPerformChannelAction(userId: String, textChannelId: String, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformChannelAction(Main.jda.getUserById(userId), Main.jda.getTextChannelById(textChannelId), action, checkGroups)
fun User.canPerformChannelAction(userId: String, textChannel: TextChannel, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformChannelAction(Main.jda.getUserById(userId), textChannel, action, checkGroups)
fun User.canPerformChannelAction(user: User, textChannelId: String, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformChannelAction(user, Main.jda.getTextChannelById(textChannelId), action, checkGroups)
fun User.canPerformChannelAction(user: User, textChannel: TextChannel, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformChannelAction(user, textChannel, action, checkGroups)

fun User.canPerformBotAction(user: User, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformBotAction(user, action, checkGroups)
fun User.canPerformBotAction(userId: String, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformBotAction(Main.jda.getUserById(userId), action, checkGroups)

fun Guild.canPerformAction(userId: String, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformGuildAction(Main.jda.getUserById(userId), this, action, checkGroups)
fun Guild.canPerformAction(user: User, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformGuildAction(user, this, action, checkGroups)

fun TextChannel.canPerformAction(userId: String, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformChannelAction(Main.jda.getUserById(userId), this, action, checkGroups)
fun TextChannel.canPerformAction(user: User, action: Action, checkGroups: Boolean = true) =
        PermUtils.canPerformChannelAction(user, this, action, checkGroups)