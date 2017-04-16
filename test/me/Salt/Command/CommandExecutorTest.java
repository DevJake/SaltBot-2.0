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

import me.Salt.Exception.DuplicateDataException;
import me.Salt.Util.CommandExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 14/04/2017.
 */
class CommandExecutorTest {
    @Test
    void addCommandExpectException() {
        Assertions.assertThrows(DuplicateDataException.class, () -> {
            CommandExecutor.addCommand(
                    new CommandContainer(
                            new HelpCommand(),
                            new CommandDescription(
                                    null,
                                    null,
                                    "CmdA",
                                    null,
                                    null,
                                    false,
                                    false,
                                    null,
                                    null,
                                    false,
                                    Arrays.asList("cmdcallA", "cmdcallB")),
                            Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE)));

            CommandExecutor.addCommand(
                    new CommandContainer(
                            new HelpCommand(),
                            new CommandDescription(
                                    null,
                                    null,
                                    "CmdB",
                                    null,
                                    null,
                                    false,
                                    false,
                                    null,
                                    null,
                                    false,
                                    Arrays.asList("cmdcallP", "cmdcallH")),
                            Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE)));


            CommandExecutor.addCommand(
                    new CommandContainer(
                            new HelpCommand(),
                            new CommandDescription(
                                    null,
                                    null,
                                    "CmdC",
                                    null,
                                    null,
                                    false,
                                    false,
                                    null,
                                    null,
                                    false,
                                    Arrays.asList("cmdcallQ", "cmdcallA")),
                            Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE)));
        });
    }


    @Test
    void addCommandExpectPass() {
        try {
            CommandExecutor.addCommand(
                    new CommandContainer(
                            new HelpCommand(),
                            new CommandDescription(
                                    null,
                                    null,
                                    "CmdA",
                                    null,
                                    null,
                                    false,
                                    false,
                                    null,
                                    null,
                                    false,
                                    Arrays.asList("cmdcall1", "cmdcall2")),
                            Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE)));

            CommandExecutor.addCommand(
                    new CommandContainer(
                            new HelpCommand(),
                            new CommandDescription(
                                    null,
                                    null,
                                    "CmdB",
                                    null,
                                    null,
                                    false,
                                    false,
                                    null,
                                    null,
                                    false,
                                    Arrays.asList("cmdcall3", "cmdcall4")),
                            Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE)));


            CommandExecutor.addCommand(
                    new CommandContainer(
                            new HelpCommand(),
                            new CommandDescription(
                                    null,
                                    null,
                                    "CmdC",
                                    null,
                                    null,
                                    false,
                                    false,
                                    null,
                                    null,
                                    false,
                                    Arrays.asList("cmdcall5", "cmdcall6")),
                            Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE)));
        } catch (DuplicateDataException d) {
            Assertions.fail("Error was thrown!");
        }
    }
}