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

package me.salt.utilities.api.bot

import me.salt.entities.config.entities.PluginConfig
import java.net.URL

abstract class SaltPlugin(title: String, description: String, author: String) {
    internal var isEnabled = false
    internal var isLoaded = false

    init {
        //TODO register plugin
    }

    open fun onEnable() {
        isEnabled = true
    }

    open fun onDisable() {
        isEnabled = false
    }

    open fun onLoad() {
        isLoaded = true
    }

    open fun onUnload() {
        isLoaded = false
    }

    fun getConfigHandler() = ConfigHandler(this)
    fun getLanguageHandler() = LanguageHandler(this)
    fun getLoggingHandler() = LoggingHandler(this)
    fun getCommandHandler() = CommandHandler(this)
    fun getPluginHandler() = PluginHandler(this)

    fun getLogger() = PluginLogger(this)
}

class ConfigHandler internal constructor(private val plugin: SaltPlugin) {
    //TODO system for getting system-held configs, but requiring certain permissions ->
    /*
    Plugins perhaps need to register with a key, bot can have command to grant plugins certain permissions based
    on their key.

    Certain configs (Guild, TextChannel and User) should have the plugin request permission from
    the config's owner to read and write.
     */

    fun getPluginConfigOrCreate(): PluginConfig {
        TODO()
    }

    fun getPluginConfig(): PluginConfig? {
        TODO()
    }

    fun createPluginConfig(): PluginConfig? = if (pluginConfigExists()) getPluginConfig()
    else {
        TODO()
    }

    fun pluginConfigExists(): Boolean {
        TODO()
    }

    fun pluginConfigExists(pluginTitle: String): Boolean {
        TODO()
    }

}

class PermissionsHandler internal constructor(private val plugin: SaltPlugin) {}

class LanguageHandler internal constructor(private val plugin: SaltPlugin) {}

class LoggingHandler internal constructor(private val plugin: SaltPlugin) {
    //TODO searching and crawling of logs (with perms)
}

class CommandHandler internal constructor(private val plugin: SaltPlugin) {

}

class PluginHandler internal constructor(private val plugin: SaltPlugin) {
    fun setContactInfo(discordId: Int) {}

    fun setContactInfo(emailAddress: String) {}

    fun setContactInfo(website: URL) {}
}

class PluginLogger internal constructor(private val plugin: SaltPlugin) {
    //TODO
}