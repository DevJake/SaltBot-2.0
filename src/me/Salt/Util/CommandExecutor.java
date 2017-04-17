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

package me.Salt.Util;

import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Exception.MalformedParametersException;
import me.Salt.Main;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

import java.util.concurrent.TimeUnit;

/**
 * Manages the execute cycle of a command. Only handles GuildMessage* events and PrivateMessage* events.
 */
public class CommandExecutor {

    public static void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) throws MalformedParametersException {
        if (!(event.getMessage().getRawContent().startsWith(Main.salt.getCmdPrefix()))) return;
        if (!(Main.salt.getCommands().containsKey(cmd.getCmd()))) {
            event.getChannel().sendMessage("Command not found!").queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        ICommand c = Main.salt.getCommands().get(cmd.getCmd());

        if (c.preExecution(cmd, event)) {
            c.executeGuildMessageEvent(cmd, event);
            c.postExecution(cmd);
        } else throw new MalformedParametersException("Incorrect command parameters given!");

        //TODO ensure all preExecution() methods throw a MalformedParametersException if incorrect parameters are inputted.

    }

    public static void execute(CommandParser.ParsedCommandContainer cmd, PrivateMessageReceivedEvent event) throws MalformedParametersException {
        if (!(Main.salt.getCommands().containsKey(cmd.getCmd()))) {
            event.getChannel().sendMessage("Command not found!").queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;//TODO merge this code with the same code from above, into a single method.
        }

        ICommand c = Main.salt.getCommands().get(cmd.getCmd());

        if (c.preExecution(cmd, event)) {
            c.executePrivateMessageEvent(cmd, event);
            c.postExecution(cmd);
        } else throw new MalformedParametersException("Incorrect command parameters given!");

    }
}
