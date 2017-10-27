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

package me.salt

import me.salt.cmd.CommandListener
import me.salt.cmd.CommandParser
import me.salt.cmd.initCommands
import me.salt.config.Configs
import me.salt.config.entities.*
import me.salt.config.initConfigs
import me.salt.lang.initLangs
import me.salt.objects.Interaction
import me.salt.objects.getConfig
import me.salt.objects.overwriteConfig
import me.salt.objects.writeConfig
import me.salt.permissions.*
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder

class Main {
    companion object {
        lateinit var jda: JDA
            private set

        @JvmStatic
        fun main(args: Array<String>) {
            initConfigs() //Calls init method for configs
            initLangs()
            initCommands()
//            println(LangCode.EN_GB.getLang())
            Configs.salt.LOG_CONFIG.writeConfig(SaltLogConfig(true, true, false, true, mutableListOf()))
//            Configs.salt.MAIN_CONFIG.overwriteConfig(SaltConfigBuilder("").build())
            println(CommandParser.parse(".cMD ArGs My BoIs", "g123", "t123", "u123").toString())
            println(CommandParser.parse(".scMD ArGs My BoIs", "g123", "t123", "u123").toString())
            println(CommandParser.parse(".scMD ArGs My BoIs", "g123", "t123", "u123").toString())
//            logEasy("Simple, easy logging")
//            logWarn("A warning log")
//            LogUtils.DEBUG("MAINTHREAD").log("A testing debug log entry")
            jda = JDABuilder(AccountType.BOT).setToken(Configs.salt.MAIN_CONFIG.getConfig(SaltConfig::class.java)?.botToken).buildAsync()
            jda.addEventListener(CommandListener())
            //TODO accept runtime params, such as regen-default-configs to regenerate default config files
        }
    }
}
