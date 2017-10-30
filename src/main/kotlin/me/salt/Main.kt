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

import me.salt.entities.cmd.CommandListener
import me.salt.entities.cmd.initCommands
import me.salt.entities.config.Configs
import me.salt.entities.config.entities.SaltConfig
import me.salt.entities.config.initConfigs
import me.salt.entities.lang.initLangs
import me.salt.entities.objects.getConfig
import me.salt.util.exception.Errorlevel
import me.salt.util.exception.exception
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

            try {
                jda = JDABuilder(AccountType.BOT)
                        .setToken(Configs.salt.MAIN_CONFIG.getConfig(SaltConfig::class.java)?.botToken)
                        .addEventListener(CommandListener()).buildAsync()
            } catch (e: Exception) {
                exception(e, Errorlevel.CRITICAL)
                System.exit(-1)
            }

            Thread({ while (true) Thread.sleep(Integer.MAX_VALUE.toLong()) }, "RuntimePersistence").start()
            //TODO accept runtime params, such as regen-default-configs to regenerate default config files
        }
    }
}
