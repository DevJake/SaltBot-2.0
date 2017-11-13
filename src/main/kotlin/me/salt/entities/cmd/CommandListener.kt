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

import me.salt.util.logging.logDebug
import me.salt.util.logging.logInfo
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

/**
 * This command listens for a range of events in relation to the Command System, and handles them accordingly.
 *
 * Namely, this class identifies [Commands][Command] from a [GuildMessageReceivedEvent], and fires any respective handlers.
 */
class CommandListener : ListenerAdapter() {

    /**
     * This method filters a [MutableList] of [Commands][Command] by the given prefix.
     *
     * @param prefix - The prefix to be used for filtering
     * @param checkAliases - If the filter should also check the current command's aliases
     *
     * @return A list of [Commands][Command] filtered by the given [prefix] and the optional [alias check][checkAliases]
     *
     * @see Command
     */
    fun MutableList<Command>.filterByCommandPrefix(prefix: String, checkAliases: Boolean = false): List<Command> =
            this.filter {
                it.cmdPrefix.equals(prefix) &&
                        (if (checkAliases)
                            it.aliases.map { it.toLowerCase() }.contains(prefix)
                        else true)
            }

    /**
     * This method receives information about any messages sent to a guild.
     * As a result, this method handles the analyses of the incoming message's prefix
     * (performed through [CommandParser.isPotentialCommand]), and identifies any applicable commands.
     * If any commands are identified, their respective [preExecute][Command.preExecute],
     * [execute][Command.execute] and [postExecute][Command.postExecute] methods are invoked.
     *
     * @see Command
     */
    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent?) {
        if (CommandParser.isPotentialCommand(event?.message?.rawContent ?: return)) {
            logInfo("Received a potential command from user ${event.author} (ID: ${event.author.id})", "COMMAND")
            val cc = CommandParser.parse(event.message.rawContent, event.guild.id, event.channel.id, event.author.id)
            val filteredCommands = CommandRegistry.getCommands().filterByCommandPrefix(cc.beheadedLiteralLower)

            filteredCommands.forEach {
                if (it.preExecute == null) {
                    logDebug("Didn't call null-value preExecute method of $it", "COMMAND")
                } else {
                    it.preExecute.invoke(cc, event, CmdInstanceHandle(it))
                    logDebug("Called preExecute method of $it", "COMMAND")
                }
            }
            filteredCommands.forEach {
                if (it.execute == null) {
                    logDebug("Didn't call null-value execute method of $it", "COMMAND")
                } else {
                    it.execute.invoke(cc, event, CmdInstanceHandle(it))
                    logDebug("Called execute method of $it", "COMMAND")
                }
            }
            filteredCommands.forEach {
                if (it.postExecute == null) {
                    logDebug("Didn't call null-value postExecute method of $it", "COMMAND")
                } else {
                    it.postExecute.invoke(cc, event)
                    logDebug("Called postExecute method of $it", "COMMAND")
                }
            }

        }
    }
}
