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

package me.salt.exception

import com.rollbar.api.payload.data.Server
import com.rollbar.notifier.Rollbar
import com.rollbar.notifier.config.ConfigBuilder
import me.salt.config.Configs
import me.salt.config.entities.SaltConfig
import me.salt.objects.getConfig

object ExceptionHandler {

    private val s = Server.Builder().build()
    private val c = ConfigBuilder
            .withAccessToken(Configs.salt.MAIN_CONFIG.getConfig(SaltConfig::class.java)?.rollbarAccessToken)
            .environment("development")
            .language("Kotlin")
            .build()
    private val rb = Rollbar.init(c)

    fun handle(e: Exception) {
        rb.error(e)
        println(e)
    }

    fun handle(e: Exception, level: Errorlevel) {
        when (level) {
            Errorlevel.ERROR -> rb.error(e)
            Errorlevel.CRITICAL -> rb.critical(e)
            Errorlevel.DEBUG -> rb.debug(e)
            Errorlevel.INFO -> rb.info(e)
            Errorlevel.LOG -> rb.log(e)
            Errorlevel.WARNING -> rb.warning(e)
        }

        println(e)
    }
}

enum class Errorlevel {
    ERROR,
    CRITICAL,
    DEBUG,
    INFO,
    LOG,
    WARNING
}

fun exception(e: Exception) = ExceptionHandler.handle(e)
fun exception(e: Exception, level: Errorlevel) = ExceptionHandler.handle(e, level)