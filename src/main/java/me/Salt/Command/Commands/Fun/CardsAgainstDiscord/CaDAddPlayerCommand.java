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
import me.Salt.Util.Utility.Games.GameManager;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class CaDAddPlayerCommand extends Command implements ICommand {
    public CaDAddPlayerCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) throws LackingPermissionException, MissingDataException, DisabledCommandException {
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        if (GameManager.hasGameOfType(e.getAuthor(), CaDGameHandler.class)) {
            if (GameManager.getGameOfType(e.getAuthor(), CaDGameHandler.class) != null) {
                CaDGameHandler cadGame = (CaDGameHandler) GameManager.getGameOfType(e.getAuthor(), CaDGameHandler.class);
                Boolean playerAddResponse = cadGame.addPlayer(new Player(e.getAuthor()));

                if (!playerAddResponse)
                    e.getChannel().sendMessage("Added you to the player list!" + cadGame.getPlayers()).queue();
                else e.getChannel().sendMessage("You're already on the player list!").queue();
            }
        } else {
            e.getChannel().sendMessage("You need to first create a game!").queue();
            // TODO: 26/05/2017 add info on how to create a game
        }
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
