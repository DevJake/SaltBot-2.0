package me.salt.util

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class ArgsProcessorTest : Spek({
    context("GenericArgument class") {
        given("A single instance") {
            val arg: ArgsProcessor.GenericArgument = ArgsProcessor.GenericArgument("GenArg", ArgsProcessor.Multiplicity.ONCE_EXACTLY, null, "-d", { argc, arg ->
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

