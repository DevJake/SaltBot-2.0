package me.salt

import com.winterbe.expekt.should
import me.salt.exception.ColourValueException
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*

class SimpleRGBColourTest : Spek({
    given("an instance of SimpleRGBColour") {
        context("Updating and requesting values") {
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
                            e.should.be.instanceof(ColourValueException::class.java)
                        }
                    }
                }
            }
        }
    }

    context("Creating instances with incorrect values") {
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
        }
    }
})