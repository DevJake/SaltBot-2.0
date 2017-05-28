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
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.CaDGameManager;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.ResponseContainer;
import me.Salt.Util.Utility.Games.GameManager;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class CaDAddPlayerCommand extends Command implements ICommand {
    private boolean isInfo = false;
    private List<User> toInvite = new ArrayList<>();
    
    public CaDAddPlayerCommand(CommandContainer commandContainer) { super(commandContainer); }
    
    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event)
            throws LackingPermissionException, MissingDataException, DisabledCommandException {
        if (cmd.getArgsLower().size() > 0) for (String arg : cmd.getArgsUpper())
            if (arg.startsWith("u:")) {
                String user = arg.replaceFirst("u:", "");
                if (user.length() <= 0) {
                    event.getChannel().sendMessage("You must specify a username!").queue();
                    return false;
                }
                if (Main.jda.getUsersByName(user, true).size() > 0)
                    toInvite.addAll(Main.jda.getUsersByName(user, true));
                else {
                    event.getChannel()
                         .sendMessage(
                                 "We couldn't find any users under the username " + user + "! \nPlease ensure you enter the *full* username")
                         .queue();
                    return false;
                }
            } else if (arg.startsWith("id:")) {
                String id = arg.replaceFirst("id:", "");
                if (Main.jda.getUserById(id) != null) toInvite.add(Main.jda.getUserById(id));
            } else event.getChannel().sendMessage("You must specify either a username or a user-id!").queue(); /* TODO: 27/05/2017 move user lookup algorithm to utility class*/
        isInfo = toInvite.size() <= 0;
        if (isInfo) {
            event.getChannel()
                 .sendMessage("There is currently no help information available for this command!")
                 .queue();
            return false;
        }
        return true;
    }
    
    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        if (GameManager.hasGameOfType(e.getAuthor(), CaDGameHandler.class)) {
            if (GameManager.getGameOfType(e.getAuthor(), CaDGameHandler.class) != null) {
                CaDGameHandler cadGame = (CaDGameHandler) GameManager.getGameOfType(e.getAuthor(),
                        CaDGameHandler.class);
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Invited the following", null);
                toInvite.forEach(user -> eb.appendDescription(user.getName() + "\n"));
                e.getChannel().sendMessage(eb.build()).queue();
                toInvite.forEach(user -> {
                    user.openPrivateChannel().queue(privateChannel -> {
                        if (cadGame.containsPlayer(user)) return; //Don't allow for duplicate users in game. Failsafe
                        privateChannel.sendMessage(
                                new EmbedBuilder().setTitle("You have been invited to a Cards Against Humanity game!",
                                        null)
                                                  .addField("Owner", cadGame.getOwner().getUser().getName(), false)
                                                  .addField("Players", toInvite.toString(), false)
                                                  .addField("Winning Score", String.valueOf(cadGame.getWinningScore()),
                                                          false)
                                                  .addField("Cards-per-Player", String.valueOf(cadGame.getCardCount()),
                                                          false)
                                                  .appendDescription("Select the tick to accept this invite")
                                                  .build()).queue(message -> {
                            message.addReaction("✅").queue();
                            CaDGameManager.addToEmbeds(message.getId(),
                                    ResponseContainer.ResponseExpector.INVITE_RESPOND, cadGame);
                        });
                    });
                });
            }
        } else {
            e.getChannel().sendMessage("You need to first create a game!").queue(); /* TODO: 26/05/2017 add info on how to create a game*/
        }
    }
    
    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {
        isInfo = false;
        toInvite.clear();
    }
}