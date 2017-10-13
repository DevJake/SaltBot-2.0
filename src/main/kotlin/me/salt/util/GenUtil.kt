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

import me.salt.Main
import me.salt.events.FileCreateEvent
import me.salt.events.fireEvent
import me.salt.exception.ColourValueException
import me.salt.objects.Colour
import java.io.*

object GenUtil {
    val saltResourceDir = File(File(Main::class.java.protectionDomain.codeSource.location.toURI()).parentFile, "Resources")

    //TODO log directory/file creation. Logging system needs a log-cache, where entries are saved before mass-flushing.
    fun createDirFromResources(dirPath: String) {
        var file = File(saltResourceDir, dirPath)
        if (!file.isDirectory) println("Made dir: ${file.mkdirs()}")
        fireEvent(FileCreateEvent(file, false))
    }

    fun createDirFromResources(dirPath: File) {
        var file = File(saltResourceDir, dirPath.parentFile.path)
        if (!file.isDirectory) println("Made dir: ${file.mkdirs()}")
        fireEvent(FileCreateEvent(file, false))
    }

    fun createFileFromResources(dirPath: String, fileName: String) {
        var file = File(File(saltResourceDir, dirPath), fileName)
        if (!file.isDirectory) println("Made dir: ${file.parentFile.mkdirs()}")
        if (!file.exists()) println("Made new file: ${file.createNewFile()}")
        fireEvent(FileCreateEvent(file, true))
    }

    fun createFileFromResources(fileDir: File) {
        var file = File(File(saltResourceDir, fileDir.parentFile.path), fileDir.name)
        if (!file.isDirectory) println("Made dir: ${file.parentFile.mkdirs()}")
        if (!file.exists()) {
            println("Made new file: ${file.createNewFile()}")
            fireEvent(FileCreateEvent(file, true))
        }
    }

    fun createFile(file: File) {
        if (!file.isDirectory) println("Made dir: ${file.parentFile.mkdirs()}")
        if (!file.exists()) {
            println("Made new file: ${file.createNewFile()}")
            fireEvent(FileCreateEvent(file, true))
        }
    }
}

//TODO: add HEX support
data class SimpleRGBColour(var red: Int, var green: Int, var blue: Int) {
    var transparency = 255
        set(value) {
            field = checkVal(value, "transparency")
        }

    private fun checkVal(value: Int, valName: String): Int = when (value) {
        in 0..255 -> value
        else -> throw ColourValueException("The value for $valName must be between 0 and 255")
    }

    init {
        checkVal(component1(), "red")
        checkVal(component2(), "green")
        checkVal(component3(), "blue")
    }

    constructor(colour: Colour) : this(colour.red, colour.green, colour.blue)
    constructor(colour: Colour, transparency: Int) : this(colour.red, colour.green, colour.blue) {
        this.transparency = checkVal(transparency, "transparency")
    }

    constructor(red: Int, green: Int, blue: Int, transparency: Int) : this(red, green, blue) {
        this.transparency = checkVal(transparency, "transparency")
    }
}