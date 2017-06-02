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
 * This command checks if the executing user has an instance of a Cards Against Discord game and - if they do - ends
 * the game. Games can end in various manners;
 * <p>
 * Gracefully - Each player is notified of shutdown, given information about their stats, playtime, necessary
 * logging is performed, etc.
 * Succinctly - Each player is notified of the shutdown, followed by a game shutdown and logging.
 * Abruptly - The game ends immediately. Nobody is informed. <b>This method is <i>not</i> recommended.</b>
 */
public class CaDEndGameCommand extends Command implements ICommand {
    // TODO: 29/05/2017
    public CaDEndGameCommand(CommandContainer commandContainer) {
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
        gameHandler.getAllPlayers().forEach(player -> {
            sb.append(player.getUser().getName())
              .append(": Won: ")
              .append(player.getRoundsWon())
              .append(" Lost: ")
              .append(player.getRoundsLost() + "\n");
            player.getUser()
                  .openPrivateChannel()
                  .queue(privateChannel -> privateChannel.sendMessage(new EmbedBuilder().setTitle(
                          "Leaderboard info for " + gameHandler.getOwner().getUser().getName() + "'s Game", null)
                                                                                        .setDescription(sb.toString())
                                                                                        .setFooter(
                                                                                                "This game is now closing. Thanks for playing! ",
                                                                                                null)
                                                                                        .build()).queue());
        });
        GameManager.unregisterGameFromUser(e.getAuthor(),
                GameManager.getGameOfType(e.getAuthor(), CaDGameHandler.class));
        e.getChannel().sendMessage("Your game has now been unregistered.").queue();
    }
    
    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {
    }
}
