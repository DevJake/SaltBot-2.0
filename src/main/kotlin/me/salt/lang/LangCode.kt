package me.salt.lang

enum class LangCode(val language: Language, val country: Country) {
    en_GB(Language.ENGLISH, Country.GREAT_BRITAIN),
    en_US(Language.ENGLISH, Country.UNITED_STATES);
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