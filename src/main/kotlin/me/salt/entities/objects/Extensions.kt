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

package me.salt.entities.objects

import me.salt.entities.config.ConfigHandler
import me.salt.entities.config.Handler
import me.salt.entities.config.entities.Configuration
import me.salt.entities.config.entities.CustomLang
import me.salt.util.exception.LangConfigReadException
import me.salt.util.exception.exception
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun File.isEmpty(): Boolean {
    return BufferedReader(FileReader(this)).readLine() == null
}

fun List<CustomLang>.getByLangName(langName: String): CustomLang = try {
    this.first { it.languageName == langName }
} catch (e: Exception) {
    exception(LangConfigReadException("The language file, $langName, could not be read!"))
    //TODO change exception + message
    CustomLang("", null, null, null, emptyMap())
}

fun <E> List<E>.containsAny(list: List<E>): Boolean {
    list.forEach { if (this.contains(it)) return true }
    return false
}

/*
Get a list of indistinct elements. The opposite result of List<T>.distinct{ ... }
Effectively removes all distinct values from a list, leaving only those with duplicates
 */
fun <T, K> List<T>.indistinctBy(selector: (T) -> K): List<T> {
    val p = this.toMutableList()
    p.removeAll(this.distinctBy(selector))
    return p
}

fun <T : Configuration> Handler.getConfig(configClass: Class<T>): T? = ConfigHandler.readConfig(this, configClass)
fun Handler.overwriteConfig(config: Configuration) = ConfigHandler.overwriteConfig(this, config)
fun Handler.writeConfig(config: Configuration) = ConfigHandler.writeConfig(this, config)
fun Handler.exists(): Boolean = ConfigHandler.configExists(this)

//TODO add Config caching + method to force a refresh/boolean in read method