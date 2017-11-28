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

import me.salt.entities.permissions.NodeBuilder
import me.salt.utilities.exception.CommandBuilderFailureException
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert

class CommandBuilderTest : Spek({
    context("CommandBuilder") {
        given("a CommandBuilder instance") {
            var cb = CommandBuilder("prefix", "name")
            beforeEachTest { cb = CommandBuilder("prefix", "name") }
            on("adding aliases") {
                it("should add the given aliases") {
                    cb.addAliases("Alias1", "Alias2")
                    val build = cb.build()
                    Assert.assertTrue(build.aliases.size == 2 && build.aliases.containsAll(listOf("alias1",
                            "alias2")))
                }
            }

            on("adding then removing the same aliases") {
                it("should list no aliases") {
                    cb.addAliases("Alias1", "Alias2")
                    cb.removeAliases("Alias1", "Alias2")
                    val build = cb.build()
                    Assert.assertTrue(build.aliases.isEmpty() && !build.aliases.containsAll(listOf("Alias1",
                            "Alias2")))
                }
            }

            on("adding aliases") {
                it("should convert them to lowercase") {
                    cb.addAliases("Alias1", "Alias2")
                    val build = cb.build()
                    Assert.assertTrue(build.aliases.size == 2 && build.aliases.containsAll(listOf("alias1",
                            "alias2")))
                }
            }

            on("setting the command prefix") {
                it("should convert it to lowercase") {
                    cb.setCmdPrefix("UpPeRcAsE")
                    Assert.assertTrue(cb.build().cmdPrefix == "uppercase")
                }
            }

            on("setting the name") {
                it("should build with the given name") {
                    cb.setName("MyCmdName")
                    Assert.assertTrue(cb.build().name == "MyCmdName")
                }
            }

            on("setting the description") {
                it("should build with the given description") {
                    cb.setDescription("MyCmdDesc")
                    Assert.assertTrue(cb.build().description == "MyCmdDesc")
                }
            }

            val func0 = { cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent, instHandler: CmdInstanceHandle ->
                instHandler.accept()
            }
            val func1 = { cmd: CommandParser.CommandContainer, event: GuildMessageReceivedEvent -> }

            on("setting the preExecute function") {
                it("should build with the given preExecute function") {
                    cb.preExecute(func0)
                    Assert.assertTrue(cb.build().preExecute == func0)
                }
            }

            on("setting the execute function") {
                it("should build with the given execute function") {
                    cb.execute(func0)
                    Assert.assertTrue(cb.build().execute == func0)
                }
            }

            on("setting the postExecute function") {
                it("should build with the given postExecute function") {
                    cb.postExecute(func1)
                    Assert.assertTrue(cb.build().postExecute == func1)
                }
            }

            on("setting the author") {
                it("should build with the given author") {
                    cb.setAuthor("Auth1")
                    Assert.assertTrue(cb.build().author == "Auth1")
                }
            }

            on("adding several permission Nodes") {
                it("should build with the given permission Nodes") {
                    val n0 = NodeBuilder("Node1").build()
                    val n1 = NodeBuilder("Node2").build()
                    cb.addPerms(n0, n1)
                    Assert.assertTrue(cb.build().perms.containsAll(listOf(n0, n1)))
                }
            }

            on("adding then removing several permission Nodes") {
                it("should build with none of the given permission Nodes") {
                    val n0 = NodeBuilder("Node1").build()
                    val n1 = NodeBuilder("Node2").build()
                    cb.addPerms(n0, n1)
                    cb.removePerms(n0, n1)

                    Assert.assertTrue(cb.build().perms.isEmpty())
                }
            }

            on("trying to set the cmdPrefix to an empty string"){
                it("should throw an Exception"){
                    try {
                        cb.setCmdPrefix("").build()
                        Assert.fail("Expected an instance of CommandBuilderFailureException to be thrown!")
                    } catch (e: CommandBuilderFailureException) {
                        Assert.assertTrue(true)
                    }
                }
            }

            on("trying to set the name to an empty string"){
                it("should throw an Exception"){
                    try {
                        cb.setName("").build()
                        Assert.fail("Expected an instance of CommandBuilderFailureException to be thrown!")
                    } catch (e: CommandBuilderFailureException) {
                        Assert.assertTrue(true)
                    }
                }
            }

            on("trying to add aliases with at least 1 empty"){
                it("should throw an Exception"){
                    try {
                        cb.addAliases("a1", "a2", "", "a4").build()
                        Assert.fail("Expected an instance of CommandBuilderFailureException to be thrown!")
                    } catch (e: CommandBuilderFailureException) {
                        Assert.assertTrue(true)
                    }
                }
            }
        }
    }
})
