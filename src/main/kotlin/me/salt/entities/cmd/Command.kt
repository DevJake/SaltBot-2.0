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
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

/**
 * This class serves to be implemented by commands, such as the [HelloWorldCommand][me.salt.entities.cmd.commands.HelloWorldCommand].
 *
 * For a class to be able to use [CommandRegistry.register], it must implement this abstract class.
 *
 * Within this class are three methods:
 * - [preExecute]
 * - [execute]
 * - [postExecute]
 *
 * These methods are executed in this order, and aim to perform the following respective tasks:
 * - Analysing arguments and user input
 * - Executing the main bulk of the code, generating a response and working with verified parameters
 * - Cleaning up code that has been left behind (closing any network connections, shutting off worker threads, etc.)
 *
 * @constructor Stores important information about the command required for analysis and processing
 * @property cmdPrefix The prefix given to this command
 * @property aliases A list of aliases that should also be identified for this command. Allows extending of [cmdPrefix]
 * @property name The name of this command
 * @property description The description of this command
 * @property author The author of this command
 * @property perms The permissions required by an individual to call upon this command. This is different to any internal permission checks performed by the command, and should instead be used to deny complete calling of a command if the user lacks the given permissions
 */
abstract class Command(
        val cmdPrefix: String,
        val aliases: MutableList<String> = mutableListOf(),
        val name: String,
        val description: String = "",
        val author: String = "",
        val perms: List<Node> = emptyList()
) {
    /**
     * @param cmd - A [CommandContainer][me.salt.entities.cmd.CommandParser.CommandContainer] generated in respect to the user input
     * @param event - The [GuildMessageReceivedEvent] which relates to the user input; the event fired from the user inputting the command matching to the given implementation of [me.salt.entities.cmd.Command]
     * @param instHandler - [CommandParser.CmdInstanceHandle] An instance of CmdInstanceHandle used to allow the [CommandListener][me.salt.entities.cmd.CommandListener] class to handle the order of execution
     */
    abstract fun preExecute(cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CommandParser.CmdInstanceHandle): CommandParser.CmdInstanceHandle

    /**
     * @param cmd - A [CommandContainer][me.salt.entities.cmd.CommandParser.CommandContainer] generated in respect to the user input
     * @param event - The [GuildMessageReceivedEvent] which relates to the user input; the event fired from the user inputting the command matching to the given implementation of [me.salt.entities.cmd.Command]
     * @param instHandler - [CommandParser.CmdInstanceHandle] An instance of CmdInstanceHandle used to allow the [CommandListener][me.salt.entities.cmd.CommandListener] class to handle the order of execution
     */
    abstract fun execute(cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CommandParser.CmdInstanceHandle): CommandParser.CmdInstanceHandle

    /**
     * @param cmd - A [CommandContainer][me.salt.entities.cmd.CommandParser.CommandContainer] generated in respect to the user input
     * @param event - The [GuildMessageReceivedEvent] which relates to the user input; the event fired from the user inputting the command matching to the given implementation of [Command][me.salt.entities.cmd.Command]
     */
    abstract fun postExecute(cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent)
}