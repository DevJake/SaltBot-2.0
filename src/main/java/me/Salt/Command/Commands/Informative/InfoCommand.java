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

package main.java.me.Salt.Command.Commands.Informative;

import main.java.me.Salt.Command.Command;
import main.java.me.Salt.Command.CommandContainer;
import main.java.me.Salt.Command.Container.CommandParser;
import main.java.me.Salt.Command.ICommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class InfoCommand extends Command implements ICommand {
    public InfoCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) {
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {

    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
