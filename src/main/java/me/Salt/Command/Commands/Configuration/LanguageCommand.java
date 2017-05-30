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
package me.Salt.Command.Commands.Configuration;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Exception.Command.DisabledCommandException;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Exception.Permission.LackingPermissionException;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

/**
 * This command allows for the user to view, edit, add and remove languages to the bot's language system.
 * <p>
 * This command allows the user to:
 * <ul>
 * <li><b>View</b> a list of languages installed</li>
 * <li><b>Filter</b> the contents of each installed language</li>
 * <li><b>Edit</b> installed languages</li>
 * <li><b>Install</b> new languages</li>
 * <li><b>Uninstall</b> currently-installed languages</li>
 * </ul>
 */
public class LanguageCommand extends Command implements ICommand {
    public LanguageCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }
    
    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event)
            throws LackingPermissionException, MissingDataException, DisabledCommandException {
        return true;
    }
    
    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
    }
    
    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {
    }
}
