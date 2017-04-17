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

package me.Salt.Command;

import me.Salt.Command.Container.CommandParser;
import me.Salt.Exception.MissingDataException;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 10/04/2017.
 */
public interface ICommand {
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, Event event);

    public void executeGuildMessageEvent(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e);

    public void executePrivateMessageEvent(CommandParser.ParsedCommandContainer cmd, PrivateMessageReceivedEvent e);


    public void postExecution(CommandParser.ParsedCommandContainer cmd);

    public CommandContainer getCmdContainer() throws MissingDataException; //Ensures the command extends Command.class

    public void setCmdContainer(CommandContainer cmdContainer); //Ensures the command extends Command.class
}
