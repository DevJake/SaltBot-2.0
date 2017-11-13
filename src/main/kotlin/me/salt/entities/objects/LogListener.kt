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

package me.salt.entities.objects

import me.salt.util.logging.logInfo
import net.dv8tion.jda.core.events.guild.GuildJoinEvent
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class LogListener : ListenerAdapter() {
    override fun onGuildJoin(event: GuildJoinEvent?) {
        logInfo("Bot added to Guild ${event?.guild?.name} (ID: ${event?.guild?.id})", "ACTIVITY")
    }

    override fun onGuildLeave(event: GuildLeaveEvent?) {
        logInfo("Bot left Guild ${event?.guild?.name} (ID: ${event?.guild?.id})", "ACTIVITY")
    }
}