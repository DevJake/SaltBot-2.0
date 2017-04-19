/*
 * Copyright (c) 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.Salt.Command.Commands;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 18/04/2017.
 */
public class StatisticsCommand extends Command implements ICommand {

    public StatisticsCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, Event event) {
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        e.getChannel().sendMessage(new EmbedBuilder()
                .setTitle(Main.salt.getName() + " Statistics", null)
                .setColor(Main.salt.getEmbedColour())

                .addField("Uptime", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(Main.salt.getUptime()) + " seconds (" + TimeUnit.MILLISECONDS.toMinutes(Main.salt.getUptime()) + " minutes)"), true)
                .addField("Guild Count", String.valueOf(Main.jda.getGuilds().size()), true)
                .addField("User Count", String.valueOf(Main.jda.getUsers().size()), true)
                .addField("Messages Received", String.valueOf(Main.salt.getMessageCount()), true)
                .addField("Commands Received", String.valueOf(Main.salt.getCommandCount()), true)
                .addField("Command Count", String.valueOf(Main.salt.getCommands().size()), true)

                .setFooter("Requested by " + e.getAuthor().getName() + " at " + e.getMessage().getCreationTime().plusHours(1).format(DateTimeFormatter.ISO_LOCAL_TIME), null)
                .build()).queue();
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
