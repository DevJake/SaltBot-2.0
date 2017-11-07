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

package me.salt.util

import java.util.regex.Pattern

object ArgsProcessor {
    private val arguments = mutableMapOf<String, MutableList<TiedArg>>()

    fun process(args: List<String>, argumentHandler: Argument): ArgumentReport {
        val map: MutableMap<String, ArgComponent> = mutableMapOf()

        args.forEach { map.put(it, argumentHandler.validityCheck.invoke(ArgComponent(), it)) }

        //TODO check for duplicates, add to ArgumentReport
        return ArgumentReport(map)
    }

    fun process(argString: String, argumentHandler: Argument) = process(argString.split(Pattern.compile(" ")), argumentHandler)

    fun addNewArg(argument: Argument, collectionName: String, tiedName: String) = arguments.getOrPut(tiedName, { mutableListOf(TiedArg(argument, tiedName)) }).add(TiedArg(argument, tiedName))

    fun getArgSet(collectionName: String, tiedName: String): List<TiedArg>? = arguments.get(collectionName)

    abstract class Argument(val name: String, val multiplicity: Multiplicity, val requiredArgs: List<String>?, val pretag: String, val validityCheck: (ArgComponent, arg: String) -> (ArgComponent))

    class GenericArgument(name: String, multiplicity: Multiplicity, requiredArgs: List<String>?, pretag: String, validityCheck: (ArgComponent, arg: String) -> ArgComponent) : Argument(name, multiplicity, requiredArgs, pretag, validityCheck)

    enum class Multiplicity {
        ONCE_EXACTLY,
        ONCE_OR_MORE,
        ZERO_OR_MORE,
        ZERO_OR_ONE
    }

    class ArgComponent {
        var accepts: Boolean = true
            private set

        fun accept() = apply { accepts = true }
        fun reject() = apply { accepts = false }
    }

    data class ArgumentReport(val results: Map<String, ArgComponent>, val duplicateComponents: List<ArgComponent> = emptyList()) {
        val passed: Boolean = results.values.none { !it.accepts }.and(duplicateComponents.isEmpty())
        val passedArgChecks: Boolean = results.values.none { !it.accepts }
        val passedDuplicateChecks: Boolean = duplicateComponents.isEmpty()
    }

    data class TiedArg(val argument: Argument, val name: String)
}
