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

package me.salt.permissions

import com.fasterxml.jackson.annotation.JsonIgnore
import me.salt.objects.Interaction

/*
Allows for a permission to state what levels it can only be used on,
and therefore what permissions will be required to write it.

For example, a permission might be declared as being at a bot level.
This means that those lacking authority perms at this level cannot have
this permission.
 */
object Authority {
    fun Bot(interaction: Interaction) = NodeAuthority(Level.BOT, interaction)
    fun Guild(interaction: Interaction) = NodeAuthority(Level.GUILD, interaction)
    fun Channel(interaction: Interaction) = NodeAuthority(Level.CHANNEL, interaction)
    fun None() = NodeAuthority(Level.NONE, Interaction.ALL)

    enum class Level {
        BOT,
        GUILD,
        CHANNEL,
        /*
        Usable at all levels, i.e., no explicit level; no perms are required for this level, but it provides authority on ALL levels
         */
        NONE;
    }

    /*
    Not possible to ensure an authority isn't directly added straight to the text file,
    but, the file can only be directly edited by those with access to it.
    In admin hosting, only an admin has access.

    This also provides more flexibility for self-hosters.

    As a result of this, we can allow groups to also provide NodeAuthority permissions, due to being a secure feature.
    TODO Any plans to allow users to download and upload configs should note down NodeAuthorities beforehand,
    TODO and fail changes where the NodeAuthorities change. This ensures that Authority's are only edited through commands.

    TODO add thorough unit testing to ensure security cannot be reached
     */
    data class NodeAuthority(val levels: MutableList<Level>, val interactions: MutableList<Interaction>) {
        fun addLevels(vararg levels: Level) = apply { this.levels.addAll(levels) }
        fun addInteractions(vararg interactions: Interaction) = apply { this.interactions.addAll(interactions) }

        constructor(level: Level, interaction: Interaction) :
                this(mutableListOf(level), mutableListOf(interaction))

        @JsonIgnore
        fun getAuthorityNodes(): List<String> {
            val nodes = mutableListOf<String>()

            this.levels.forEach { level ->
                this.interactions.forEach { inter ->
                    nodes.add("salt.authority.${level.name.toLowerCase()}.${inter.name.toLowerCase()}")
                }
            }
            return nodes
        }

        override fun toString(): String {
            return "NodeAuthority(levels=$levels, interactions=$interactions), authorityNodes=${getAuthorityNodes()}"
        }


    }
}