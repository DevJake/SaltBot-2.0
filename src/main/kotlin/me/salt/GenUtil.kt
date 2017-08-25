package me.salt

import me.salt.exception.ColourValueException
import java.io.File

object GenUtil {
    val saltResourceDir = File(File(Main::class.java.protectionDomain.codeSource.location.toURI()).parentFile, "Resources")

    //TODO log directory/file creation. Logging system needs a log-cache, where entries are saved before mass-flushing.
    fun createDirFromResources(dirPath: String) {
        var file = File(saltResourceDir, dirPath)
        if (!file.isDirectory) println("Made dir: ${file.mkdirs()}")
    }

    fun createDirFromResources(dirPath: File) {
        var file = File(saltResourceDir, dirPath.parentFile.path)
        if (!file.isDirectory) println("Made dir: ${file.mkdirs()}")
    }

    fun createFileFromResources(dirPath: String, fileName: String) {
        var file = File(File(saltResourceDir, dirPath), fileName)
        if (!file.isDirectory) println("Made dir: ${file.parentFile.mkdirs()}")
        if (!file.exists()) println("Made new file: ${file.createNewFile()}")
    }

    fun createFileFromResources(fileDir: File) {
        var file = File(File(saltResourceDir, fileDir.parentFile.path), fileDir.name)
        if (!file.isDirectory) println("Made dir: ${file.parentFile.mkdirs()}")
        if (!file.exists()) println("Made new file: ${file.createNewFile()}")
    }
}

//TODO: add HEX support
data class SimpleRGBColour(var red: Int, var green: Int, var blue: Int) {
    var transparency = 255
        private set(value) { checkVal(value, "transparency") }

    private fun checkVal(value: Int, valName: String):Int = when (value) {
        !in 0..255 -> throw ColourValueException("The value for $valName must be between 0 and 255")
        else -> 255
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