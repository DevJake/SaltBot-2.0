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

package me.salt.utilities.logging

import me.salt.entities.config.Configs
import me.salt.entities.config.entities.SaltLogConfig
import me.salt.entities.objects.getConfig
import me.salt.entities.objects.writeConfig
import me.salt.utilities.events.Event
import me.salt.utilities.exception.ConfigMissingValueException
import me.salt.utilities.exception.LogEntryIdGenMissingIdException
import me.salt.utilities.exception.exception
import java.time.Instant
import java.time.OffsetDateTime

object LogUtils {
    private val cache = mutableListOf<LogEntry>()
    private fun addLogEntry(entry: LogEntry) {
        cache.add(entry)
        flush(entry) //TODO remove when log system is done
    }

    init {
        Configs.salt.LOG_CONFIG.writeConfig(SaltLogConfig(true, true, true, true, mutableListOf()))
    }

    //TODO add 'spacer'; spacing out messages so that they're consistent, such as ensuring time fragment is properly spaced if below normal length ([2017-11-13T00:29:59.572Z] vs [2017-11-13T00:30Z])

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
        if (logConfig.logToConsole) println(calcLogMessage(entry))

        flushCache()
    }

    private fun calcLogMessage(entry: LogEntry) =
            "[${entry.dateTime}] " +
                    "[${entry.type.entryName}" +
                    "${if (entry.optional != null)
                        "/${entry.optional}" else ""}] " +
                    entry.elements.joinToString()

    data class LogSource(val srcType: Entity, val srcId: String?)

    fun info(prefix: String? = null, src: LogSource?) =
            Logger(LogType.INFO, src, prefix)

    fun debug(prefix: String? = null, src: LogSource?) =
            Logger(LogType.DEBUG, src, prefix)

    fun warn(prefix: String? = null, src: LogSource?) =
            Logger(LogType.WARNING, src, prefix)

    fun fatal(prefix: String? = null, src: LogSource?) =
            Logger(LogType.FATAL, src, prefix)

    fun severe(prefix: String? = null, src: LogSource?) =
            Logger(LogType.SEVERE, src, prefix)

    fun exception(prefix: String? = null, src: LogSource?) =
            Logger(LogType.EXCEPTION, src, prefix)

    class Logger(private val type: LogType, private val src: LogSource?, private val optional: String?) {
        fun log(message: String) =
                addLogEntry(LogEntry(type, optional?.toUpperCase(), src, message))

        fun log(exception: Exception) =
                addLogEntry(LogEntry(type, optional?.toUpperCase(), src, exception))

        fun log(message: String, exception: Exception) =
                addLogEntry(LogEntry(type, optional?.toUpperCase(), src, message, exception))

        fun log(event: Event) =
                addLogEntry(LogEntry(type, optional?.toUpperCase(), src, event))

        fun log(message: String, event: Event) =
                addLogEntry(LogEntry(type, optional?.toUpperCase(), src, message, event))

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

    private class LogEntry(val type: LogType, val optional: String?, src: LogSource?, vararg val elements: Any) {
        val dateTime = Instant.now()
        val saltId: String = "tempSaltId" //TODO Add SaltId generator
        val entryId: EntryId? = if (src != null) genEntryId(src.srcId, src.srcType) else null
        //TODO generate the log message from the provided element(s) and other information.
        //TODO switch through types of loggable entities to determine best formatting

    }

    enum class Entity {
        SALT,
        GUILD,
        VOICECHANNEL,
        CHANNEL,
        USER
    }

    private fun genEntryId(entityId: String?, type: Entity): EntryId {
        if (type == Entity.GUILD || type == Entity.VOICECHANNEL || type == Entity.CHANNEL || type == Entity.USER) {
            if (entityId == null)
                exception(LogEntryIdGenMissingIdException("An entity ID was failed to be specified for generation of a log entry ID!"))
        }

        return makeEntryId(entityId ?: "")

    }

    private fun makeEntryId(preData: String): EntryId {
        val token: String = ""
        return EntryId(token, preData, OffsetDateTime.now())
    }

    data class EntryId(val fullToken: String, val givenId: String, val dateTime: OffsetDateTime)
}

fun logInfo(message: String, optional: String? = null, src: LogUtils.LogSource? = null) = LogUtils.info(optional,
        src).log(message)

fun logDebug(message: String, optional: String? = null, src: LogUtils.LogSource? = null) = LogUtils.debug(optional,
        src).log(message)

fun logWarn(message: String, optional: String? = null, src: LogUtils.LogSource? = null) = LogUtils.warn(optional,
        src).log(message)

fun logSevere(message: String, optional: String? = null, src: LogUtils.LogSource? = null) = LogUtils.severe(optional,
        src).log(message)

fun logFatal(message: String, optional: String? = null, src: LogUtils.LogSource? = null) = LogUtils.fatal(optional,
        src).log(message)

fun logException(exception: Exception, optional: String? = null, src: LogUtils.LogSource? = null) = LogUtils.exception(
        optional,
        src).log(exception)

fun logEasy(message: String, src: LogUtils.LogSource? = null) = LogUtils.info(Configs.salt.LOG_CONFIG.getConfig(
        SaltLogConfig::class.java)?.easyLogMessage ?: "EASYLOG", src).log(message)