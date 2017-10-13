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

package me.salt.config.entities

class SaltLogConfig : Configuration {
    var logToConsole = false
    var logToFile = true
    var splitLogsByEntryType = false //Separate log file per type of entry. Avoids commands as too many to make splitting useful.
    var groupLogsByTimeDate = true //Divide logs into a new file per hour of logging, and each group of 24-hour logs into a dated-directory.
    var toLog = mutableListOf<Loggables>()
    var easyLogMessage: String

    constructor(
            logToConsole: Boolean,
            logToFile: Boolean,
            splitLogsByEntryType: Boolean,
            groupLogsByTimeDate: Boolean,
            toLog: MutableList<Loggables>,
            easyLogMessage: String = "EASYLOG") {
        this.logToConsole = logToConsole
        this.logToFile = logToFile
        this.splitLogsByEntryType = splitLogsByEntryType
        this.groupLogsByTimeDate = groupLogsByTimeDate
        this.toLog = toLog
        this.easyLogMessage = easyLogMessage
    }
}

class GuildLogConfig
class TextChannelLogConfig
class UserLogConfig

enum class Loggables(description: String) {
    BOT_START("Fired when the bot starts up"),
    BOT_STOP("Fired when the bot begins a controlled shutdown"),
}