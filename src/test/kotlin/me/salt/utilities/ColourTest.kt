/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.utilities

import com.winterbe.expekt.should
import me.salt.entities.objects.Colour
import me.salt.utilities.exception.ExceptionHandler
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class ColourTest : Spek({
    ExceptionHandler.isTesting = true
    on("requesting the red, green and blue values of Colour.RED") {
        it("should return 255, 0 and 0, respectively") {
            Colour.RED.red.should.equal(255)
            Colour.RED.green.should.equal(0)
            Colour.RED.blue.should.equal(0)
        }
    }
})