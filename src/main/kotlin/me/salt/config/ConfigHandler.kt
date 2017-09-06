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

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import me.salt.config.entities.Configuration
import me.salt.events.ConfigReadWriteEvent
import me.salt.events.fireEvent
import me.salt.util.GenUtil
import me.salt.util.isEmpty
import java.io.File

object ConfigHandler {
    private val mapper = ObjectMapper(YAMLFactory()
            .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
            .enable(YAMLGenerator.Feature.LITERAL_BLOCK_STYLE)
            .enable(YAMLGenerator.Feature.SPLIT_LINES)
            .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER))
            .setSerializationInclusion(JsonInclude.Include.NON_ABSENT)

    private fun getFile(handler: Handler): File = File(File(GenUtil.saltResourceDir, "\\Data"), handler.expectedPath)

    fun writeConfig(handler: Handler, conf: Configuration) {
        val f = getFile(handler)
        GenUtil.createFile(f)
        if (f.exists() && f.isEmpty()) {
            mapper.writeValue(f, conf)
            fireEvent(ConfigReadWriteEvent(handler, conf, ConfigReadWriteEvent.Interact.WRITE))
        }
    }

    fun updateConfig(handler: Handler, conf: Configuration) {
        val f = getFile(handler)
        if (f.exists()) {
            mapper.writeValue(f, conf)
            fireEvent(ConfigReadWriteEvent(handler, conf, ConfigReadWriteEvent.Interact.WRITE))
        }
    }

    fun <T : Configuration> readConfig(handler: Handler, type: Class<T>): T {
        return ObjectMapper(YAMLFactory()).registerKotlinModule()
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .readValue(getFile(handler), type)
    }

    val saltConfigHandler = SaltConfigHandler()
    val guildConfigHandler = GuildConfigHandler()
    val textChannelConfigHandler = TextChannelConfigHandler()
    val userConfigHandler = UserConfigHandler()
}


class SaltConfigHandler : GenericConfigHandler {

    override fun getMainConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoggingConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStatsConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLangConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class GuildConfigHandler : GenericConfigHandler {
    override fun getMainConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoggingConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStatsConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLangConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class TextChannelConfigHandler : GenericConfigHandler {
    override fun getMainConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoggingConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStatsConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLangConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class UserConfigHandler : GenericConfigHandler {
    override fun getMainConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoggingConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStatsConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLangConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

private interface GenericConfigHandler {
    fun getMainConfig(entityId: String?)
    fun getPermConfig(entityId: String?)
    fun getLoggingConfig(entityId: String?)
    fun getStatsConfig(entityId: String?)
    fun getLangConfig(entityId: String?)

    fun getMainConfig(entityId: Long?) = getMainConfig(entityId.toString())
    fun getPermConfig(entityId: Long?) = getPermConfig(entityId.toString())
    fun getLoggingConfig(entityId: Long?) = getLoggingConfig(entityId.toString())
    fun getStatsConfig(entityId: Long?) = getStatsConfig(entityId.toString())
    fun getLangConfig(entityId: Long?) = getLangConfig(entityId.toString())

    enum class Entity {
        GUILD,
        TEXTCHANNEL,
        USER;
    }
}

private interface SectionHandler {
    fun getSampleSaltConfig()
    fun getSampleGuildConfig()
    fun getSampleTextChannelConfig()
    fun getSampleUserConfig()
}

class EntityConfig : SectionHandler {
    override fun getSampleSaltConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleGuildConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleTextChannelConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleUserConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class PermissionConfig : SectionHandler {
    override fun getSampleSaltConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleGuildConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleTextChannelConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleUserConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class LogConfig : SectionHandler {
    override fun getSampleSaltConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleGuildConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleTextChannelConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleUserConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class StatsConfig : SectionHandler {
    override fun getSampleSaltConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleGuildConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleTextChannelConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleUserConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class LanguageConfig : SectionHandler {
    override fun getSampleSaltConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleGuildConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleTextChannelConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleUserConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}