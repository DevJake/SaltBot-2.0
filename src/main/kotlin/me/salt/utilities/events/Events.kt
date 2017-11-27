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

package me.salt.utilities.events

import me.salt.entities.cmd.CommandParser
import me.salt.entities.config.Handler
import me.salt.entities.config.entities.Configuration
import me.salt.entities.objects.Interaction
import me.salt.entities.permissions.Authority
import me.salt.entities.permissions.Node
import me.salt.utilities.ArgsProcessor
import me.salt.utilities.util.SearchUtil
import me.salt.utilities.rest.RestRequestType
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
            is PermissionRegisterEvent -> onPermissionRegister(e)
            is PermissionUnregisterEvent -> onPermissionUnregister(e)
            is RestRequestEvent -> onRestRequest(e)
            is SearchRequestEvent -> onSearchRequest(e)
            is SearchRespondEvent -> onSearchRespond(e)
            is ArgProcessEvent -> onArgProcess(e)
            is ArgAddEvent -> onArgAdd(e)
            is ArgRetrieveEvent -> onArgRetrieve(e)
        }
    }

    open fun onFileCreate(e: FileCreateEvent) {}

    open fun onConfigReadWrite(e: ConfigInteractEvent) {}

    open fun onPermissionCheck(e: PermissionCheckEvent) {}

    open fun onCommandParse(e: CommandParseEvent) {}

    open fun onPermissionRegister(e: PermissionRegisterEvent) {}

    open fun onPermissionUnregister(e: PermissionUnregisterEvent) {}

    open fun onRestRequest(e: RestRequestEvent) {}

    open fun onSearchRequest(e: SearchRequestEvent) {}

    open fun onSearchRespond(e: SearchRespondEvent) {}

    open fun onArgProcess(e: ArgProcessEvent) {}

    open fun onArgAdd(e: ArgAddEvent) {}

    open fun onArgRetrieve(e: ArgRetrieveEvent) {}
}

data class FileCreateEvent(val file: File, val isFile: Boolean) : Event

data class ConfigInteractEvent(val handler: Handler, val entity: Configuration, val type: Interaction) : Event

data class PermissionCheckEvent(val level: Authority.Level, val nodes: List<Node>, val entityId: String?, val user: User) : Event

data class PermissionRegisterEvent(val nodes: List<Node>) : Event

data class PermissionUnregisterEvent(val nodes: List<Node>) : Event

data class CommandParseEvent(val cmdContainer: CommandParser.CommandContainer) : Event

data class RestRequestEvent(val type: RestRequestType) : Event

data class SearchRequestEvent(val request: SearchUtil.SearchElement) : Event

data class SearchRespondEvent(val successful: Boolean) : Event

data class ArgProcessEvent(val argumentHandler: ArgsProcessor.Argument, val argument: String) : Event

//TODO
data class ArgAddEvent(val placeholder: Int) : Event

data class ArgRetrieveEvent(val placeholder: Int) : Event

