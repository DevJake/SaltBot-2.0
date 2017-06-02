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
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.CaDGameManager;
import me.Salt.Util.Utility.Games.GameManager;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

/**
 * This command is used to start a Cards Against Discord session, owned by the command executor.
 */
public class CaDStartGameCommand extends Command implements ICommand {
    public CaDStartGameCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }
    
    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event)
            throws LackingPermissionException, MissingDataException, DisabledCommandException {
        return true;
    }
    
    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        if (GameManager.hasGameOfType(e.getAuthor(), CaDGameHandler.class)) {
            if (((CaDGameHandler) GameManager.getGameOfType(e.getAuthor(), CaDGameHandler.class)).getAllPlayers()
                                                                                                 .size() < 3) {
                e.getChannel()
                 .sendMessage("You need at least three players in your game! Invite some to begin")
                 .queue();
            } else {
                e.getChannel().sendMessage("Starting your game!").queue();
                ((CaDGameHandler) GameManager.getGameOfType(e.getAuthor(), CaDGameHandler.class)).setActive(true);
                System.out.println("Attempting to modify");
                CaDGameManager.modifyHandler(e.getAuthor().getId(), CaDGameManager.PlayState.STARTING);
                // TODO: 27/05/2017 Register new handler to interact with game commands
            }
        } else {
            e.getChannel().sendMessage("You don't have a game! You need to create one first").queue();
        }
    }
    
    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {
    }
}
