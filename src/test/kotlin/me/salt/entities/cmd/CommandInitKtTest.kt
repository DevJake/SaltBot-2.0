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
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class CommandInitKtTest : Spek({
    on("calling the initCommands method") {
        initCommands()

        it("should register the HelloWorld Command") {
            CommandRegistry.getCommands().firstOrNull { it.cmdPrefix == "hello" }.should.not.be.`null`
        }

        it("should register the Math Command") {
            CommandRegistry.getCommands().firstOrNull { it.cmdPrefix == "math" }.should.not.be.`null`
        }

    }
})