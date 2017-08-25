package me.salt.config.entities

import com.fasterxml.jackson.annotation.JsonProperty
import me.salt.Admin
import me.salt.Module
import me.salt.lang.LangCode
import me.salt.SimpleRGBColour

class SaltConfig {
    @JsonProperty("Bot Token")
    val botToken: String?
    @JsonProperty("Global Safe Mode enabled")
    val botSafeMode: Boolean?
    @JsonProperty("Modules")
    val modules: List<Module>?
    @JsonProperty("Global Admins")
    val globalAdmins: List<Admin>?
    @JsonProperty("Respond to mentions")
    val respondToMentions: Boolean?
    @JsonProperty("Global Command Prefixes")
    val globalPrefixes: List<String>?
    @JsonProperty("Enable Cooldowns")
    val enableCooldowns: Boolean?
    @JsonProperty("Default Cooldown Value (Seconds)")
    val defaultCooldownValue: Double?
    @JsonProperty("Default Embed colour")
    val defaultEmbedColour: SimpleRGBColour?
    @JsonProperty("Default Language")
    val defaultLangCode: LangCode?
    @JsonProperty("Content Pinning enabled")
    val allowContentPinning: Boolean?
    @JsonProperty("Default User-set Languages enabled")
    val allowDefaultUserLanguages: Boolean?
    @JsonProperty("Custom User-defined Languages enabled")
    val allowCustomUserLanguages: Boolean?
    @JsonProperty("Reminders enabled")
    val allowReminders: Boolean?
    @JsonProperty("Reminder limit")
    val reminderTimeLimit: Double?

    constructor(botToken: String?, botSafeMode: Boolean?, modules: List<Module>?, globalAdmins: List<Admin>?, respondToMentions: Boolean?, globalPrefixes: List<String>?, enableCooldowns: Boolean?, defaultCooldownValue: Double?, defaultEmbedColour: SimpleRGBColour?, defaultLangCode: LangCode?, allowContentPinning: Boolean?, allowDefaultUserLanguages: Boolean?, allowCustomUserLanguages: Boolean?, allowReminders: Boolean?, reminderTimeLimit: Double?) {
        this.botToken = botToken
        this.botSafeMode = botSafeMode
        this.modules = modules
        this.globalAdmins = globalAdmins
        this.respondToMentions = respondToMentions
        this.globalPrefixes = globalPrefixes
        this.enableCooldowns = enableCooldowns
        this.defaultCooldownValue = defaultCooldownValue
        this.defaultEmbedColour = defaultEmbedColour
        this.defaultLangCode = defaultLangCode
        this.allowContentPinning = allowContentPinning
        this.allowDefaultUserLanguages = allowDefaultUserLanguages
        this.allowCustomUserLanguages = allowCustomUserLanguages
        this.allowReminders = allowReminders
        this.reminderTimeLimit = reminderTimeLimit
    }
}

class GuildConfig {
    @JsonProperty("Bot enabled")
    val botEnabled: Boolean?
    @JsonProperty("Guild Safe Mode enabled")
    val botSafeMode: Boolean?
    @JsonProperty("Modules")
    val modules: List<Module>?
    @JsonProperty("Guild Admins")
    val guildAdmins: List<Admin>?
    @JsonProperty("Respond to mentions")
    val respondToMentions: Boolean?
    @JsonProperty("Guild-wide Command Prefixes")
    val guildPrefixes: List<String>?
    @JsonProperty("Force Cooldowns")
    val forceCooldowns: Boolean?
    @JsonProperty("Default Guild Cooldown Value (Seconds)")
    val defaultCooldownValue: Double?
    @JsonProperty("Guild Embed colour")
    val defaultEmbedColour: SimpleRGBColour?
    @JsonProperty("Default Guild Language")
    val defaultLangCode: LangCode?
    @JsonProperty("Rules")
    val rules: List<String>?
    @JsonProperty("Content Pinning enabled")
    val allowContentPinning: Boolean?
    @JsonProperty("Default User-set Languages enabled")
    val allowDefaultUserLanguages: Boolean?
    @JsonProperty("Custom User-defined Languages enabled")
    val allowCustomUserLanguages: Boolean?
    @JsonProperty("Reminders enabled")
    val allowReminders: Boolean?
    @JsonProperty("Reminder limit")
    val reminderTimeLimit: Double?

    constructor(botEnabled: Boolean?, botSafeMode: Boolean?, modules: List<Module>?, guildAdmins: List<Admin>?, respondToMentions: Boolean?, guildPrefixes: List<String>?, forceCooldowns: Boolean?, defaultCooldownValue: Double?, defaultEmbedColour: SimpleRGBColour?, defaultLangCode: LangCode?, rules: List<String>?, allowContentPinning: Boolean?, allowDefaultUserLanguages: Boolean?, allowCustomUserLanguages: Boolean?, allowReminders: Boolean?, reminderTimeLimit: Double?) {
        this.botEnabled = botEnabled
        this.botSafeMode = botSafeMode
        this.modules = modules
        this.guildAdmins = guildAdmins
        this.respondToMentions = respondToMentions
        this.guildPrefixes = guildPrefixes
        this.forceCooldowns = forceCooldowns
        this.defaultCooldownValue = defaultCooldownValue
        this.defaultEmbedColour = defaultEmbedColour
        this.defaultLangCode = defaultLangCode
        this.rules = rules
        this.allowContentPinning = allowContentPinning
        this.allowDefaultUserLanguages = allowDefaultUserLanguages
        this.allowCustomUserLanguages = allowCustomUserLanguages
        this.allowReminders = allowReminders
        this.reminderTimeLimit = reminderTimeLimit
    }
}

//TODO special permissions to bypass restrictions, like bypassing disabled reminders
class TextChannelConfig {
    @JsonProperty("Post User-join message")
    val postJoinMessages: Boolean?
    @JsonProperty("Join Message template")
    val joinMessageTemplate: String?
    @JsonProperty("Post User-exit message")
    val postExitMessages: Boolean?
    @JsonProperty("Exit Message template")
    val exitMessageTemplate: Boolean?
    @JsonProperty("Rules Channel")
    val rulesChannel: Boolean? //Delete any messages individuals send, only allow for the sending of '.rules accept'
    @JsonProperty("Rules")
    val rules: List<String>?
    //TODO if a TextChannel lists their own individual rules, the rules auto-generated for a rules channel should include sub-sections for each textchannel applicable
    @JsonProperty("Self Clearing enabled")
    val selfClearing: Boolean?
    @JsonProperty("Self-clearing delay")
    val selfClearingDelay: Int? //How many minutes to leave each message before deleting its
    @JsonProperty("Bot enabled")
    val botEnabled: Boolean?
    @JsonProperty("TextChannel Safe Mode enabled")
    val botSafeMode: Boolean?
    @JsonProperty("Modules")
    val modules: List<Module>?
    @JsonProperty("Guild Admins")
    val guildAdmins: List<Admin>?
    @JsonProperty("Respond to mentions")
    val respondToMentions: Boolean?
    @JsonProperty("Channel-wide Command Prefixes")
    val guildPrefixes: List<String>?
    @JsonProperty("Force Cooldowns")
    val forceCooldowns: Boolean?
    @JsonProperty("Default Channel Cooldown Value (Seconds)")
    val defaultCooldownValue: Double?
    @JsonProperty("Channel Embed colour")
    val defaultEmbedColour: SimpleRGBColour?
    @JsonProperty("Default Channel Language")
    val defaultLangCode: LangCode?
    @JsonProperty("Content Pinning enabled")
    val allowContentPinning: Boolean?
    @JsonProperty("Default User-set Languages enabled")
    val allowDefaultUserLanguages: Boolean?
    @JsonProperty("Custom User-defined Languages enabled")
    val allowCustomUserLanguages: Boolean?
    @JsonProperty("Reminders enabled")
    val allowReminders: Boolean?
    @JsonProperty("Reminder limit")
    val reminderTimeLimit: Double?

    constructor(postJoinMessages: Boolean?, joinMessageTemplate: String?, postExitMessages: Boolean?, exitMessageTemplate: Boolean?, rulesChannel: Boolean?, rules: List<String>?, selfClearing: Boolean?, selfClearingDelay: Int?, botEnabled: Boolean?, botSafeMode: Boolean?, modules: List<Module>?, guildAdmins: List<Admin>?, respondToMentions: Boolean?, guildPrefixes: List<String>?, forceCooldowns: Boolean?, defaultCooldownValue: Double?, defaultEmbedColour: SimpleRGBColour?, defaultLangCode: LangCode?, allowContentPinning: Boolean?, allowDefaultUserLanguages: Boolean?, allowCustomUserLanguages: Boolean?, allowReminders: Boolean?, reminderTimeLimit: Double?) {
        this.postJoinMessages = postJoinMessages
        this.joinMessageTemplate = joinMessageTemplate
        this.postExitMessages = postExitMessages
        this.exitMessageTemplate = exitMessageTemplate
        this.rulesChannel = rulesChannel
        this.rules = rules
        this.selfClearing = selfClearing
        this.selfClearingDelay = selfClearingDelay
        this.botEnabled = botEnabled
        this.botSafeMode = botSafeMode
        this.modules = modules
        this.guildAdmins = guildAdmins
        this.respondToMentions = respondToMentions
        this.guildPrefixes = guildPrefixes
        this.forceCooldowns = forceCooldowns
        this.defaultCooldownValue = defaultCooldownValue
        this.defaultEmbedColour = defaultEmbedColour
        this.defaultLangCode = defaultLangCode
        this.allowContentPinning = allowContentPinning
        this.allowDefaultUserLanguages = allowDefaultUserLanguages
        this.allowCustomUserLanguages = allowCustomUserLanguages
        this.allowReminders = allowReminders
        this.reminderTimeLimit = reminderTimeLimit
    }
}

class UserConfig {
    val preferredName: String?
    val preferredLanguage: LangCode?
    val preferredCmdPrefix: List<String>?
    
    constructor(preferredName: String?, preferredLanguage: LangCode?, preferredCmdPrefix: List<String>?) {
        this.preferredName = preferredName
        this.preferredLanguage = preferredLanguage
        this.preferredCmdPrefix = preferredCmdPrefix
    }
}

//TODO config for guild and textchannel spam filters. filter discord.gg links, foul words, etc.
//TODO config for levelling and points system
//todo boolean for giving points and levelling and ways of earning. Currency systems. Toggling conversions. Allowing/blocking global currency.
//TODO logging should allow for logging edited and deleted messages, as well as specifying a textchannel and what to log to each
//TODO A 'module' is a bot feature - such as reminders - and therefore also commands. Any command unlisted is classed as 'enabled', unless explicitly stated. Leaving a command listed but stated as enabled leaves it enabled as well.
