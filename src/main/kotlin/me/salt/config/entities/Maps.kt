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

package me.salt.config.entities

import com.fasterxml.jackson.annotation.JsonProperty
import me.salt.config.Handler
import me.salt.exception.ConfigMissingValueException
import me.salt.lang.LangTerm
import me.salt.lang.LangUtils
import me.salt.objects.*
import me.salt.permissions.GroupPermission
import me.salt.permissions.UserPermission

class PermissionMap : ConfigMap {
    @JsonProperty("Groups")
    var groups: List<GroupPermission>?
    @JsonProperty("Users")
    var users: List<UserPermission>?

//    constructor(groups: List<GroupPermission>?, users: List<UserPermission>?) {
//        if (groups != null && groups.indistinctBy { it.groupName }.isNotEmpty())
//            throw ConfigMissingValueException(
//                    "The provided GroupPermissions are not distinct! " +
//                            "Indistinct = ${groups.indistinctBy { it.groupName }}")
//
//        if (users != null && users.indistinctBy { it.userId }.isNotEmpty())
//            throw ConfigMissingValueException(
//                    "The provided UserPermissions are not distinct! " +
//                            "Indistinct = ${users.indistinctBy { it.userId }}")
//
//        this.groups = groups
//        this.users = users
//    }

    constructor(builder: PermissionMapBuilder) : this(
            builder.groups,
            builder.users
    )

    constructor(groups: List<GroupPermission>?, users: List<UserPermission>?) {
        this.groups = groups
        this.users = users
    }

    override fun toString(): String {
        return "PermissionMap(groups=$groups, users=$users)"
    }

}

class RolesMap {
    var roles: List<SelfAssignableRole>?

    constructor(roles: List<SelfAssignableRole>?) {
        this.roles = roles
    }
}

/*
No requirement for multiple RolesMap types, as they're consistent across a SaltRolesMap, Guild and TextChannel alternatives
 */

class LevellingMap : ConfigMap {
    @JsonProperty("Point scoring")
    val pointSystems: List<LevellingPointComponent>?
    @JsonProperty("Level multiplier")
    val levelMultiplier: Double?
    @JsonProperty("Base XP value")
    val baseXPValue: Int?
    @JsonProperty("Superior Entity base")
    val useSuperiorEntityLevel: Entity?

    constructor(pointSystems: List<LevellingPointComponent>?, levelMultiplier: Double?, baseXPValue: Int?, useSuperiorEntityLevel: Entity?) {
        this.pointSystems = pointSystems
        this.levelMultiplier = levelMultiplier
        this.baseXPValue = baseXPValue
        this.useSuperiorEntityLevel = useSuperiorEntityLevel
    }
}

//TODO enforcements to require a specific level before allowing usage of a command

class FilteringMap : ConfigMap {
    @JsonProperty("Filtering enabled")
    val enabled: Boolean?
    @JsonProperty("Filter Maps")
    val filterMaps: List<FilterMap>?
    @JsonProperty("Filter default 'foul terms' list")
    val filterDefaultFoulList: Boolean?

    constructor(enabled: Boolean?, filterMaps: List<FilterMap>?, filterDefaultFoulList: Boolean?) {
        this.enabled = enabled
        this.filterMaps = filterMaps
        this.filterDefaultFoulList = filterDefaultFoulList
    }
}

class LanguageMap : ConfigMap {
    val languages: MutableList<CustomLang> = mutableListOf()
    /*
    Allows for code to get an immutable set of the languages, whilst not exposing the mutable set.
    This forces usage of function addLang, which provides distinction checking of language names - a necessity.
     */

    fun addLang(vararg lang: CustomLang) = apply {
        val indistinct: MutableList<CustomLang> = mutableListOf()
        val distinct = lang.distinctBy { cl -> cl.languageName }
        lang.forEach { cl -> if (!distinct.contains(cl)) indistinct.add(cl) }

        if (indistinct.isNotEmpty())
            throw ConfigMissingValueException("Some languages lack distinct names! " +
                    "Indistinct names=${indistinct.mapTo(mutableListOf(), { cl -> cl.languageName }).distinct()}")
        //Calculates a list of language names for the indistinct instances, then makes the list entries distinct

        languages.addAll(lang)
    }

    fun getLanguage(langName: String) = languages.getByLangName(langName)
}

/*
Override the specified langcode (if present).
When an individual specifies the overriden langcode,
they are told that a selection of terms are overriden,
and can choose to be shown the edited list.

Note: overriden languages should swap out base terms for overriden ones,
whilst keeping any unmodified base terms
 */
data class CustomLang(val languageName: String, val author: String?, val permission: String?, val languageOverride: LanguageOverride?, val termMap: Map<LangTerm, String>) {
    fun getTerm(langTerm: LangTerm) = LangUtils.getTerm(this, langTerm)
    fun getTerm(langTerm: LangTerm, replacements: MutableMap<String, String>) = LangUtils.getTerm(this, langTerm, replacements)
    class LanguageOverride(val handler: Handler, val languageName: String)
}
