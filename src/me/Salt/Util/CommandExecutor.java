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

import me.Salt.Command.CommandContainer;
import me.Salt.Command.ICommand;
import me.Salt.Container.CommandParser;
import me.Salt.Exception.DuplicateDataException;
import me.Salt.Exception.MalformedParametersException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the execute cycle of a command
 */
public class CommandExecutor {
    private static List<CommandContainer> commands = new ArrayList<>();

    public static void execute(ICommand command, CommandParser.CommandContainer cmd) throws MalformedParametersException {

        Executor ex = (c, d) -> {
            if (c.preExecution(d)) {
                c.execute(cmd);
                c.postExecution(cmd);
            } else throw new MalformedParametersException("Malformed command parameters!");
            //TODO add Logger.write(...) call
        };

        ex.execute(command, cmd);
    }

    public static void addCommand(CommandContainer command) throws DuplicateDataException {
        //TODO make sure Commands cannot have duplicate commandCallers, either in the same command or different commands.
        if (commands != null) {
            if (command.getCommandDescription().getCommandCallers().stream().filter(s -> commands.stream().filter(c -> c.getCommandDescription().getCommandCallers().contains(s)).collect(Collectors.toList()).size() > 0).collect(Collectors.toList()).size() > 0) {
                throw new DuplicateDataException("Command Callers specified for \"" + command.getCommandDescription().getName() + "\" are overlapping with alternate Command Callers from different Commands!");
            } else {
                commands.add(command);
            }
        } else {
            commands.add(command);
        }
    }

    private interface Executor {
        void execute(ICommand command, CommandParser.CommandContainer cmd) throws MalformedParametersException;
    }
}
