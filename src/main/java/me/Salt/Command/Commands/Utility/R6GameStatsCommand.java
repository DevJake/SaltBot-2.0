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
package me.Salt.Command.Commands.Utility;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Exception.Command.DisabledCommandException;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Exception.Permission.LackingPermissionException;
import me.Salt.Main;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.R6Player;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.R6Handler;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Util.Platform;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class R6GameStatsCommand extends Command implements ICommand {
    public R6GameStatsCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }
    
    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event)
            throws LackingPermissionException, MissingDataException, DisabledCommandException {
        //throw new DisabledCommandException("Sorry! This command is currently under construction...");
        return true;
    }
    
    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        R6Handler h = new R6Handler("https://api.r6stats.com/api/v1");
        R6Player r = h.getPlayerByName("Wallek224",
                Platform.UPLAY); //TODO add count of R6 command calls to stats. Allow stats command to show stats on more individual things, such as usages of each command
        //        e.getChannel().sendMessage(r.toString()).queue();
        e.getChannel()
         .sendMessage(
                 new EmbedBuilder()//TODO have R6Handler throw exception so that we can tell the user if the specified individual exists or not
                                   .setColor(Main.salt.getEmbedColour())
                                   .addField("Username", r.getUsername(), true)
                                   .addField("ID", r.getUbisoftId(), true)
                                   .addField("Platform", r.getPlatform().toString(), true)
                                   .addField("Headshots", String.valueOf(r.getStats().getOverall().getHeadshots()),
                                           true)
                                   .addField("Bullets Fired",
                                           String.valueOf(r.getStats().getOverall().getBulletsFired()), true)
                                   .addField("Bullets Hit", String.valueOf(r.getStats().getOverall().getBulletsHit()),
                                           true)
                                   .addField("Suicides", String.valueOf(r.getStats().getOverall().getSuicides()), true)
                                   .addField("Steps Moved", String.valueOf(r.getStats().getOverall().getStepsMoved()),
                                           true)
                                   .build())
         .queue();
    }
    
    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {
    }
}
