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
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert

class CommandListenerTest : Spek({
    var listener = CommandListener()

    val cmdP0A0 = CommandBuilder("0", "").build()
    val cmdP0A1 = CommandBuilder("0", "").addAliases("2").build()
    val cmdP0A2 = CommandBuilder("0", "").addAliases("2", "3").build()
    val cmdP1A0 = CommandBuilder("1", "").build()
    val cmdP1A1 = CommandBuilder("1", "").addAliases("2").build()
    val cmdP1A2 = CommandBuilder("1", "").addAliases("2", "3").build()

    //Test using prefix "1" and aliases "2 3"

    beforeEachTest { listener = CommandListener() }

    context("filterByCommandPrefix") {
        on("filtering a list of commands where one contains our prefix") {
            val result = listOf(cmdP0A0, cmdP1A0, cmdP0A0).filterByCommandPrefix("1", false)
            it("should return a list with size 1") {
                result.size.should.equal(1)
            }

            it("should contain our expected command") {
                result.first().should.equal(cmdP1A0)
            }
        }

        on("filtering a list of commands where multiple contains our prefix") {
            val result = listOf(cmdP0A0, cmdP1A0, cmdP1A0).filterByCommandPrefix("1", false)
            it("should return a list with size 2") {
                result.size.should.equal(2)
            }

            it("should contain our expected command") {
                Assert.assertTrue(result[0] == cmdP1A0 && result[1] == cmdP1A0)
            }
        }

        on("filtering a list of commands where none contains our prefix") {
            val result = listOf(cmdP0A0, cmdP1A0, cmdP1A0).filterByCommandPrefix("2", false)
            it("should return a list with size 0") {
                result.size.should.equal(0)
            }
        }

        on("filtering a list of commands where one contains our prefix in the aliases") {
            val result = listOf(cmdP0A0, cmdP1A0, cmdP0A1).filterByCommandPrefix("2", true)
            it("should return a list with size 1") {
                result.size.should.equal(1)
            }

            it("should contain our expected command") {
                result.first().should.equal(cmdP0A1)
            }
        }

        on("filtering a list of commands where multiple contains our prefix in the aliases") {
            val result = listOf(cmdP0A0, cmdP0A1, cmdP0A2).filterByCommandPrefix("2", true)
            it("should return a list with size 2") {
                result.size.should.equal(2)
            }

            it("should contain our expected command") {
                Assert.assertTrue(result[0] == cmdP0A1 && result[1] == cmdP0A2)
            }
        }

        on("filtering a list of commands where none contains our prefix in the aliases") {
            val result = listOf(cmdP0A0, cmdP0A1, cmdP0A1).filterByCommandPrefix("3", true)
            it("should return a list with size 0") {
                result.size.should.equal(0)
            }
        }

        on("filtering a list of commands where none contains our prefix in the aliases and the prefix") {
            val result = listOf(cmdP0A0, cmdP0A1, cmdP0A0).filterByCommandPrefix("3", true)
            it("should return a list with size 0") {
                result.size.should.equal(0)
            }
        }

        on("filtering a list of commands where checkAliases false and the aliases contains our prefix") {
            val result = listOf(cmdP1A1, cmdP1A1, cmdP1A1).filterByCommandPrefix("2", false)
            it("should return a list with size 2") {
                result.size.should.equal(0)
            }
        }
    }
})