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

package me.salt

import com.winterbe.expekt.should
import me.salt.util.exception.ColourValueException
import me.salt.util.SimpleRGBColour
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import kotlin.test.fail

class SimpleRGBColourTest : Spek({
    context("updating and requesting values") {
        given("an instance of SimpleRGBColour") {
            describe("with red, green and blue set to zero") {
                var subject = SimpleRGBColour(0, 0, 0)
                on("requesting the value of red") {
                    it("should return zero") {
                        subject.red.should.equal(0)
                    }
                }

                on("requesting the value of green") {
                    it("should return zero") {
                        subject.green.should.equal(0)
                    }
                }

                on("requesting the value of blue") {
                    it("should return zero") {
                        subject.blue.should.equal(0)
                    }
                }

                on("updating and requesting the value of red to 200") {
                    it("should return 200") {
                        subject.red = 200
                        subject.red.should.equal(200)
                    }
                }

                on("updating and requesting the value of blue to 150") {
                    it("should return 150") {
                        subject.red = 150
                        subject.red.should.equal(150)
                    }
                }

                on("updating and requesting the value of green to 400") {
                    it("should throw a ColourValueException") {
                        try {
                            subject.green = 400
                        } catch (e: Exception) {
                            e.should.be.instanceof(ColourValueException::class.java)
                        }
                    }
                }

                on("updating and requesting the value of green to -10") {
                    it("should throw a ColourValueException") {
                        try {
                            subject.red = -10
                        } catch (e: Exception) {
                            e.should.be.an.instanceof(ColourValueException::class.java)
                        }
                    }
                }

                on("providing a valid value for transparency (200)") {
                    it("should return the value of 200 for transparency") {
                        subject.transparency = 200
                        subject.transparency.should.equal(200)
                    }
                }

                on("providing an invalid value for transparency (-10)") {
                    it("should throw a ColourValueException") {
                        try {
                            subject.transparency = -10
                        } catch (e: Exception) {
                            e.should.be.an.instanceof(ColourValueException::class.java)
                        }
                    }

                    it("should throw a ColourValueException with a set message") {
                        try {
                            subject.transparency = -10
                        } catch (e: Exception) {
                            e.message.should.equal("The value for transparency must be between 0 and 255")
                        }
                    }
                }
            }
        }

        context("creating instances with incorrect values") {
            given("an instance of SimpleRGBColour") {
                on("providing incorrect values and no transparency as parameters") {
                    it("should throw a ColourValueException") {
                        try {
                            SimpleRGBColour(0, 0, 400)
                        } catch (e: Exception) {
                            e.should.be.instanceof(ColourValueException::class.java)
                        }
                    }
                }

                on("providing an incorrect value for the transparency parameter") {
                    it("should throw a ColourValueException") {
                        try {
                            SimpleRGBColour(0, 0, 100, 400)
                        } catch (e: Exception) {
                            e.should.be.instanceof(ColourValueException::class.java)
                        }
                    }
                }
            }
        }

        context("creating instances with valid parameters") {
            given("an instance of SimpleRGBColour") {
                on("passing valid red, green and blue parameters, excluding transparency") {
                    it("should not throw any exception") {
                        try {
                            SimpleRGBColour(40, 30, 80)
                        } catch (e: Exception) {
                            fail("The valid instance threw an exception!")
                        }
                    }
                }

                on("passing valid red, green, blue and transparency parameters") {
                    it("should not throw any exception") {
                        try {
                            SimpleRGBColour(40, 30, 80, 60)
                        } catch (e: Exception) {
                            fail("The valid instance threw an exception!")
                        }
                    }
                }
            }
        }
    }
})