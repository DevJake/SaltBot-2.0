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

package me.salt.entities.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import me.salt.entities.Constants
import me.salt.entities.config.entities.Configuration
import me.salt.entities.config.entities.SaltConfig
import me.salt.entities.objects.Interaction
import me.salt.entities.objects.isEmpty
import me.salt.utilities.util.GenUtil
import me.salt.utilities.events.ConfigInteractEvent
import me.salt.utilities.events.fireEvent
import me.salt.utilities.exception.ConfigIllegalFieldException
import me.salt.utilities.exception.ConfigMissingValueException
import me.salt.utilities.exception.ConfigWriteException
import me.salt.utilities.exception.exception
import java.io.File
import java.io.IOException

object ConfigHandler {
    private val mapper = ObjectMapper(YAMLFactory()
            .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
            .enable(YAMLGenerator.Feature.LITERAL_BLOCK_STYLE)
            .enable(YAMLGenerator.Feature.SPLIT_LINES)
            .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER))
            .setSerializationInclusion(JsonInclude.Include.NON_ABSENT)

    private fun getFile(handler: Handler): File = File(File(GenUtil.saltResourceDir, Constants.CONFIG_DIR.value),
            handler.expectedPath)

    fun configExists(handler: Handler) = getFile(handler).exists()

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

    fun <T : Configuration> readConfig(handler: Handler, type: Class<T>): T? {
        var conf: T? = null

        try {
            conf = ObjectMapper(YAMLFactory()).registerKotlinModule()
                    .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                    .enable(DeserializationFeature.WRAP_EXCEPTIONS)
                    .readValue(getFile(handler), type)
        } catch (e: IOException) {
            throw ConfigWriteException(//TODO update exception names
                    "The config could not be loaded... it likely does not exist (and must first be created), or there was an internal issue deserialising it to an object! \nSpecified Handler=$handler\nerror=${e.message}")

            /*
            Due to a limitation with generics and type erasure, it is not possible to automatically create the file.
            This is because we cannot reliably determine which class pairs with which config type.
            The only solution would be unscalable and would require constant adaptation.
            The remaining solution would be for each config class to be annotated.
             */

        } catch (e: JsonMappingException) {
            exception(ConfigMissingValueException(
                    "The config could not be mapped to the specified Handler instance... the Handler likely has a different structure to the parsed file!"))
        } catch (e: JsonParseException) {
            exception(ConfigMissingValueException(
                    "The config could not be parsed... it's structure is likely malformed. Regenerate the file to fix structural issues."))
        }

        var throwIllegal = false
        when (conf) {
            is SaltConfig -> {
                conf.globalPrefixes?.forEach {
                    if (it.matches(Regex(".*[a-zA-Z]"))) {
                        throwIllegal = true
                        return@forEach
                    }
                }
            }
        }

        if (throwIllegal) exception(ConfigIllegalFieldException("The config loaded for config class ${type.name} has illegal fields!"))
        return conf
    }
}