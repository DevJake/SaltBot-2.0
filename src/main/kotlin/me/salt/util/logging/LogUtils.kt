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

package me.salt.util.logging

import me.salt.entities.config.Configs
import me.salt.entities.config.entities.SaltLogConfig
import me.salt.util.events.Event
import me.salt.util.exception.ConfigMissingValueException
import me.salt.entities.objects.getConfig
import me.salt.entities.objects.writeConfig
import java.time.Instant

object LogUtils {
    private val cache = mutableListOf<LogEntry>()
    private fun addLogEntry(entry: LogEntry) {
        cache.add(entry)
        flush(entry) //TODO remove when log system is done
    }

    init {
        Configs.salt.LOG_CONFIG.writeConfig(SaltLogConfig(true, true, true, true, mutableListOf()))
    }

    private fun flushCache() {
        if (cache.size < 100) return

        cache.forEach { entry ->
            println(entry.toString())
            //TODO writing cache, ignore optional if null
        }

        cache.clear()

//TODO("Writing entries (with log entry about the flush), clearing cache"), splitting logs to different files, such as one for exceptions
    }

    private fun flush(entry: LogEntry) {
        val logConfig = Configs.salt.LOG_CONFIG.getConfig(SaltLogConfig::class.java) ?:
                //exception(ConfigMissingValueException())
                throw ConfigMissingValueException("The logging config does not exist! Fix this issue") //TODO
        if (logConfig.logToConsole as Boolean) println(calcLogMessage(entry))

        flushCache()
    }

    private fun calcLogMessage(entry: LogEntry) =
            "[${entry.dateTime}] " +
                    "[${entry.type.entryName}" +
                    "${if (entry.optional != null)
                        "/${entry.optional}" else ""}] " +
                    entry.elements.joinToString()

    fun info(prefix: String? = null) =
            Logger(LogType.INFO, prefix)

    fun debug(prefix: String? = null) =
            Logger(LogType.DEBUG, prefix)

    fun warn(prefix: String? = null) =
            Logger(LogType.WARNING, prefix)

    fun fatal(prefix: String? = null) =
            Logger(LogType.FATAL, prefix)

    fun severe(prefix: String? = null) =
            Logger(LogType.SEVERE, prefix)

    fun exception(prefix: String? = null) =
            Logger(LogType.EXCEPTION, prefix)

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
        FATAL("FATAL"),
        EXCEPTION("EXCEPTION");

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
fun logException(exception: Exception, optional: String? = null) = LogUtils.exception(optional).log(exception)

fun logEasy(message: String) = LogUtils.info(Configs.salt.LOG_CONFIG.getConfig(SaltLogConfig::class.java)?.easyLogMessage ?: "EASYLOG").log(message)