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
import me.salt.util.exception.Errorlevel
import me.salt.util.exception.ExceptionHandler
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert

class CmdInstanceHandleTest: Spek({
    var ihand: CmdInstanceHandle = CmdInstanceHandle(CommandBuilder("prefix1", "name1").build())
    given("an instance of CmdInstanceHandle"){
        beforeEachTest { ihand = CmdInstanceHandle(CommandBuilder("prefix1", "name1").build()) }

        on("getting the default acceptance state"){
            it("should return false"){
                ihand.accepts.should.be.`false`
            }
        }

        on("calling the parameter-less accept method"){
            it("should update the acceptance state to true"){
                ihand.accept()
                ihand.accepts.should.be.`true`
            }

            it("should return our Command instance"){
                ihand.accept().should.be.instanceof(Command::class.java)
            }
        }

        on("calling the accept method with a callback"){
            it("should update the acceptance state to true"){
                ihand.accept({})
                ihand.accepts.should.be.`true`
            }

            it("should return our Command instance"){
                ihand.accept({}).should.be.instanceof(Command::class.java)
            }

            it("should not execute our callback"){
                ihand.accept({ Assert.fail("The callback was unexpectedly called!") })
            }
        }

        class TestException: RuntimeException()
        on("calling the reject method with an exception"){
            it("should update the acceptance state to false"){
                ihand.accept({})
                ihand.reject(TestException())
                ihand.accepts.should.be.`false`
            }

            it("should fire our given Exception"){
                ihand.reject(TestException())
                        ExceptionHandler.latestException.should.be.instanceof(TestException::class.java)
            }
        }

        on("calling the reject method with an exception and a level"){
            it("should update the acceptance state to false"){
                ihand.accept({})
                ihand.reject(TestException())
                ihand.accepts.should.be.`false`
            }

            it("should fire our given Exception"){
                ihand.reject(TestException(), Errorlevel.CRITICAL)
                        ExceptionHandler.latestException.should.be.instanceof(TestException::class.java)
            }
        }

        on("getting the default value of callback"){
            it("should be null"){
                ihand.callback.should.be.`null`
            }
        }
    }
})