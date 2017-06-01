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
import me.Salt.Main;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.CaDGameHandler;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.Player;
import me.Salt.Util.Utility.Games.GameManager;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.requests.RestAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This command is used to remove a player from a Cards Against Discord game. As would be expected, the command
 * executor must not only have a Cards Against Discord game session, but the session must also contain the specified
 * player.
 * <p>
 * This command requires one parameter minimum. The parameter must be either {@code u:} or {@code id:},
 * where either a full username or a user's ID must be immediately appended, respectively. For example, the following
 * command would remove a user with the username {@code user1} and a user with the ID of
 * {@code 203182861401063425} from the game - if they are <i>in</i> the game:
 * {@code .cadremove u:user1 id:203182861401063425}
 * <p>
 * A list of those removed is returned to the command executor.
 */
public class CaDRemovePlayerCommand extends Command implements ICommand {
    /**
     * A HashMap of Users and Players, where each {@link User} is tied to their respective instance
     * of {@link Player}.
     * <p>
     * This HashMap far simplifies the process of searching for a user's Player instance.
     */
    private HashMap<User, Player> players = new HashMap<>();
    /**
     * A List of {@link User} instances specified for removal from the game.
     */
    private List<User> toRemove = new ArrayList<>();
    /**
     * A List of {@link User} instances who have already been removed from the game. This List is necessary to
     * determine which users have and haven't been removed, through comparison of the old and new list of players.
     */
    private List<User> removed = new ArrayList<>();
    
    public CaDRemovePlayerCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }
    
    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event)
            throws LackingPermissionException, MissingDataException, DisabledCommandException {
        for (String arg : cmd.getArgsUpper()) {
            if (arg.startsWith("u:")) {
                String user = arg.replaceFirst("u:", "");
                if (user.length() <= 0) {
                    event.getChannel().sendMessage("You must specify a username!").queue();
                    return false;
                }
                if (Main.jda.getUsersByName(user, true).size() > 0) {
                    toRemove.addAll(Main.jda.getUsersByName(user, true));
                } else {
                    event.getChannel()
                         .sendMessage(
                                 "We couldn't find any users under the username " + user + "! \nPlease ensure you enter the *full* username")
                         .queue();
                    return false;
                }
            } else if (arg.startsWith("id:")) {
                String id = arg.replaceFirst("id:", "");
                if (Main.jda.getUserById(id) != null) {
                    toRemove.add(Main.jda.getUserById(id));
                }
            } else {
                event.getChannel().sendMessage("You must specify either a username or a user-id!").queue();
            }
        }
        return true;
    }
    
    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        if (GameManager.hasGameOfType(e.getAuthor(), CaDGameHandler.class)) {
            CaDGameHandler game = (CaDGameHandler) GameManager.getGameOfType(e.getAuthor(), CaDGameHandler.class);
            game.getPlayers().forEach(player -> players.put(player.getUser(), player));
            toRemove.forEach(user -> {
                if (players.containsKey(user)) game.removePlayer(players.get(user));
                removed.add(user);
            });
            RestAction r = removed.size() > 0 ? e.getChannel()
                                                 .sendMessage(
                                                         "Removed the following users from the game: " + removed.toString()) : e
                    .getChannel()
                    .sendMessage("No players matching your query were found. No users were removed from the game");
            r.queue();
        } else {
            e.getChannel()
             .sendMessage("You don't currently have a Cards Against Discord game session in progress!")
             .queue();
        }
    }
    
    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {
        players.clear();
        toRemove.clear();
        removed.clear();
    }
}
