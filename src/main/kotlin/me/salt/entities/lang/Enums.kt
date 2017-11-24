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
/**@param country
 * @param language
 * @author Nishan Fernando
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
    AR_YE(Language.ARABIC, Country.YEMEN),
    HY(Language.ARMENIAN, Country.ARMENIA),
    AS(Language.ASSAMESE, Country.ASSAM),
    AY(Language.AYMARA, Country.BOLIVIA, Country.PERU),
    AZ(Language.AZERI, Country.AZERBAIJAN, Country.AFGHANISTAN, Country.GEORGIA, Country.IRAN, Country.IRAQ, Country.RUSSIA, Country.SYRIA, Country.TURKEY, Country.TURKMENISTAN),
    AZ_CYRILLIC(Language.AZERI_CYRILLIC, Country.BELARUS, Country.BOSNIA_HERZEGOVINA, Country.BULGARIA, Country.KAZAKHSTAN, Country.KYRGYZSTAN, Country.MACEDONIA, Country.MONGOLIA, Country.MONTENEGRO, Country.RUSSIA, Country.SERBIA, Country.TAJIKISTAN, Country.UKRAINE),
    //    AZ_LATIN(Language.AZERI_LATIN,Country),
    BA(Language.BASHKIR, Country.BASHKORTOSTAN, Country.RUSSIA),
    EU_ES(Language.BASQUE, Country.BASQUE, Country.SPAIN, Country.FRANCE),
    BE_BY(Language.BELARUSIAN, Country.BELARUS, Country.POLAND, Country.CZECH_REPUBLIC, Country.UKRAINE, Country.LITHUANIA),
    BN(Language.BENGALI, Country.BANGLADESH, Country.INDIA),
    DZ(Language.BHUTANI, Country.BHUTAN),
    BH(Language.BIHARI, Country.BIHAR),
    BI(Language.BISLAMA, Country.VANUATA),
    BR(Language.BRETON, Country.BRITTANY),
    BG_BG(Language.BULGARIAN, Country.BULGARIA, Country.CZECH_REPUBLIC, Country.MOLDOVA, Country.UKRAINE, Country.SERBIA, Country.ALBANIA, Country.ROMANIA),
    MY(Language.BURMESE, Country.MYANMAR, Country.BURMA),
    KM(Language.KHMER, Country.CAMBODIA, Country.VIETNAM),
    CA_ES(Language.CATALAN, Country.ANDORRA, Country.ITALY, Country.SPAIN, Country.FRANCE),
    ZH_TW(Language.MANDARIN, Country.CHINA, Country.TAIWAN),
    ZH_HK(Language.MANDARIN, Country.HONG_KONG),
    ZH_MO(Language.MANDARIN, Country.MACAU),
    ZH_SG(Language.MANDARIN, Country.SINGAPORE),
    CO(Language.CORSICAN, Country.FRANCE, Country.ITALY),
    HR_HR(Language.CROATIAN, Country.CROATIA, Country.BOSNIA_HERZEGOVINA, Country.SERBIA, Country.SLOVAKIA, Country.CZECH_REPUBLIC, Country.MONTENEGRO, Country.AUSTRIA, Country.HUNGARY, Country.ITALY, Country.ROMANIA),
    CS_CZ(Language.CZECH, Country.CZECH_REPUBLIC, Country.AUSTRIA, Country.BOSNIA_HERZEGOVINA, Country.CROATIA, Country.POLAND, Country.ROMANIA, Country.SLOVAKIA),
    DA_DK(Language.DANISH, Country.DENMARK, Country.FAROE_ISLANDS, Country.GREENLAND, Country.GERMANY),
    PT_BR(Language.PORTUGUESE, Country.ANGOLA, Country.BRAZIL, Country.CAPE_VERDE, Country.EAST_TIMOR, Country.EQUATORIAL_GUINEA, Country.GUINEA_BISSAU, Country.MOZAMBIQUE, Country.PORTUGAL, Country.SAO_TOME_PRINCIPE, Country.MACAU, Country.ANDORRA, Country.CANADA, Country.INDIA, Country.FRANCE, Country.SPAIN, Country.JAPAN, Country.LUXEMBOURG, Country.MALAYSIA, Country.NAMIBIA, Country.PARAGUAY, Country.SOUTH_AFRICA, Country.SWITZERLAND, Country.UNITED_STATES, Country.URUGUAY, Country.VENEZUELA),
    NL_NL(Language.DUTCH, Country.ARUBA, Country.BELGIUM, Country.CURACAO, Country.NETHERLANDS, Country.SINT_MAARTEN, Country.SURINAME),
    NL_BE(Language.FLEMISH, Country.FLANDERS),


    fun getLang() = Configs.salt.LANGUAGE_MAP.getConfig(LanguageMap::class.java)?.getLanguage(this.name)

    fun filterBy(language: Language, country: Country, inverse: Boolean = false) =
            LangCode.values().toList().filter { (it.language == language && it.country.contains(country)) != inverse }

    fun filterBy(language: Language, inverse: Boolean = false) =
            LangCode.values().toList().filter { (it.language == language) != inverse }

    fun filterBy(country: Country, inverse: Boolean = false) =
            LangCode.values().toList().filter { (it.country.contains(country)) != inverse }
}


enum class Language {
    DUTCH,
    PORTUGUESE,
    DANISH,
    ENGLISH,
    FLEMISH,
    FRENCH,
    RUSSIAN,
    GERMAN,
    KHMER,
    CATALAN,
    BURMESE,
    CORSICAN,
    CZECH,
    SPANISH,
    CROATIAN,
    MANDARIN,
    AFRIKAANS,
    ALBANIAN,
    ARABIC,
    ARMENIAN,
    ASSAMESE,
    AYMARA,
    AZERI,
    AZERI_CYRILLIC,
    BASHKIR,
    BASQUE,
    BELARUSIAN,
    BENGALI,
    BHUTANI,
    BIHARI,
    BISLAMA,
    BRETON,
    BULGARIAN,

}

enum class Country {
    CZECH_REPUBLIC,
    BURMA,
    BELARUS,
    VIETNAM,
    DENMARK,
    FAROE_ISLANDS,
    LUXEMBOURG,
    GREENLAND,
    ARUBA,
    BELGIUM,
    CURACAO,
    NETHERLANDS,
    SINT_MAARTEN,
    SURINAME,
    FLANDERS,
    ANGOLA,
    BRAZIL,
    CANADA,
    CAPE_VERDE,
    JAPAN,
    SWITZERLAND,
    MALAYSIA,
    EAST_TIMOR,
    PARAGUAY,
    EQUATORIAL_GUINEA,
    GUINEA_BISSAU,
    URUGUAY,
    VENEZUELA,
    MOZAMBIQUE,
    PORTUGAL,
    SAO_TOME_PRINCIPE,
    ANDORRA,
    ITALY,
    TAIWAN,
    HONG_KONG,
    MACAU,
    SINGAPORE,
    CROATIA,
    SLOVAKIA,
    AUSTRIA,
    HUNGARY,
    CAMBODIA,
    BASHKORTOSTAN,
    AFGHANISTAN,
    MYANMAR,
    POLAND,
    MACEDONIA,
    KYRGYZSTAN,
    TURKEY,
    BOSNIA_HERZEGOVINA,
    MONGOLIA,
    TURKMENISTAN,
    TAJIKISTAN,
    MONTENEGRO,
    IRAN,
    BASQUE,
    KAZAKHSTAN,
    GEORGIA,
    AZERBAIJAN,
    LITHUANIA,
    BRITTANY,
    BIHAR,
    UKRAINE,
    BANGLADESH,
    BHUTAN,
    MOLDOVA,
    SERBIA,
    INDIA,
    GREAT_BRITAIN,
    VANUATA,
    UNITED_STATES,
    ROMANIA,
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
    TUNISIA,
    ARMENIA,
    ASSAM,
    BOLIVIA,
    PERU,
    BULGARIA,
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
