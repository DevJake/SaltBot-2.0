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

package me.Salt.Command.Commands.Administrator;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Exception.Command.DisabledCommandException;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Exception.Permission.LackingPermissionException;
import me.Salt.Main;
import me.Salt.Permissions.Perm;
import me.Salt.Permissions.Permission;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

/**
 * This command is restricted to the bot owner, and is used solely for the controlled shutting down of the bot.
 */
public class ShutdownCommand extends Command implements ICommand {
    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) throws LackingPermissionException, MissingDataException, DisabledCommandException {
        System.out.println(Main.salt.getJUserById(event.getAuthor().getId()).hasPermission(Perm.COMMAND_SHUTDOWN_USE, Permission.Range.USER_WIDE));
        return Main.salt.getJUserById(event.getAuthor().getId()).hasPermission(Perm.COMMAND_SHUTDOWN_USE, Permission.Range.USER_WIDE);
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        //Main.jda.shutdown();
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }

    public ShutdownCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }
}
