package me.Salt.Util.Language;

import java.util.HashMap;

public class LanguageContainer {
    private String code; //such as en_GB
    private HashMap<LangString, String> strings = new HashMap<>();

    public LanguageContainer(String code, HashMap<LangString, String> strings) {
        this.code = code;
        this.strings = strings;
    }

    public String getCode() {
        return code;
    }

    public HashMap<LangString, String> getStrings() {
        return strings;
    }

    public String getString(LangString text) {
        return strings.getOrDefault(text, null);
    }
}
