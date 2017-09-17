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
import me.salt.events.ConfigInteractEvent
import me.salt.events.fireEvent
import me.salt.objects.Interaction
import me.salt.util.GenUtil
import me.salt.objects.isEmpty
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
            fireEvent(ConfigInteractEvent(handler, conf, Interaction.WRITE))
        }
    }

    fun overwriteConfig(handler: Handler, conf: Configuration) {
        val f = getFile(handler)
        if (f.exists()) {
            mapper.writeValue(f, conf)
            fireEvent(ConfigInteractEvent(handler, conf, Interaction.WRITE))
        } else writeConfig(handler, conf)
    }

    fun <T : Configuration> readConfig(handler: Handler, type: Class<T>): T = try {
        ObjectMapper(YAMLFactory()).registerKotlinModule()
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .readValue(getFile(handler), type)
    } catch (e: Exception) {
        throw e //TODO change and update
    }
}