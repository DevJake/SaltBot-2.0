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

package me.salt.logging

import me.salt.config.Configs
import me.salt.config.entities.SaltLogConfig
import me.salt.events.Event
import me.salt.exception.ConfigMissingValueException
import me.salt.exception.exception
import me.salt.objects.getConfig
import java.time.Instant

object LogUtils {
    private val cache = mutableListOf<LogEntry>()
    private fun addLogEntry(entry: LogEntry) {
        cache.add(entry)
        flush(entry)
    }

    private fun flushCache() {
        if (cache.size < 100) return

        cache.forEach { entry ->
            println(entry.toString())
        }

        cache.clear()

//TODO("Writing entries (with log entry about the flush), clearing cache"), splitting logs to different files, such as one for exceptions
    }

    private fun flush(entry: LogEntry) {
        var logConfig = Configs.salt.LOG_CONFIG.getConfig(SaltLogConfig::class.java) //TODO
        if (logConfig == null)
            exception(ConfigMissingValueException())
        if (logConfig?.logToConsole as Boolean) println(calcLogMessage(entry))

        flushCache()
    }

    private fun calcLogMessage(entry: LogEntry) =
            "[${entry.dateTime}] " +
                    "[${entry.type.entryName}" +
                    "${if (entry.optional != null)
                        "/${entry.optional}" else ""}] " +
                    entry.elements.joinToString()

    fun info(optional: String? = null) =
            Logger(LogType.INFO, optional)

    fun debug(optional: String? = null) =
            Logger(LogType.DEBUG, optional)

    fun warn(optional: String? = null) =
            Logger(LogType.WARNING, optional)

    fun fatal(optional: String? = null) =
            Logger(LogType.FATAL, optional)

    fun severe(optional: String? = null) =
            Logger(LogType.SEVERE, optional)

    class Logger(private val type: LogType, private val optional: String?) {
        fun log(message: String) =
                addLogEntry(LogEntry(type, optional, message))

        fun log(exception: Exception) =
                addLogEntry(LogEntry(type, optional, exception))

        fun log(message: String, exception: Exception) =
                addLogEntry(LogEntry(type, optional, message, exception))

        fun log(event: Event) =
                addLogEntry(LogEntry(type, optional, event))

        fun log(message: String, event: Event) =
                addLogEntry(LogEntry(type, optional, message, event))

    }

    enum class LogType(var entryName: String) {
        INFO("INFO"),
        DEBUG("DEBUG"),
        WARNING("WARN"),
        SEVERE("SEVERE"),
        FATAL("FATAL");

        init {
            entryName = entryName.toUpperCase()
        }
    }

    private class LogEntry(val type: LogType, val optional: String?, vararg val elements: Any) {
        val dateTime = Instant.now()
        val saltId: String = "tempSaltId" //TODO Add SaltId generator
        //TODO generate the log message from the provided element(s) and other information.
        //TODO switch through types of loggable entities to determine best formatting

    }
}

fun logInfo(message: String, optional: String? = null) = LogUtils.info(optional).log(message)
fun logDebug(message: String, optional: String? = null) = LogUtils.debug(optional).log(message)
fun logWarn(message: String, optional: String? = null) = LogUtils.warn(optional).log(message)
fun logSevere(message: String, optional: String? = null) = LogUtils.severe(optional).log(message)
fun logFatal(message: String, optional: String? = null) = LogUtils.fatal(optional).log(message)

fun logEasy(message: String) = LogUtils.info(Configs.salt.LOG_CONFIG.getConfig(SaltLogConfig::class.java)?.easyLogMessage ?: "EASYLOG").log(message)