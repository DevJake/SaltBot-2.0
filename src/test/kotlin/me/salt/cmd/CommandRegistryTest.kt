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

package me.salt.cmd

import com.winterbe.expekt.should
import me.salt.exception.ExecuteCommandFailureException
import me.salt.exception.PostExecuteCommandFailureException
import me.salt.exception.PreExecuteCommandFailureException
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions

class CommandRegistryTest: Spek({
    context("registering commands"){
        on("registering a single command") {
            it("should register the given command"){
                val testCommand0 = TestCommand("TestCommand")
                CommandRegistry.register(testCommand0)
                CommandRegistry.getCommands().should.contain(testCommand0)
            }
        }

        on("registering multiple commands") {
            it("should register the given commands"){
                val commands1 = TestCommand("TestCommand0")
                val commands2 = TestCommand("TestCommand1")
                val commands3 = TestCommand("TestCommand2")
                CommandRegistry.register(commands1, commands2, commands3)
                val commands = CommandRegistry.getCommands()
                Assertions.assertTrue(
                        commands.contains(commands1) &&
                                commands.contains(commands2) &&
                                commands.contains(commands3))
            }
        }
    }

    context("unregistering commands"){
        on("registering a single command") {
            it("should register the given command"){
                val testCommand0 = TestCommand("TestCommand")
                CommandRegistry.register(testCommand0)
                CommandRegistry.unregister(testCommand0)
                CommandRegistry.getCommands().should.not.contain(testCommand0)
            }
        }

        on("registering multiple commands") {
            it("should register the given commands"){
                val commands1 = TestCommand("TestCommand0")
                val commands2 = TestCommand("TestCommand1")
                val commands3 = TestCommand("TestCommand2")
                CommandRegistry.register(commands1, commands2, commands3)
                CommandRegistry.unregister(commands1, commands2, commands3)
                val commands = CommandRegistry.getCommands()
                commands.should.not.contain.elements(commands1, commands2, commands3)
            }
        }
    }
})

class TestCommand(name: String): Command(name) {
    override fun preExecute(cmd: CommandParser.CommandContainer) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(cmd: CommandParser.CommandContainer) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postExecute(cmd: CommandParser.CommandContainer) {
        throw PostExecuteCommandFailureException()
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}