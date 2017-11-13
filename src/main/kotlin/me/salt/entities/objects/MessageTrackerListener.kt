package me.salt.entities.objects

import net.dv8tion.jda.core.events.message.MessageBulkDeleteEvent
import net.dv8tion.jda.core.events.message.MessageDeleteEvent
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.events.message.MessageUpdateEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class MessageTrackerListener : ListenerAdapter() {
    override fun onMessageDelete(event: MessageDeleteEvent?) {

    }

    override fun onMessageUpdate(event: MessageUpdateEvent?) {

    }

    override fun onMessageReceived(event: MessageReceivedEvent?) {

    }

    override fun onMessageBulkDelete(event: MessageBulkDeleteEvent?) {

    }

    //TODO regular limit of storing messages is 7 days, anything greater requires premium
}
