package me.Salt.Util.Language;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class LanguageContainer {
    @Expose
    private LangCode code; //such as en_GB
    @Expose
    private Map<LangString, String> strings = new HashMap<>();

    @Override
    public String toString() {
        return "LanguageContainer{" +
                "code='" + code + '\'' +
                ", strings=" + strings +
                '}';
    }

    public LanguageContainer(LanguageContainer l) {
        this.code = l.getCode();
        this.strings = l.getStrings();
    }

    public LanguageContainer(LangCode code, Map<LangString, String> strings) {
        this.code = code;
        this.strings = strings;
    }

    public LangCode getCode() {
        return code;
    }

    public Map<LangString, String> getStrings() {
        return strings;
    }

    public String getString(LangString text) {
        return strings.getOrDefault(text, null);
    }
}
