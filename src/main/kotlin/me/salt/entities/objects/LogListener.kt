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