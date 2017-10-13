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

import com.fasterxml.jackson.annotation.JsonProperty
import me.salt.config.ConfigHandler
import me.salt.config.Handler
import me.salt.lang.LangCode
import me.salt.objects.Admin
import me.salt.objects.Module
import me.salt.util.SimpleRGBColour

interface ConfigBuilder {
    fun build(): Config
}

interface ConfigMapBuilder {
    fun build(): ConfigMap
}

interface Configuration {
    fun write(handler: Handler) = ConfigHandler.writeConfig(handler, this)
    fun overwrite(handler: Handler) = ConfigHandler.overwriteConfig(handler, this
    )
}

interface Config : Configuration
interface ConfigMap : Configuration

class SaltConfig : Config {
    @JsonProperty("Bot_Token")
    val botToken: String?
    @JsonProperty("Global_Safe_Mode_enabled")
    var botSafeMode: Boolean?
    @JsonProperty("Modules")
    var modules: List<Module>?
    @JsonProperty("Global_Admins")
    var globalAdmins: List<Admin>?
    @JsonProperty("Respond_to_mentions")
    var respondToMentions: Boolean?
    @JsonProperty("Global_Command_Prefixes")
    var globalPrefixes: List<String>?
    @JsonProperty("Default_Cooldown_Value_(Seconds)")
    var defaultCooldownValue: Long?
    @JsonProperty("Default_Embed_colour")
    var defaultEmbedColour: SimpleRGBColour?
    @JsonProperty("Default_Language")
    var defaultLangCode: LangCode?

    constructor(builder: SaltConfigBuilder) : this(
            builder.botToken,
            builder.botSafeMode,
            builder.modules,
            builder.globalAdmins,
            builder.respondToMentions,
            builder.globalPrefixes,
            builder.defaultCooldownValue,
            builder.defaultEmbedColour,
            builder.defaultLangCode)

    constructor(
            botToken: String?,
            botSafeMode: Boolean?,
            modules: List<Module>?,
            globalAdmins: List<Admin>?,
            respondToMentions: Boolean?,
            globalPrefixes: List<String>?,
            defaultCooldownValue: Long?,
            defaultEmbedColour: SimpleRGBColour?,
            defaultLangCode: LangCode?) {
        this.botToken = botToken
        this.botSafeMode = botSafeMode
        this.modules = modules
        this.globalAdmins = globalAdmins
        this.respondToMentions = respondToMentions
        this.globalPrefixes = globalPrefixes
        this.defaultCooldownValue = defaultCooldownValue
        this.defaultEmbedColour = defaultEmbedColour
        this.defaultLangCode = defaultLangCode
    }

    override fun toString(): String {
        return "SaltConfig(botToken=$botToken, botSafeMode=$botSafeMode, modules=$modules, globalAdmins=$globalAdmins, respondToMentions=$respondToMentions, globalPrefixes=$globalPrefixes, defaultCooldownValue=$defaultCooldownValue, defaultEmbedColour=$defaultEmbedColour, defaultLangCode=$defaultLangCode)"
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
    @JsonProperty("Guild_wide Command Prefixes")
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
    @JsonProperty("Default User_set Languages enabled")
    val allowDefaultUserLanguages: Boolean?
    @JsonProperty("Custom User_defined Languages enabled")
    val allowCustomUserLanguages: Boolean?
    @JsonProperty("Reminders enabled")
    val allowReminders: Boolean?
    @JsonProperty("Reminder limit")
    val reminderTimeLimit: Double?

    private constructor(botEnabled: Boolean?, botSafeMode: Boolean?, modules: List<Module>?, guildAdmins: List<Admin>?, respondToMentions: Boolean?, guildPrefixes: List<String>?, forceCooldowns: Boolean?, defaultCooldownValue: Double?, defaultEmbedColour: SimpleRGBColour?, defaultLangCode: LangCode?, rules: List<String>?, allowContentPinning: Boolean?, allowDefaultUserLanguages: Boolean?, allowCustomUserLanguages: Boolean?, allowReminders: Boolean?, reminderTimeLimit: Double?) {
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
    @JsonProperty("Post User_join message")
    val postJoinMessages: Boolean?
    @JsonProperty("Join Message template")
    val joinMessageTemplate: String?
    @JsonProperty("Post User_exit message")
    val postExitMessages: Boolean?
    @JsonProperty("Exit Message template")
    val exitMessageTemplate: Boolean?
    @JsonProperty("Rules Channel")
    val rulesChannel: Boolean? //Delete any messages individuals send, only allow for the sending of '.rules accept'
    @JsonProperty("Rules")
    val rules: List<String>?
    //TODO if a TextChannel lists their own individual rules, the rules auto_generated for a rules channel should include sub_sections for each textchannel applicable
    @JsonProperty("Self Clearing enabled")
    val selfClearing: Boolean?
    @JsonProperty("Self_clearing delay")
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
    @JsonProperty("Channel_wide Command Prefixes")
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
    @JsonProperty("Default User_set Languages enabled")
    val allowDefaultUserLanguages: Boolean?
    @JsonProperty("Custom User_defined Languages enabled")
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
//TODO A 'module' is a bot feature _ such as reminders _ and therefore also commands. Any command unlisted is classed as 'enabled', unless explicitly stated. Leaving a command listed but stated as enabled leaves it enabled as well.
