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

package me.salt.entities.objects

data class Module(val name: String, val description: String?, val enabled: Boolean)
data class Admin(val name: String, val id: String)

data class SelfAssignableRole(val name: String?, val description: String?, val enabled: Boolean?, val restrictedUsers: List<String>?, var permissionGroups: List<String>?, var tiedRoles/*Roles given when this role is acquired. Offers an internal link to Discord's roles*/: List<String>?, var requiredRoles: List<String>?)

data class PermRole(val roleName: String, val requirementState: RoleRequirementState)

data class FilterMap(val filterTerm: String, val strictCasing: Boolean, val matchRepeatLetters: Boolean, val matchFillerChars: Boolean, val matchLeetAlts: Boolean)

data class LevellingPointComponent(val pointMethod: PointMethod, val value: Int)
