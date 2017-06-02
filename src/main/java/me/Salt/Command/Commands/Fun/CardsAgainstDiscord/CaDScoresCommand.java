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
package me.Salt.Command.Commands.Fun.CardsAgainstDiscord;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Exception.Command.DisabledCommandException;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Exception.Permission.LackingPermissionException;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.CaDGameHandler;
import me.Salt.Util.Utility.Games.GameManager;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

/**
 * This command provides a detailed leaderboard to the owner of a Cards Against Discord game.
 */
public class CaDScoresCommand extends Command implements ICommand {
    public CaDScoresCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }
    
    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event)
            throws LackingPermissionException, MissingDataException, DisabledCommandException {
        if (GameManager.hasGameOfType(event.getAuthor(), CaDGameHandler.class)) return true;
        else {
            event.getChannel().sendMessage("You don't currently have a game in progress!").queue();
            return false;
        }
    }
    
    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        CaDGameHandler gameHandler = (CaDGameHandler) GameManager.getGameOfType(e.getAuthor(), CaDGameHandler.class);
        StringBuilder sb = new StringBuilder();
        gameHandler.getAllPlayers()
                   .forEach(player -> sb.append(player.getUser().getName())
                                        .append(": Won: ")
                                        .append(player.getRoundsWon())
                                        .append(" Lost: ")
                                        .append(player.getRoundsLost() + "\n"));
        e.getChannel()
         .sendMessage(new EmbedBuilder().setTitle(
                 "Leaderboard info for " + gameHandler.getOwner().getUser().getName() + "'s Game", null)
                                        .setDescription(sb.toString())
                                        .build())
         .queue();
    }
    
    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {
    }
}
