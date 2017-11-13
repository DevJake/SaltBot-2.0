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

package me.salt.entities.cmd

import me.salt.entities.permissions.Node
import me.salt.util.exception.Errorlevel
import me.salt.util.exception.ExceptionHandler
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

class Command(
        val cmdPrefix: String,
        val aliases: List<String> = emptyList(),
        val name: String,
        val description: String = "",
        val author: String = "",
        val perms: List<Node> = emptyList(),

        val preExecute: ((cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CmdInstanceHandle) -> Command)?,
        val execute: ((cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CmdInstanceHandle) -> Command)?,
        val postExecute: ((cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent) -> Unit)?
) {
    init {
        CommandRegistry.register(this)
    }

    override fun toString(): String {
        return "Command(cmdPrefix='$cmdPrefix', aliases=$aliases, name='$name', description='$description', author='$author', perms=$perms)"
    }

}

class CommandBuilder(private var cmdPrefix: String, private var name: String) {
    private var aliases: MutableList<String> = mutableListOf()
    private var description: String = ""
    private var author: String = ""
    private var perms: MutableList<Node> = mutableListOf()

    private var preExecute: ((cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CmdInstanceHandle) -> Command)? = null
    private var execute: ((cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CmdInstanceHandle) -> Command)? = null
    private var postExecute: ((cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent) -> Unit)? = null

    fun preExecute(block: (cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CmdInstanceHandle) -> Command): CommandBuilder {
        this.preExecute = block
        return this
    }

    fun execute(block: (cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CmdInstanceHandle) -> Command): CommandBuilder {
        this.execute = block
        return this
    }

    fun postExecute(block: (cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent) -> Unit): CommandBuilder {
        this.postExecute = block
        return this
    }

    fun build() = Command(cmdPrefix.toLowerCase(),
            aliases.map { it.toLowerCase() }.toList(),
            name,
            description,
            author,
            perms,
            preExecute,
            execute,
            postExecute)

    fun setCmdPrefix(prefix: String) = apply { cmdPrefix = prefix }
    fun setName(name: String) = apply { this.name = name }
    fun setDescription(description: String) = apply { this.description = description }
    fun addAlias(vararg aliases: String) = apply { this.aliases.addAll(aliases) }
    fun removeAlias(vararg aliases: String) = apply { this.aliases.removeAll(aliases) }
    fun setAuthor(author: String) = apply { this.author = author }
    fun addPerms(vararg nodes: Node) = apply { this.perms.addAll(nodes) }
    fun removePerms(vararg nodes: Node) = apply { this.perms.removeAll(nodes) }
}

class CmdInstanceHandle internal constructor(private val cmd: Command) {
    var accepts: Boolean = false
        private set
    var callback: (() -> Unit)? = null
        private set

    fun accept(): Command = kotlin.run { accepts = true; cmd }
    fun accept(callback: () -> (Unit)) = kotlin.run { accepts = true; this.callback = callback; cmd }

    fun reject(e: Exception) = kotlin.run { accepts = false; ExceptionHandler.handle(e) }
    fun reject(e: Exception, level: Errorlevel) = kotlin.run { accepts = false; ExceptionHandler.handle(e, level) }
}