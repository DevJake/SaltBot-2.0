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
        val aliases: MutableList<String> = mutableListOf(),
        val name: String,
        val description: String = "",
        val author: String = "",
        val perms: List<Node> = emptyList(),

        val preExecute: (cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CmdInstanceHandle) -> Command,
        val execute: (cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CmdInstanceHandle) -> Command,
        val postExecute: (cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent) -> Unit
) {

    init {
        CommandRegistry.register(this)
    }
}

class CommandBuilder(private var cmdPrefix: String, private var name: String) {
    private var aliases: MutableList<String> = mutableListOf()
    private var description: String = ""
    private var author: String = ""
    private var perms: List<Node> = emptyList()

    private lateinit var preExecute: (cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CmdInstanceHandle) -> Command
    private lateinit var execute: (cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CmdInstanceHandle) -> Command
    private lateinit var postExecute: (cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent) -> Unit

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

    fun build() = Command(cmdPrefix, aliases, name, description, author, perms, preExecute, execute, postExecute)

    fun setCmdPrefix(prefix: String) = apply { cmdPrefix = prefix }
    fun setName(name: String) = apply { this.name = name }
    fun setDescription(description: String) = apply { this.description = description }
    fun addAlias(vararg aliases: String) = apply { this.aliases.addAll(aliases) }
}

class CmdInstanceHandle internal constructor(private val cmd: Command) {
    var accepts: Boolean = false
        private set

    fun accept(): Command = kotlin.run { accepts = true; cmd }
    fun accept(callback: () -> (Unit)) = kotlin.run { accepts = true; callback.invoke(); cmd }

    fun reject(e: Exception) = ExceptionHandler.handle(e)
    fun reject(e: Exception, level: Errorlevel) = ExceptionHandler.handle(e, level)
}