package me.salt.entities.cmd

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class CommandListener : ListenerAdapter() {
    private fun MutableList<Command>.filterByCommandPrefix(prefix: String, checkAliases: Boolean = false): List<Command> =
            this.filter {
                it.cmdPrefix.equals(prefix) &&
                        (if (checkAliases)
                            it.aliases.map { it.toLowerCase() }.contains(prefix)
                        else true)
            }

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent?) {
        if (CommandParser.isPotentialCommand(event?.message?.rawContent ?: return)) {
            val cc = CommandParser.parse(event.message.rawContent, event.guild.id, event.channel.id, event.author.id)
            val filteredCommands = CommandRegistry.getCommands().filterByCommandPrefix(cc.beheadedLiteralLower)

            filteredCommands.forEach { it.preExecute(cc, event) }
            filteredCommands.forEach { it.execute(cc, event) }
            filteredCommands.forEach { it.postExecute(cc, event) }

        }
    }
}
