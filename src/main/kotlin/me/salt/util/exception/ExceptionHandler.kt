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

package me.salt.util.exception

import com.rollbar.api.payload.data.Server
import com.rollbar.notifier.Rollbar
import com.rollbar.notifier.config.ConfigBuilder
import me.salt.entities.config.Configs
import me.salt.entities.config.entities.SaltConfig
import me.salt.entities.objects.getConfig
import me.salt.util.logging.logException

object ExceptionHandler {
    private val s = Server.Builder().build()
    private val c = ConfigBuilder
            .withAccessToken(Configs.salt.MAIN_CONFIG.getConfig(SaltConfig::class.java)?.rollbarAccessToken)
            .environment("development")
            .language("Kotlin")
            .build() ?: throw RollbarInitException("SaltConfig lacks a value for rollbarAccessToken! Does it exist?")
    private val rb = Rollbar.init(c)
    var latestException: Exception? = null
        private set
        get() {
            val b = field
            latestException = null
            return b
        }

    var isTesting = false

    fun handle(e: Exception) {
        if (!isTesting) rb?.error(e)
        logException(e)
        latestException = e
        if (e !is RuntimeException)
            throw e
    }

    fun handle(e: Exception, level: Errorlevel) {
        when (level) {
            Errorlevel.ERROR -> handle(e)
            Errorlevel.CRITICAL -> handle(e)
            Errorlevel.DEBUG -> handle(e)
            Errorlevel.INFO -> handle(e)
            Errorlevel.LOG -> handle(e)
            Errorlevel.WARNING -> handle(e)
        }
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