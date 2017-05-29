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

public class CaDInfoCommand extends Command implements ICommand {
    public CaDInfoCommand(CommandContainer commandContainer) {
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
            CaDGameHandler game = (CaDGameHandler) GameManager.getGameOfType(e.getAuthor(), CaDGameHandler.class);
            EmbedBuilder eb = new EmbedBuilder().setTitle(e.getAuthor().getName() + "'s Game", null)
                                                .addField("Cards-per-Player", String.valueOf(game.getCardCount()), true)
                                                .addField("Winning Score", String.valueOf(game.getWinningScore()), true)
                                                .addField("Active?", (game.isActive() ? "✅" : "❌"), true);
            StringBuilder sb = new StringBuilder();
            game.getAllPlayers().forEach(player -> sb.append(player.getUser().getName()).append("\n"));
            eb.addField("Players", sb.toString(), true);
            e.getChannel().sendMessage(eb.build()).queue();
        } else {
            e.getChannel()
             .sendMessage("You don't have a registered Cards Against Discord session. You must create one first")
             .queue();
        }
    }
    
    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {
    }
}
