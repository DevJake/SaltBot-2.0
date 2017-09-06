/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.config

import me.salt.config.entities.Configuration
import me.salt.config.entities.SaltConfig
import me.salt.exception.ConfigHandlerException

object Configs {
    private var chain = mutableListOf<String>()
    private var entityId: String? = null

    val SALT = ConfigTypes
        get() {
            chain.clear()
            chain.add("SALT")
            return field
        }

    fun GUILD(entityId: String): ConfigTypes {
        chain.clear()
        chain.add("GUILD")
        this.entityId = entityId
        return ConfigTypes
    }

    fun TEXTCHANNEL(entityId: String): ConfigTypes {
        chain.clear()
        chain.add("TEXTCHANNEL")
        this.entityId = entityId
        return ConfigTypes
    }

    fun VOICECHANNEL(entityId: String): ConfigTypes {
        chain.clear()
        chain.add("VOICECHANNEL")
        this.entityId = entityId
        return ConfigTypes
    }

    fun USER(entityId: String): ConfigTypes {
        chain.clear()
        chain.add("USER")
        this.entityId = entityId
        return ConfigTypes
    }

    object ConfigTypes {
        val MAIN_CONFIG: Handler
            get() {
                chain.add("MAIN CONFIG")
                return Handler(chain, entityId)
            }
        val LANGUAGE_CONFIG: Handler
            get() {
                chain.add("LANGUAGE CONFIG")
                return Handler(chain, entityId)
            }
        val LOG_CONFIG: Handler
            get() {
                chain.add("LOG CONFIG")
                return Handler(chain, entityId)
            }
        val STATS_CONFIG: Handler
            get() {
                chain.add("STATS CONFIG")
                return Handler(chain, entityId)
            }

        val FILTERING_MAP: Handler
            get() {
                chain.add("FILTERING MAP")
                return Handler(chain, entityId)
            }
        val LEVELLING_MAP: Handler
            get() {
                chain.add("LEVELLING MAP")
                return Handler(chain, entityId)
            }
        val PERMISSIONS_MAP: Handler
            get() {
                chain.add("PERMISSIONS MAP")
                return Handler(chain, entityId)
            }
        val SELF_ASSIGNABLE_ROLES_MAP: Handler
            get() {
                chain.add("SAR MAP")
                return Handler(chain, entityId)
            }
        val STAT_TRACKING_MAP: Handler
            get() {
                chain.add("STAT TRACKING MAP")
                return Handler(chain, entityId)
            }
    }


}

class Handler(val chain: List<String>, val entityId: String?) {
    var expectedPath = ""
    private var keyword = ""

    init {
        if (chain.size != 2) throw ConfigHandlerException("The given chain is of the incorrect size(${chain.size})!")
        when (chain.get(0)) {
            "SALT" -> {
                expectedPath += "/Admin"
                keyword = "Salt"
            }
            "GUILD" -> {
                expectedPath += "/Guilds/$entityId"
                keyword = "Guild"
            }
            "TEXTCHANNEL" -> {
                expectedPath += "/Guilds/$entityId/TextChannels/$entityId"
                keyword = "TextChannel"
            } //Use JDA to determine guildId from entityId, therefore checking entityId sooner
            "VOICECHANNEL" -> {
                expectedPath += "/Guilds/$entityId/VoiceChannels/$entityId"
                keyword = "VoiceChannel"
            }
            "USER" -> {
                expectedPath += "/Users/$entityId"
                keyword = "User"
            }
            else -> throw ConfigHandlerException("The value at chain[0] is incorrect! Must be one of either SALT, GUILD, TEXTCHANNEL, VOICECHANNEL or USER")
        }

        expectedPath += when (chain.get(1)) {
            "MAIN CONFIG" -> "/Configs/${keyword}Config.yaml"
            "LANGUAGE CONFIG" -> "/Configs/${keyword}LanguageConfig.yaml"
            "LOG CONFIG" -> "/Configs/${keyword}LogConfig.yaml"
            "STATS CONFIG" -> "/Configs/${keyword}StatsConfig.yaml"

            "FILTERING MAP" -> "/Maps/${keyword}FilteringMap.yaml"
            "LEVELLING MAP" -> "/Maps/${keyword}LevellingMap.yaml"
            "PERMISSIONS MAP" -> "/Maps/${keyword}PermissionsMap.yaml"
            "SAR MAP" -> "/Maps/${keyword}SARMap.yaml"
            "STAT TRACKING MAP" -> "/Maps/${keyword}StatTrackingMap.yaml"
            else -> throw ConfigHandlerException("The value at chain[1] is incorrect! Must be one of me.salt.config.Configs.ConfigTypes")
        }


        //TODO checking specified entityId is valid
        //TODO decipher chain, determine path, throw required exceptions
    }

    fun <T : Configuration> getConfigFile(configClass: Class<T>): T = ConfigHandler.readConfig(this, configClass)
}