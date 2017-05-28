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
import me.Salt.Util.Utility.Games.Game;
import me.Salt.Util.Utility.Games.GameManager;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.Map;

public class CaDViewGamesCommand extends Command implements ICommand {
    public CaDViewGamesCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) throws LackingPermissionException, MissingDataException, DisabledCommandException {
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Users running Cards Against Discord games", null);
        int total = 0;
        for (Map.Entry<User, List<Game>> userListEntry : GameManager.getUserGames().entrySet()) {
            CaDGameHandler game = null;
            for (Game g : userListEntry.getValue()){
                if (g instanceof CaDGameHandler) game = (CaDGameHandler) g;
            }

            if (game == null) continue;

            eb.addField(game.getOwner().getUser().getName() + "'s Game", "Players: " + game.getAllPlayers().size() + "\nWinning score: " + game.getWinningScore() + "\nCard Count: " + game.getCardCount() + "\nActive: " + (game.isActive() ? "✅" : "❌"), false);
        total++;
        }
// TODO: 27/05/2017 add pages to display
        if (total > 0)
        e.getChannel().sendMessage(eb.build()).queue();
        else e.getChannel().sendMessage("There are currently no players running Cards Against Discord sessions!").queue();
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
