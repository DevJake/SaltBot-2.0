/*
 *
 *  * Copyright (c) 2017 DevJake
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package me.Salt.Command.Commands.Informative;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.format.DateTimeFormatter;

public class GuildInfoCommand extends Command implements ICommand {
    public GuildInfoCommand(CommandContainer commandContainer) {
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
                        .addField(e.getGuild().getName(), "ID#" + e.getGuild().getIdLong(), true)
                        .addField("Region", e.getGuild().getRegion().toString(), true)
                        .addField("Online Users", e.getGuild().getMembers().stream().filter(member -> member.getOnlineStatus().equals(OnlineStatus.ONLINE)).count() + "/" + e.getGuild().getMembers().size(), true)
                        .addField("Owner", e.getGuild().getOwner().getAsMention(), true)
                        .addField("Voice Channels", String.valueOf(e.getGuild().getVoiceChannels().size()), true)
                        .addField("Text Channels", String.valueOf(e.getGuild().getTextChannels().size()), true)
                        .addField("Roles", String.valueOf(e.getGuild().getRoles().size()), true)
                        .addField("Creation Timestamp", e.getGuild().getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME), true)
                        .setColor(Main.salt.getEmbedColour())

                        .appendDescription("Generated in " + String.valueOf(System.currentTimeMillis() - n) + "ms")
                        .setFooter("Requested by " + e.getAuthor().getName() + " at " + e.getMessage().getCreationTime().plusHours(1).format(DateTimeFormatter.ISO_LOCAL_TIME), e.getAuthor().getAvatarUrl())
                        .build()).queue();
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
