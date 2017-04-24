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

package me.Salt.Command.Commands.Informative;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Handler.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.format.DateTimeFormatter;

/**
 * Calculates a ping value (in milliseconds) and returns the value to the user.
 */
public class PingCommand extends Command implements ICommand {

    public PingCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) {
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        long n = System.currentTimeMillis();
        e.getChannel().sendMessage(
                new EmbedBuilder()
                        .appendDescription("Calculating...")
                        .setColor(Main.salt.getEmbedColour())
                        .build()).queue(m -> m.editMessage(
                new EmbedBuilder()
                        .setTitle("Pong! \uD83C\uDFD3", null)
                        .setFooter("Requested by " + e.getAuthor().getName() + " at " + e.getMessage().getCreationTime().plusHours(1).format(DateTimeFormatter.ISO_LOCAL_TIME), null)
                        .setDescription("Response speed of " + (System.currentTimeMillis() - n) + "ms")
                        .setColor(Main.salt.getEmbedColour()).build())
                .queue());
    }


    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
