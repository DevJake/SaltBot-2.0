/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.lang

import me.salt.config.Configs
import me.salt.config.entities.LanguageMap

/*
A range of default languages defined in SaltLanguageMap.yaml
 */
enum class LangCode(val language: Language, val country: Country) {
    en_GB(Language.ENGLISH, Country.GREAT_BRITAIN),
    en_US(Language.ENGLISH, Country.UNITED_STATES);

    fun getLang() = Configs.SALT.LANGUAGE_MAP.getConfig(LanguageMap::class.java).getLanguage(this.name)

    fun filterBy(language: Language, country: Country, inverse: Boolean = false) =
            LangCode.values().toList().filter { (it.language == language && it.country == country) != inverse }

    fun filterBy(language: Language, inverse: Boolean = false) =
            LangCode.values().toList().filter { (it.language == language) != inverse }

    fun filterBy(country: Country, inverse: Boolean = false) =
            LangCode.values().toList().filter { (it.country == country) != inverse }
}

enum class Language {
    ENGLISH,
    FRENCH,
    RUSSIAN,
    GERMAN,
    SPANISH,
    MANDARIN;
}

enum class Country {
    GREAT_BRITAIN,
    UNITED_STATES,
    FRANCE,
    GERMANY,
    RUSSIA,
    CHINA,
    SPAIN;
}

enum class LangTerm(vararg val variables: Variable?) {
    USER_WELCOME(
            Variable("user", "User's nickname", "The nickname of the User who joined"),
            Variable("user_mention", "User's mention", "The User who joined, as a mention"),
            Variable("guild", "Guild's title", "The title of the Guild joined")),
    USER_GOODBYE(
            Variable("user", "User's nickname", "The nickname of the User who left"),
            Variable("guild", "Guild's title", "The title of the Guild left")),
    BOT_WELCOME,
    BOT_GOODBYE,
    USER_HAS_CHANNEL_PERMISSION,
    USER_HAS_GUILD_PERMISSION,
    USER_HAS_BOT_PERMISSION,
    USER_LACKING_CHANNEL_PERMISSION,
    USER_LACKING_GUILD_PERMISSION,
    USER_LACKING_BOT_PERMISSION,
    USER_HAS_AUTHORITY(
            Variable("level", "Authority Level", "The level of the authority"),
            Variable("interaction", "Authority Interaction type", "The type of interaction for the authority")),
    USER_LACKS_AUTHORITY(
            Variable("level", "Authority Level", "The level of the authority"),
            Variable("interaction", "Authority Interaction type", "The type of interaction for the authority")),
    CONFIG_CREATE_SUCCESS,
    CONFIG_CREATE_FAILURE,
    CONFIG_RESET_SUCCESS,
    CONFIG_RESET_FAILURE,
    CONFIG_RESTORE_SUCCESS, //TODO allow restoration of configs to version prior to edit. Useful if a mistake is made and requires a rollback
    CONFIG_RESTORE_FAILURE,
    CONFIG_UPDATE_SUCCESS,
    CONFIG_UPDATE_FAILURE,
    CONFIG_RETRIEVE_SUCCESS,
    CONFIG_RETRIEVE_FAILURE,
    EXPECTED_EXCEPTION, //Code was aware and prepared for the event the exception was thrown. Common in errors in user input
    UNEXPECTED_EXCEPTION; //Code didn't anticipate the exception; common in unchecked exceptions, caused commonly by internal code errors and mischecks

    //TODO params should be wrapped with double asterisks, i.e., **user**
    data class Variable(var term: String, var name: String, var description: String? = null)
}
