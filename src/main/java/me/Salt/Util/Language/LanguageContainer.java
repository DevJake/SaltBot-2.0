/*
 * Copyright (c) 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package main.java.me.Salt.Util.Language;

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
