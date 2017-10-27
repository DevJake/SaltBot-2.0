package me.salt.cmd.commands

import me.salt.cmd.Command
import me.salt.cmd.CommandParser
import me.salt.permissions.Node
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

class HelloWorldCommand(cmdPrefix: String, aliases: MutableList<String>, name: String, description: String, author: String, perms: List<Node>) : Command(cmdPrefix, aliases, name, description, author, perms) {

    override fun preExecute(cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent) {
        //Parse input
    }

    override fun execute(cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent) {
        //Run command
        event.channel.sendMessage("Hello, ${event.author.name}").queue()
    }

    override fun postExecute(cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent) {
        //Cleanup
    }
}

