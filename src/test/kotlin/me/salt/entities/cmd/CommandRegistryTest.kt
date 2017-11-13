/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.entities.cmd

import com.winterbe.expekt.should
import me.salt.util.exception.ExceptionHandler
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions

class CommandRegistryTest : Spek({
    ExceptionHandler.isTesting = true
    context("registering commands") {
        on("registering a single command") {
            it("should register the given command") {
                val testCommand0 = CommandBuilder("TestCommand", "testcmd").build()
                CommandRegistry.register(testCommand0)
                CommandRegistry.getCommands().should.contain(testCommand0)
            }
        }

        on("registering multiple commands") {
            it("should register the given commands") {
                val commands1 = CommandBuilder("TestCommand0", "testcmd").build()
                val commands2 = CommandBuilder("TestCommand1", "testcmd").build()
                val commands3 = CommandBuilder("TestCommand2", "testcmd").build()
                CommandRegistry.register(commands1, commands2, commands3)
                val commands = CommandRegistry.getCommands()
                Assertions.assertTrue(
                        commands.contains(commands1) &&
                                commands.contains(commands2) &&
                                commands.contains(commands3))
            }
        }
    }

    context("unregistering commands") {
        on("registering a single command") {
            it("should register the given command") {
                val testCommand0 = CommandBuilder("TestCommand", "testcmd").build()
                CommandRegistry.register(testCommand0)
                CommandRegistry.unregister(testCommand0)
                CommandRegistry.getCommands().should.not.contain(testCommand0)
            }
        }

        on("registering multiple commands") {
            it("should register the given commands") {
                val commands1 = CommandBuilder("TestCommand0", "testcmd").build()
                val commands2 = CommandBuilder("TestCommand1", "testcmd").build()
                val commands3 = CommandBuilder("TestCommand2", "testcmd").build()
                CommandRegistry.register(commands1, commands2, commands3)
                CommandRegistry.unregister(commands1, commands2, commands3)
                val commands = CommandRegistry.getCommands()
                commands.should.not.contain.elements(commands1, commands2, commands3)
            }
        }
    }
})