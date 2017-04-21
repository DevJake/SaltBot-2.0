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
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 10/04/2017.
 * <p>
 * This interface defines methods that every Command must implement.
 * Typical operation of a command follows as:
 * <p>
 * Operation order:
 * preExecution() -&gt; execute() -&gt; postExecution()
 * <p>
 * However, if the preExecution method returns false, no further methods shall be called,
 * and the individual will be notified that they have entered incorrect parameters.
 */
public interface ICommand {
    /**
     * preExecution is the first method of a command that is called.
     * It allows the command to analyse the parameters inputted by the user,
     * and determine if they follow any patterns that may be required.
     *
     * @param cmd   CommandParser.ParsedCommandContainer - The parsed command inputted by the user
     * @param event GuildMessageReceivedEvent - The GuildMessageReceivedEvent that triggered this method being called
     * @return Boolean - A boolean representing if the CommandExecutor should continue with calling any further methods.
     */
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event);

    /**
     * The execute() method is where a command should place the functional part of the code; the code that actually performs the command's task.
     *
     * @param cmd CommandParser.ParsedCommandContainer - The parsed command inputted by the user
     * @param e   GuildMessageReceivedEvent - The GuildMessageReceivedEvent that triggered this method being called
     */
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e);

    /**
     * The postExecution() method is the last method called of a command.
     * It allows for clear-up processes to be run, and any potential memory leaks to be avoided.
     *
     * @param cmd CommandParser.ParsedCommandContainer - The parsed command inputted by the user
     */
    public void postExecution(CommandParser.ParsedCommandContainer cmd);

    /**
     * This class' purpose acts to ensure that each command extends the Command class.
     *
     * @return CommandContainer - This command's instance of a CommandContainer.
     */
    public CommandContainer getCmdContainer(); //Ensures the command extends Command.class
}
