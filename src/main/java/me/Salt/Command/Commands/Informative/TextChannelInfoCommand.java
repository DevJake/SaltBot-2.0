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

package main.java.me.Salt.Command.Commands.Informative;

import main.java.me.Salt.Command.Command;
import main.java.me.Salt.Command.CommandContainer;
import main.java.me.Salt.Command.Container.CommandParser;
import main.java.me.Salt.Command.ICommand;
import main.java.me.Salt.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.format.DateTimeFormatter;

public class TextChannelInfoCommand extends Command implements ICommand {
    public TextChannelInfoCommand(CommandContainer commandContainer) {
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
                        .setThumbnail(e.getGuild().getIconUrl())
                        .addField(e.getChannel().getName(), "ID#" + e.getGuild().getIdLong(), true)
                        .addField("Topic", e.getChannel().getTopic().length() >0 ? e.getChannel().getTopic() : "None set", true)
                        .addField("Members", e.getChannel().getMembers().stream().filter(member -> member.getOnlineStatus().equals(OnlineStatus.ONLINE)).count() + "/" + e.getChannel().getMembers().size(), true)
                        .addField("Creation Timestamp", e.getChannel().getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME), true)

                        .setColor(Main.salt.getEmbedColour())
                        .appendDescription("Generated in " + String.valueOf(System.currentTimeMillis() - n) + "ms")
                        .setFooter("Requested by " + e.getAuthor().getName() + " at " + e.getMessage().getCreationTime().plusHours(1).format(DateTimeFormatter.ISO_LOCAL_TIME), e.getAuthor().getAvatarUrl())
                        .build()).queue();
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
