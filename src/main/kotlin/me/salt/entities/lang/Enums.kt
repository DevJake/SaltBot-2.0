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

import me.salt.entities.config.Configs
import me.salt.entities.config.entities.LanguageMap
import me.salt.entities.objects.getConfig

/*
A range of default languages defined in SaltLanguageMap.yaml

Source: http://quivi.sourceforge.net/languagecodes.html
 */
enum class LangCode(val language: Language, vararg val country: Country?) {
    EN(Language.ENGLISH),
    EN_GB(Language.ENGLISH, Country.GREAT_BRITAIN),
    EN_US(Language.ENGLISH, Country.UNITED_STATES),
    AF_ZA(Language.AFRIKAANS, Country.SOUTH_AFRICA, Country.NAMIBIA),
    SQ_AL(Language.ALBANIAN, Country.ALBANIA),
    AR(Language.ARABIC),
    AR_DZ(Language.ARABIC, Country.ALGERIA),
    AR_BH(Language.ARABIC, Country.BAHRAIN),
    AR_EG(Language.ARABIC, Country.EGYPT),
    AR_IQ(Language.ARABIC, Country.IRAQ),
    AR_JO(Language.ARABIC, Country.JORDAN),
    AR_KW(Language.ARABIC, Country.KUWAIT),
    AR_LB(Language.ARABIC, Country.LEBANON),
    AR_LY(Language.ARABIC, Country.LIBYA),
    AR_MA(Language.ARABIC, Country.MOROCCO),
    AR_OM(Language.ARABIC, Country.OMAN),
    AR_QA(Language.ARABIC, Country.QATAR),
    AR_SA(Language.ARABIC, Country.SAUDI_ARABIA),
    AR_SD(Language.ARABIC, Country.SUDAN),
    AR_SY(Language.ARABIC, Country.SYRIA),
    AR_TN(Language.ARABIC, Country.TUNISIA),
    AR_AE(Language.ARABIC, Country.UNITED_ARAB_EMIRATES),
    AR_YE(Language.ARABIC, Country.YEMEN);


    fun getLang() = Configs.salt.LANGUAGE_MAP.getConfig(LanguageMap::class.java)?.getLanguage(this.name)

    fun filterBy(language: Language, country: Country, inverse: Boolean = false) =
            LangCode.values().toList().filter { (it.language == language && it.country.contains(country)) != inverse }

    fun filterBy(language: Language, inverse: Boolean = false) =
            LangCode.values().toList().filter { (it.language == language) != inverse }

    fun filterBy(country: Country, inverse: Boolean = false) =
            LangCode.values().toList().filter { (it.country.contains(country)) != inverse }
}

enum class Language {
    ENGLISH,
    FRENCH,
    RUSSIAN,
    GERMAN,
    SPANISH,
    MANDARIN,
    AFRIKAANS,
    ALBANIAN,
    ARABIC,
}

enum class Country {
    GREAT_BRITAIN,
    UNITED_STATES,
    FRANCE,
    GERMANY,
    RUSSIA,
    CHINA,
    SPAIN,
    SOUTH_AFRICA,
    NAMIBIA,
    ALBANIA,
    ALGERIA,
    EGYPT,
    BAHRAIN,
    IRAQ,
    KUWAIT,
    JORDAN,
    LEBANON,
    LIBYA,
    SYRIA,
    UNITED_ARAB_EMIRATES,
    YEMEN,
    MOROCCO,
    OMAN,
    QATAR,
    SAUDI_ARABIA,
    SUDAN,
    TUNISIA
}

enum class LangTerm(vararg val variables: Variable?) {
    USER_WELCOME(
            Variable("user", "User's nickname", "The nickname of the User who joined"),
            Variable("user_mention", "User's mention", "The User who joined, as a mention"),
            Variable("guild", "Guild's title", "The title of the Guild joined")),
    USER_GOODBYE(
            Variable("user", "User's nickname", "The nickname of the User who left"),
            Variable("guild", "Guild's title", "The title of the Guild left")),
    BOT_WELCOME(),
    BOT_GOODBYE(),
    USER_HAS_CHANNEL_PERMISSION(),
    USER_HAS_GUILD_PERMISSION(),
    USER_HAS_BOT_PERMISSION(),
    USER_LACKING_CHANNEL_PERMISSION(),
    USER_LACKING_GUILD_PERMISSION(),
    USER_LACKING_BOT_PERMISSION(),
    USER_HAS_AUTHORITY(
            Variable("level", "Authority Level", "The level of the authority"),
            Variable("interaction", "Authority Interaction type", "The type of interaction for the authority")),
    USER_LACKS_AUTHORITY(
            Variable("level", "Authority Level", "The level of the authority"),
            Variable("interaction", "Authority Interaction type", "The type of interaction for the authority")),

    EXPECTED_EXCEPTION_DEFAULT(), //Code was aware and prepared for the event the exception was thrown. Common in errors in user input
    UNEXPECTED_EXCEPTION_DEFAULT(), //Code didn't anticipate the exception; common in unchecked exceptions, caused commonly by internal code errors and mischecks

    COMMAND_HELLO_WORLD(
            Variable("user", "User's nickname", "The nickname of the command sender"),
            Variable("time", "Event time", "The time at which the command was received")),
    COMMAND_PERMS_USER_HAS_PERM(),
    COMMAND_PERMS_USER_LACKS_PERM(),
    COMMAND_PERMS_ADD_PERM_USER_SUCCESS(),
    COMMAND_PERMS_ADD_PERM_USER_FAILURE(),
    COMMAND_PERMS_ADD_PERM_GROUP_SUCCESS(),
    COMMAND_PERMS_ADD_PERM_GROUP_FAILURE(),

    COMMAND_PERMS_REMOVE_PERM_USER_SUCCESS(),
    COMMAND_PERMS_REMOVE_PERM_USER_FAILURE(),
    COMMAND_PERMS_REMOVE_PERM_GROUP_SUCCESS(),
    COMMAND_PERMS_REMOVE_PERM_GROUP_FAILURE(),

    COMMAND_CONFIG_CREATE_SUCCESS(),
    COMMAND_CONFIG_CREATE_FAILURE(),
    COMMAND_CONFIG_RESET_SUCCESS(),
    COMMAND_CONFIG_RESET_FAILURE(),
    COMMAND_CONFIG_RESTORE_SUCCESS(), //TODO allow restoration of configs to version prior to edit. Useful if a mistake is made and requires a rollback
    COMMAND_CONFIG_RESTORE_FAILURE(),
    COMMAND_CONFIG_UPDATE_SUCCESS(),
    COMMAND_CONFIG_UPDATE_FAILURE(),
    COMMAND_CONFIG_RETRIEVE_SUCCESS(),
    COMMAND_CONFIG_RETRIEVE_FAILURE();




    //TODO params should be wrapped with double asterisks, i.e., **user**
    data class Variable(var term: String, var name: String, var description: String? = null)
}
