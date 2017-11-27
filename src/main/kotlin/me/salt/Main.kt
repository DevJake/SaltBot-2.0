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
import me.salt.utilities.exception.Errorlevel
import me.salt.utilities.exception.exception
import me.salt.utilities.logging.logInfo
import me.salt.utilities.rest.RestController
import me.salt.utilities.rest.initRest
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import java.time.OffsetDateTime

class Main {
    companion object {
        lateinit var jda: JDA
            private set

        @JvmStatic
        fun main(args: Array<String>) {
            logInfo("Began startup at ${OffsetDateTime.now()}", "BOOT")
            initConfigs() //Calls init method for configs
            initLangs()
            initCommands()
            initRest()

            RestController.start()

            Runtime.getRuntime().addShutdownHook(Thread({ logInfo("Now shutting down", "ACTIVITY") }))

            //TODO generate a 'session ID' (saltId) key, have all logs reference to a key (or perhaps name file according to key/create singular log entry about the generated ID)
            try {
                jda = JDABuilder(AccountType.BOT)
                        .setToken(Configs.salt.MAIN_CONFIG.getConfig(SaltConfig::class.java)?.botToken)
                        .addEventListener(CommandListener()).buildAsync()
            } catch (e: Exception) {
                exception(e, Errorlevel.CRITICAL)
                System.exit(-1)
            }

            Thread({ while (true) Thread.sleep(Integer.MAX_VALUE.toLong()) }, "RuntimePersistence").start() //TODO fix
            logInfo("Runtime Persistence Daemon now active", "PERSISTENCE")
            //TODO accept runtime params, such as regen-default-configs to regenerate default config files

        }
    }
}
