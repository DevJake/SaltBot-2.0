package me.salt.config.entities

class SaltLogConfig {
    val enabled: Boolean?
    val logGuildAdds: Boolean?
    val logGuildLeaves: Boolean?
    val logMessageDeletions: Boolean?
    val logMessageEdits: Boolean?
    val logUserGainXp: Boolean?
    val logUserLoseXp: Boolean?
    val logUserGainLevel: Boolean?
    val logUserLoseLevel: Boolean?
    val logCommands: Boolean? //TODO map of commands and subcommands to log
    val splitLogsByEntryType: Boolean? //Separate log file per type of entry. Avoids commands as too many to make splitting useful.
    val groupLogsByTimeDate: Boolean? //Divide logs into a new file per hour of logging, and each group of 24-hour logs into a dated-directory.

    constructor(enabled: Boolean?, logGuildAdds: Boolean?, logGuildLeaves: Boolean?, logMessageDeletions: Boolean?, logMessageEdits: Boolean?, logUserGainXp: Boolean?, logUserLoseXp: Boolean?, logUserGainLevel: Boolean?, logUserLoseLevel: Boolean?, logCommands: Boolean?, splitLogsByEntryType: Boolean?, groupLogsByTimeDate: Boolean?) {
        this.enabled = enabled
        this.logGuildAdds = logGuildAdds
        this.logGuildLeaves = logGuildLeaves
        this.logMessageDeletions = logMessageDeletions
        this.logMessageEdits = logMessageEdits
        this.logUserGainXp = logUserGainXp
        this.logUserLoseXp = logUserLoseXp
        this.logUserGainLevel = logUserGainLevel
        this.logUserLoseLevel = logUserLoseLevel
        this.logCommands = logCommands
        this.splitLogsByEntryType = splitLogsByEntryType
        this.groupLogsByTimeDate = groupLogsByTimeDate
    }
}

class GuildLogConfig
class TextChannelLogConfig
class UserLogConfig