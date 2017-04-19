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

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 18/04/2017.
 */
public class IssueCommand extends Command implements ICommand {
    public IssueCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, Event event) {
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        e.getChannel().sendMessage(new EmbedBuilder()
                .setColor(Main.salt.getEmbedColour())
                .setTitle("Reporting an issue", "https://github.com/DevJake/SaltBot-2.0/issues/new")
                .addField("Why raise an issue?", "You can raise an issue if you: \n" +
                                "> Have found a bug\n" +
                                "> Want to suggest a new feature (or changes to an existing one)\n" +
                                "> Have any general queries about the bot, a command, or anything else\n\n" +
                                "Notice: reporting a bug will result in gaining a reward, such as a boost to your currency or a free upgrade to premium!"
                        , true)
                .addField("Where can I raise an issue?", "Issues can be raised by following this link: \n" +
                        "https://github.com/DevJake/SaltBot-2.0/issues/new\n" +
                        "Or by clicking the title of this message!", true)
                .setThumbnail("https://cdn1.itcentralstation.com/vendors/logos/original/pBeeJQDQ_400x400.png")
                .setFooter("Requested by " + e.getAuthor().getName() + " at " + e.getMessage().getCreationTime().plusHours(1).format(DateTimeFormatter.ISO_LOCAL_TIME), null)
                .build()).queue();
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
