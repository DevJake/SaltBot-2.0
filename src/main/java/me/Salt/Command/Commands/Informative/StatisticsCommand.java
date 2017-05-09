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
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Calculates, formats and provides detailed statistics on the bot, such as current Uptime and the total thread count.
 */
public class StatisticsCommand extends Command implements ICommand {

    public StatisticsCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) {
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        long n = System.currentTimeMillis();

        e.getChannel().sendMessage(new EmbedBuilder()
                .setTitle(Main.salt.getName() + " Statistics", null)
                .setColor(Main.salt.getEmbedColour())

                .addField("Uptime", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(Main.salt.getUptime()) + " seconds \n(" + TimeUnit.MILLISECONDS.toMinutes(Main.salt.getUptime()) + " minutes)"), true)
                .addField("Guild Count", String.valueOf(Main.jda.getGuilds().size()), true)
                .addField("User Count", String.valueOf(Main.jda.getUsers().size()), true)
                .addField("Messages Received", String.valueOf(Main.salt.getMessageCount()), true)
                .addField("Commands Received", String.valueOf(Main.salt.getCommandCount()), true)
                .addField("Command Count", String.valueOf(Main.salt.getCommands().size()), true)
                .addField("Threads", String.valueOf(Thread.activeCount()), true)
                .addField("Source Code", "[GitHub](https://github.com/DevJake/SaltBot-2.0)", true)
                .addField("Issue Reports", "[Issues](https://github.com/DevJake/SaltBot-2.0/issues)", true)
                .addField("Invite", "[Invite](https://discordapp.com/oauth2/authorize?client_id=246309425902649345&scope=bot&permissions=2146958463)", true)
                .addField("Author", Main.jda.getUserById("112633500447838208").getAsMention(), true)

                .appendDescription("Generated in " + String.valueOf(System.currentTimeMillis() - n) + "ms")
.setThumbnail(Main.jda.getSelfUser().getAvatarUrl())
                .setFooter("Requested by " + e.getAuthor().getName() + " at " + e.getMessage().getCreationTime().plusHours(1).format(DateTimeFormatter.ISO_LOCAL_TIME), e.getAuthor().getAvatarUrl())
                .build()).queue();
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
