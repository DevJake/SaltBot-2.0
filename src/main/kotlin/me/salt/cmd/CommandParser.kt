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

package me.salt.cmd

import me.salt.config.Configs
import me.salt.config.entities.GuildConfig
import me.salt.config.entities.SaltConfig
import me.salt.config.entities.TextChannelConfig
import me.salt.config.entities.UserConfig
import me.salt.objects.Entity


object CommandParser {

    fun parse(raw: String, guildId: String, textChannelId: String, userId: String) {
        var saltMain: SaltConfig? = null
        var guildMain: GuildConfig? = null
        var textMain: TextChannelConfig? = null
        var userMain: UserConfig? = null

        if (Configs.SALT.MAIN_CONFIG.exists())
            saltMain = Configs.SALT.MAIN_CONFIG.getConfig(SaltConfig::class.java)

        if (Configs.GUILD(guildId).MAIN_CONFIG.exists())
            guildMain = Configs.GUILD(guildId).MAIN_CONFIG.getConfig(GuildConfig::class.java)

        if (Configs.TEXTCHANNEL(guildId).MAIN_CONFIG.exists())
            textMain = Configs.TEXTCHANNEL(textChannelId).MAIN_CONFIG.getConfig(TextChannelConfig::class.java)

        if (Configs.USER(guildId).MAIN_CONFIG.exists())
            userMain = Configs.USER(userId).MAIN_CONFIG.getConfig(UserConfig::class.java)

        var beheaded = mutableMapOf<String, Pair<String, Entity>>()

        /*
        Determines which prefixes are applicable, and filters them into the beheaded variable.
        This allows for specific information to be distributed in events, for example.
         */
        saltMain?.globalPrefixes?.forEach {
            if (raw.startsWith(it))
                beheaded.put(it, Pair(raw.replaceFirst(it, ""), Entity.GLOBAL))
        }

        guildMain?.guildPrefixes?.forEach {
            if (raw.startsWith(it))
                beheaded.put(it, Pair(raw.replaceFirst(it, ""), Entity.GUILD))
        }

        textMain?.channelPrefixes?.forEach {
            if (raw.startsWith(it))
                beheaded.put(it, Pair(raw.replaceFirst(it, ""), Entity.TEXTCHANNEL))
        }

        userMain?.preferredCmdPrefixes?.forEach {
            if (raw.startsWith(it))
                beheaded.put(it, Pair(raw.replaceFirst(it, ""), Entity.USER))
        }

        //TODO If the raw is identified as matching to a command, recursively remove the first character until reaching a letter
        //No specific way to check and appropriately remove identified prefixes without looping over them each removal; inefficient and needless
        //TODO https://github.com/DevJake/SaltBot-2.0/blob/master/src/main/java/me/Salt/Command/Container/CommandParser.java
    }

    class CommandContainer {

    }
}