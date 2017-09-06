/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.config.entities

import com.fasterxml.jackson.annotation.JsonProperty
import me.salt.util.*

class PermissionMap: ConfigMap {
    @JsonProperty("Groups")
    var groups: MutableList<GroupPermission>?
    @JsonProperty("Users")
    var users: MutableList<UserPermission>?

    private constructor(groups: MutableList<GroupPermission>?, users: MutableList<UserPermission>?) {
        this.groups = groups
        this.users = users
    }

    constructor(builder: PermissionMapBuilder): this(
            builder.groups,
            builder.users
    )
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

class LevellingMap: ConfigMap {
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

class FilteringMap: ConfigMap {
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
