/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.util.events

import me.salt.entities.cmd.CommandParser
import me.salt.entities.config.Handler
import me.salt.entities.config.entities.Configuration
import me.salt.entities.objects.Interaction
import me.salt.entities.permissions.Authority
import me.salt.entities.permissions.Node
import net.dv8tion.jda.core.entities.User
import java.io.File

interface Event

interface ListenerAdapter {
    fun onEvent(e: Event)
}

fun fireEvent(event: Event) = EventDistributor.fireEvent(event)

object EventDistributor {
    private var listeners: MutableList<ListenerAdapter> = mutableListOf()

    fun registerListener(eventListener: ListenerAdapter) = listeners.add(eventListener)
    fun unregisterListener(eventListener: ListenerAdapter) = listeners.remove(eventListener)
    fun fireEvent(event: Event) = listeners.forEach { listener -> listener.onEvent(event) }
}

open class EventHandler : ListenerAdapter {
    override fun onEvent(e: Event) {
        when (e) {
            is FileCreateEvent -> onFileCreate(e)
            is ConfigInteractEvent -> onConfigReadWrite(e)
            is PermissionCheckEvent -> onPermissionCheck(e)
            is CommandParseEvent -> onCommandParse(e)
        }
    }

    open fun onFileCreate(e: FileCreateEvent) {}

    open fun onConfigReadWrite(e: ConfigInteractEvent) {}

    open fun onPermissionCheck(e: PermissionCheckEvent) {}

    open fun onCommandParse(e: CommandParseEvent) {}
}

data class FileCreateEvent(val file: File, val isFile: Boolean) : Event
data class ConfigInteractEvent(val handler: Handler, val entity: Configuration, val type: Interaction) : Event
data class PermissionCheckEvent(val level: Authority.Level, val nodes: List<Node>, val entityId: String?, val user: User) : Event
data class PermissionRegisterEvent(val nodes: List<Node>) : Event
data class PermissionUnregisterEvent(val nodes: List<Node>) : Event
data class CommandParseEvent(val cmdContainer: CommandParser.CommandContainer) : Event
