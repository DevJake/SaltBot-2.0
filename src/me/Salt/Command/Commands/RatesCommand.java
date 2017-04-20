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

package me.Salt.Command.Commands;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 10/04/2017.
 */
public class RatesCommand extends Command implements ICommand {

    public RatesCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) {
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        e.getChannel().sendMessage(
                "Rate limits\n" +
                        "```http\n" +
                        "REST: \n" +
                        "       bot:msg:dm | 5/5s    | account-wide\n" +
                        "  bot:msg:channel | 5/5s    | channel-wide\n" +
                        "   bot:msg:global | 50/10s  | account-wide (excludes Direct Messages)\n" +
                        "             dmsg | 5/1s    | channel-wide\n" +
                        "            bdmsg | 1/1s    | channel-wide\n" +
                        "     guild_member | 10/10s  | guild-wide\n" +
                        "guild_member_nick | 1/1s    | account-wide\n" +
                        "         Username | 2/3600s | account-wide\n\n" +
                        "WS (WebSocket) Send: \n" +
                        "  Presense Update |   5/60s\n" +
                        "           Global | 120/60s\n```"
        ).queue(); //TODO consider moving text into a list, then using a forEach method call to add them to a StringBuilder. Would also allow command to be modified to return -single- rate limits, as well as all of them.
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
