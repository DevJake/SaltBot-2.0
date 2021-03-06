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
import me.salt.entities.config.entities.CustomLang
import me.salt.entities.config.entities.LanguageMap
import me.salt.entities.objects.writeConfig

fun initLangs() {

    Configs.salt.LANGUAGE_MAP.writeConfig(
            LanguageMap().addLang(
                    CustomLang("EN_GB", "Administrator", null, null,
                            mapOf(
                                    Pair(LangTerm.USER_WELCOME, "Welcome to **guild**, **user**! :)"),
                                    Pair(LangTerm.USER_GOODBYE, "Goodbye **user**!"))
                    )
            ))
}

