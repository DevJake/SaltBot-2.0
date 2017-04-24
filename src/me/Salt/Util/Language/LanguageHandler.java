package me.Salt.Util.Language;

import java.util.List;

public class LanguageHandler {
    private List<LanguageContainer> languages;

    public LanguageContainer getLanguage(LangCode langCode) {
        //TODO add to ConfigurationImpl
        //TODO get a language, then add to languages list as a cache.
        return new LanguageContainer();
    }

    //TODO allow languages to be updated via command
    public List<LanguageContainer> getLanguages() {
        return languages;
    }
}
