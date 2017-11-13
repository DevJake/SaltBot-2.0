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

package me.salt.entities.config.entities

import me.salt.entities.lang.LangCode
import me.salt.entities.objects.Admin
import me.salt.entities.objects.Module
import me.salt.entities.permissions.GroupPermission
import me.salt.entities.permissions.UserPermission
import me.salt.util.SimpleRGBColour
import me.salt.util.exception.ExcessiveValueException
import me.salt.util.exception.exception
import java.util.concurrent.TimeUnit

class SaltConfigBuilder(botToken: String) : ConfigBuilder {
    private var botToken: String = botToken
    private var botSafeMode: Boolean = false
    private var modules: MutableList<Module> = mutableListOf()
    private var globalAdmins: MutableList<Admin> = mutableListOf(Admin("Salt", "tobesorted"))
    private var respondToMentions: Boolean = true
    private var globalPrefixes: MutableList<String> = mutableListOf(".")
    private var defaultCooldownValue: Long = TimeUnit.SECONDS.toSeconds(10)
    private var defaultEmbedColour: SimpleRGBColour = SimpleRGBColour(255, 175, 175) /*Pink*/
    private var defaultLangCode: LangCode = LangCode.EN_GB
    private var rollbarAccessToken: String? = null

    override fun build(): Config {
        //TODO checking values, throwing exception if invalid values
        //TODO change so default values are assigned and/or added here. Allow build params which determine which params should be defaulted
        return SaltConfig(
                botToken,
                botSafeMode,
                modules,
                globalAdmins,
                respondToMentions,
                globalPrefixes,
                defaultCooldownValue,
                defaultEmbedColour,
                defaultLangCode,
                rollbarAccessToken
        )
    }

    //TODO check values in setters below, throwing runtime errors if incorrect values, and failing to update the value

    fun setBotToken(token: String) = apply { botToken = token }
    fun addModules(vararg modules: Module) = apply { this.modules.addAll(modules) }
    fun removeModules(vararg modules: Module) = apply { this.modules.removeAll(modules) }
    fun setBotSafeMode(safeMode: Boolean) = apply { botSafeMode = safeMode }
    fun addGlobalAdmins(vararg admins: Admin) = apply {
        if (globalAdmins.contains(Admin("Salt", "tobesorted")) && globalAdmins.size == 1) //TODO sort 'tobesorted'
            globalAdmins = mutableListOf()
        globalAdmins.addAll(admins)
    }

    fun removeGlobalAdmins(vararg admins: Admin) = apply { globalAdmins.removeAll(admins) }
    fun setRespondToMentions(respond: Boolean) = apply { respondToMentions = respond }
    fun addGlobalPrefixes(vararg prefixes: String) = apply {
        if (globalPrefixes.containsAll(listOf(".")) && globalPrefixes.size == 1)
            globalPrefixes = mutableListOf()
        globalPrefixes.addAll(prefixes)
    }

    //TODO prefixes and admins should not wipe if specifically adding default values.

    fun removeGlobalPrefixes(vararg prefixes: String) = apply { globalPrefixes.removeAll(prefixes) }
    fun setDefaultCooldownValue(timeUnit: TimeUnit, value: Int) = apply {
        val t = timeUnit.toSeconds(value.toLong())
        if (t < 0 || t > 86400 /*24 hours, as seconds*/) exception(ExcessiveValueException("Specified cooldown value of $t is out of bounds; value must be between 0 and 86400 (24 hours)"))
        defaultCooldownValue = t
    }

    fun setDefaultEmbedColour(colour: SimpleRGBColour) = apply { defaultEmbedColour = colour }
    fun setDefaultLangCode(langCode: LangCode) = apply { defaultLangCode = langCode }

    fun setRollbarAccessToken(token: String) = apply { rollbarAccessToken = token }

    //TODO move content pinning, reminders, language, etc. config stuff to own configs in /modules/ dir
}

class PermissionMapBuilder : ConfigMapBuilder {
    var groups: MutableList<GroupPermission> = mutableListOf()
        private set
    var users: MutableList<UserPermission> = mutableListOf()
        private set

    override fun build(): ConfigMap {
        return PermissionMap(this)
    }

    fun addGroups(vararg groups: GroupPermission) = apply { this.groups.addAll(groups) }
    fun removeGroups(vararg groups: GroupPermission) = apply { this.groups.removeAll(groups) }

    fun addUsers(vararg users: UserPermission) = apply { this.users.addAll(users) }
    fun removeUsers(vararg users: UserPermission) = apply { this.users.removeAll(users) }
}