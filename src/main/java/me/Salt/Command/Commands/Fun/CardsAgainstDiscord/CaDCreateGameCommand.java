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
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.Player;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.CaDGameManager;
import me.Salt.Util.Utility.Games.GameManager;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

/**
 * This command is used to create a new Cards Against Discord game, which is tied to the user's ID. The system checks
 * if the user already <i>has</i> a Cards Against Discord game session in existence, and prevents the adding of a new
 * session if one is found to already exist.
 * <p>
 * This system prevents abusive repetition of the command, as well as making the game-playing system far simpler for
 * each player.
 */
public class CaDCreateGameCommand extends Command implements ICommand {
    public CaDCreateGameCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }
    
    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event)
            throws LackingPermissionException, MissingDataException, DisabledCommandException {
        return true;
    }
    
    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        if (GameManager.hasGameOfType(e.getAuthor(), CaDGameHandler.class))
            e.getChannel().sendMessage("You already have a game in progress!").queue();
        else {
            CaDGameHandler c = new CaDGameHandler(5, 5, new Player(e.getAuthor()));
            GameManager.registerGame(e.getAuthor(), c);
            e.getChannel().sendMessage("Registered a new game under your name").queue();
            CaDGameManager.registerHandler(c);
        }
    }
    
    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {
    }
}
