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

package me.salt.entities.lang

import me.salt.entities.config.entities.CustomLang
import me.salt.entities.config.entities.LanguageMap
import me.salt.entities.objects.getConfig
import me.salt.util.exception.ConfigMissingValueException
import me.salt.util.exception.exception
import java.util.regex.Pattern

object LangUtils {
    private val paramMatch = Pattern.compile("\\*\\*[A-z]+\\*\\*") //Matching **x/y/z**; language parameters

    fun getTerm(customLang: CustomLang, langTerm: LangTerm, replacements: MutableMap<String, String>): String {
        val repl = mutableMapOf<String, String>()
        replacements.forEach { s0, s1 -> repl.put(s0.toLowerCase(), s1) }
        var finalString = getTerm(customLang, langTerm)

        if (langTerm.variables.isEmpty()) return finalString
        //This LangCode has no defined variables, meaning the user has placed a non-existent variable
        val matcher = paramMatch.matcher(finalString)
        while (matcher.find()) {
            finalString = finalString.replace(matcher.group(0).toLowerCase(),
                    repl.get(matcher.group(0)
                            .removeSurrounding("**", "**")
                            .toLowerCase())
                            ?: { exception(ConfigMissingValueException()); "" }(), //TODO update to better exception. Means we didn't give the proper name of the variable
                    true)
//                else throw ConfigHandlerException("Uhh ohh...") //TODO use different exception + more useful message
        }

        //TODO Checking parameters for LangTerms are valid, and that specified languageOverride exists
        return finalString
    }

    fun getTerm(lang: CustomLang, langTerm: LangTerm): String {
        if (!checkLangChain(lang, listOf(langTerm)))
            exception(ConfigMissingValueException()) //TODO change exception and add message
        // No term exists within the chain!

        if (lang.termMap.containsKey(langTerm)) return lang.termMap.get(langTerm).toString()

        if (lang.languageOverride == null)
            exception(ConfigMissingValueException()) //TODO change exception and add message
        //There is no higher language to check
        val lMap = lang.languageOverride?.handler?.getConfig(LanguageMap::class.java)
        val filtered = lMap?.languages?.filter { lang.languageOverride.languageName == it.languageName }
        if (filtered?.isEmpty() as Boolean || filtered.size != 1)
            exception(ConfigMissingValueException()) //TODO change exception and add message
        /*
        Checked the overriden language, but it has no languages matching the one specified,
        or too many matching languages.

        This is a fail-safe check, as checkLangChain already checks this for us.
         */

        return getTerm(filtered.get(0), langTerm)
        //Recursive call to this method to check the overriden language's superior language
    }

    /*
    Returns a boolean reflecting if the specified customlang contains all of the specified langterms
     */
    fun checkLangChain(lang: CustomLang, shouldContain: List<LangTerm>): Boolean {
        if (!lang.termMap.keys.containsAll(shouldContain)) { //language doesn't define each LangTerm... now to checking overrides
            if (lang.languageOverride == null) {
                return false
            }

            val languages = lang.languageOverride.handler.getConfig(LanguageMap::class.java)?.languages
            val filtered = languages?.filter { cl -> lang.languageOverride.languageName == cl.languageName }
            if (filtered?.isEmpty() as Boolean) {
                return false
            }

            filtered.forEach { cl ->
                val p = shouldContain.toMutableList()
                p.removeAll(lang.termMap.keys)
                return checkLangChain(cl, p)
            }
        }
        return true
    }
}