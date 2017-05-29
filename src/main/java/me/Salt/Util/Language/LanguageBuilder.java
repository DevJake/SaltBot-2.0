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
package me.Salt.Util.Language;

import me.Salt.SaltAPI.Util.Interface.Buildable;

import java.util.HashMap;
import java.util.Map;

public class LanguageBuilder implements Buildable {
    private LangCode code; //such as en_GB
    private Map<LangString, String> strings = new HashMap<>();

    public LanguageBuilder setCode(LangCode code) {
        this.code = code;
        return this;
    }

    public LanguageBuilder addString(LangString langString, String value) {
        if (!this.strings.containsKey(langString)) this.strings.put(langString, value.toLowerCase());
        return this;
    }

    @Override
    public LanguageContainer build() {
        //TODO add checker
        return new LanguageContainer(code, strings);
    }
}
