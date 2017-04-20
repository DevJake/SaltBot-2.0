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

import java.util.concurrent.TimeUnit;

/**
 * Manages the execute cycle of a command. Currently only handles GuildMessageReceivedEvents
 */
public class CommandExecutor {

    public static void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) throws MalformedParametersException {
        if (!(event.getMessage().getRawContent().startsWith(Main.salt.getCmdPrefix()))) return;
        if (!(Main.salt.getCommands().containsKey(cmd.getCmd()))) {
            event.getChannel().sendMessage("Command not found!").queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        ICommand c = Main.salt.getCommands().get(cmd.getCmd());

        if (CooldownManager.isNotInCooldown(cmd.getCmd(), c, event.getAuthor(), System.currentTimeMillis())) { //Checking the individual is within the time cooldown
            if (c.preExecution(cmd, event)) {
                c.execute(cmd, event);
                c.postExecution(cmd);
            } else
                throw new MalformedParametersException("Incorrect command parameters given! Please use `" + Main.salt.getCmdPrefix() + "help " + cmd.getCmd() + "`");
        } else {
            String n = String.valueOf(CooldownManager.getRemainingTime(cmd.getCmd(), c, event.getAuthor()));
            if (Long.valueOf(n) >= 1000)
                event.getChannel().sendMessage(event.getAuthor().getAsMention() + ", you can use this command in " + n.substring(0, n.length() - 3) + "." + n.substring(n.length() - 3, n.length()) + " seconds. ").queue(m -> m.delete().queueAfter(2, TimeUnit.SECONDS));
            else
                event.getChannel().sendMessage(event.getAuthor().getAsMention() + ", you can use this command in <1 seconds.").queue(m -> m.delete().queueAfter(2, TimeUnit.SECONDS));
        }
        //TODO ensure all preExecution() methods throw a MalformedParametersException if incorrect parameters are inputted.
    }
}

