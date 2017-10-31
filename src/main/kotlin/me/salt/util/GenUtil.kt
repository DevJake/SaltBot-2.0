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

package me.salt.util

import me.salt.Main
import me.salt.entities.objects.Colour
import me.salt.util.events.FileCreateEvent
import me.salt.util.events.fireEvent
import me.salt.util.exception.ColourValueException
import me.salt.util.exception.exception
import me.salt.util.logging.logDebug
import java.io.File

object GenUtil {
    val saltResourceDir = File(File(Main::class.java.protectionDomain.codeSource.location.toURI()).parentFile, "Resources")

    //TODO log directory/file creation. Logging system needs a log-cache, where entries are saved before mass-flushing.
    fun createDirFromResources(dirPath: String) {
        val file = File(saltResourceDir, dirPath)
        if (!file.isDirectory) {
            file.mkdirs()
            logDebug("Made new dir at path ${file.path}")
        }
        fireEvent(FileCreateEvent(file, false))
    }

    fun createDirFromResources(dirPath: File) {
        val file = File(saltResourceDir, dirPath.parentFile.path)
        if (!file.isDirectory) {
            file.mkdirs()
            logDebug("Made new dir at path ${file.path}")
        }
        fireEvent(FileCreateEvent(file, false))
    }

    fun createFileFromResources(dirPath: String, fileName: String) {
        val file = File(File(saltResourceDir, dirPath), fileName)
        if (!file.isDirectory) {
            file.mkdirs()
            logDebug("Made new dir at path ${file.path}")
        }
        if (!file.exists()) {
            file.createNewFile()
            logDebug("Made new file at path ${file.path}, name ${file.name}")
        }
        fireEvent(FileCreateEvent(file, true))
    }

    fun createFileFromResources(fileDir: File) {
        val file = File(File(saltResourceDir, fileDir.parentFile.path), fileDir.name)
        if (!file.isDirectory) {
            file.mkdirs()
            logDebug("Made new dir at path ${file.path}")
        }
        if (!file.exists()) {
            file.createNewFile()
            logDebug("Made new file at path ${file.path}, name ${file.name}")
            fireEvent(FileCreateEvent(file, true))
        }
    }

    fun createFile(file: File) {
        if (!file.isDirectory) {
            file.parentFile.mkdirs()
//            logDebug("Made new dir at path ${file.path}")
        }
        if (!file.exists()) {
            file.createNewFile()
//            logDebug("Made new file at path ${file.path}, name ${file.name}")
            fireEvent(FileCreateEvent(file, true))
        }
    }
}

//TODO: add HEX support
data class SimpleRGBColour(private val _red: Int, private val _green: Int, private val _blue: Int) {
    var green = _green
        set(value) {
            field = checkVal(value, "green")
        }
    var red = _red
        set(value) {
            field = checkVal(value, "green")
        }
    var blue = _blue
        set(value) {
            field = checkVal(value, "green")
        }
    var transparency = 255
        set(value) {
            field = checkVal(value, "transparency")
        }

    private fun checkVal(value: Int, valName: String): Int = when (value) {
        in 0..255 -> value
        else -> {
            exception(ColourValueException("The value for $valName must be between 0 and 255")); 0
        }
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