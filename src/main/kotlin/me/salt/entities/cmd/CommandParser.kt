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

package me.salt.entities.cmd

import me.salt.entities.config.Configs
import me.salt.entities.config.entities.GuildConfig
import me.salt.entities.config.entities.SaltConfig
import me.salt.entities.config.entities.TextChannelConfig
import me.salt.entities.config.entities.UserConfig
import me.salt.entities.objects.Entity
import me.salt.entities.objects.exists
import me.salt.entities.objects.getConfig
import me.salt.util.events.CommandParseEvent
import me.salt.util.events.fireEvent
import me.salt.util.exception.*
import me.salt.util.logging.logDebug
import java.util.regex.Pattern

object CommandParser {
    fun isPotentialCommand(raw: String) = Pattern.compile("^[^a-zA-Z]+").matcher(raw.split(" ")[0]).find()

    fun parse(raw: String, guildId: String, textChannelId: String, userId: String): CommandContainer {
        var saltMain: SaltConfig? = null
        var guildMain: GuildConfig? = null
        var textMain: TextChannelConfig? = null
        var userMain: UserConfig? = null

        if (Configs.salt.MAIN_CONFIG.exists())
            saltMain = Configs.salt.MAIN_CONFIG.getConfig(SaltConfig::class.java)

        if (Configs.guild(guildId).MAIN_CONFIG.exists())
            guildMain = Configs.guild(guildId).MAIN_CONFIG.getConfig(GuildConfig::class.java)

        if (Configs.textChannel(guildId).MAIN_CONFIG.exists())
            textMain = Configs.textChannel(textChannelId).MAIN_CONFIG.getConfig(TextChannelConfig::class.java)

        if (Configs.user(guildId).MAIN_CONFIG.exists())
            userMain = Configs.user(userId).MAIN_CONFIG.getConfig(UserConfig::class.java)

        val beheaded = mutableMapOf<Entity, Pair<String, String>>()

        /*
        Determines which prefixes are applicable, and filters them into the beheaded variable.
        This allows for specific information to be distributed in events, for example.
         */
        saltMain?.globalPrefixes?.forEach {
            if (raw.startsWith(it))
                beheaded.put(Entity.GLOBAL, Pair(it, raw.replaceFirst(it, "")))
        }

        guildMain?.guildPrefixes?.forEach {
            if (raw.startsWith(it))
                beheaded.put(Entity.GUILD, Pair(it, raw.replaceFirst(it, "")))
        }

        textMain?.channelPrefixes?.forEach {
            if (raw.startsWith(it))
                beheaded.put(Entity.TEXTCHANNEL, Pair(it, raw.replaceFirst(it, "")))
        }

        userMain?.preferredCmdPrefixes?.forEach {
            if (raw.startsWith(it))
                beheaded.put(Entity.USER, Pair(it, raw.replaceFirst(it, "")))
        }

        var beheadedLiteral: String = ""

        if (!Pattern.compile("^[^a-zA-Z]+").matcher(raw.split(" ").get(0)).find())
            exception(PrefixlessCommandException("The given input, $raw, seems to lack any sort of prefix!"))
        if (!Pattern.compile("^.*?[^a-zA-Z]+").matcher(raw.split(" ").get(0)).find())
            exception(AmorphousCommandException("The given input, $raw, has a foul-structured (non-identifiable) prefix!"))
        else
            beheadedLiteral = raw.replace(Regex("^.*?[^a-zA-Z]+"), "")
        if (beheaded.isEmpty())
            exception(AmorphousCommandException("The given input, $raw, failed to match any prefixes loaded from configs!"))

        //TODO If the raw is identified as matching to a command, recursively remove the first character until reaching a letter
        //No specific way to check and appropriately remove identified prefixes without looping over them each removal; inefficient and needless
        //TODO https://github.com/DevJake/SaltBot-2.0/blob/master/src/main/java/me/Salt/Command/Container/CommandParser.java
        //TODO ensure prefixes cannot **end** with a letter (a-z)

        val cc = CommandContainer(
                beheaded,
                beheadedLiteral,
                beheadedLiteral.toLowerCase(),
                beheadedLiteral.split(" "),
                beheadedLiteral.split(" ").map { it.toLowerCase() }
        )

        fireEvent(CommandParseEvent(cc))
        logDebug("Parsed command; CommandContainer=$cc")
        return cc
    }

    data class CommandContainer(
            val beheaded: MutableMap<Entity, Pair<String, String>>,
            val beheadedLiteral: String,
            val beheadedLiteralLower: String,
            val args: List<String>,
            val argsLower: List<String>)


}
