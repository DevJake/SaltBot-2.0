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
