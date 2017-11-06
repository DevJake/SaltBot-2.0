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
import me.salt.util.exception.ExecuteCommandFailureException
import me.salt.util.exception.PostExecuteCommandFailureException
import me.salt.util.exception.PreExecuteCommandFailureException
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

abstract class Command(val cmdPrefix: String, val aliases: MutableList<String> = mutableListOf(), val name: String, val description: String = "", val author: String = "", val perms: List<Node> = emptyList()) {
    @Throws(PreExecuteCommandFailureException::class)
    abstract fun preExecute(cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent)

    @Throws(ExecuteCommandFailureException::class)
    abstract fun execute(cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent)

    @Throws(PostExecuteCommandFailureException::class)
    abstract fun postExecute(cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent)
}