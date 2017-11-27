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

package me.salt.utilities

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class ArgsProcessorTest : Spek({
    context("GenericArgument class") {
        given("A single instance") {
            val arg: ArgsProcessor.GenericArgument = ArgsProcessor.GenericArgument("GenArg",
                    ArgsProcessor.Multiplicity.ONCE_EXACTLY,
                    null,
                    "-d",
                    { argc, arg ->
                        if (arg.toIntOrNull() == null)
                            argc.reject()
                        argc.accept()
                    })
            on("Processing the given instance against a valid input") {
                it("should return true") {
                    ArgsProcessor.process("-d 7", arg).passed.should.be.`true`
                }
            }
        }
    }
})

