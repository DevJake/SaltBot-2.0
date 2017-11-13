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

package me.salt.entities.config

import me.salt.entities.config.entities.*
import me.salt.entities.lang.LangTerm
import me.salt.entities.objects.exists
import me.salt.entities.objects.getConfig
import me.salt.entities.objects.overwriteConfig
import me.salt.entities.objects.writeConfig
import me.salt.util.logging.logFatal

fun initConfigs() {
//TODO each Guild directory has their own sub-folders for TextChannels
    Configs.salt.MAIN_CONFIG.writeConfig(SaltConfigBuilder("bottoken").setRollbarAccessToken("token").build())
    Configs.salt.LOG_CONFIG.writeConfig(SaltLogConfig(true, true, false, true, mutableListOf()))
    Configs.salt.LANGUAGE_MAP.overwriteConfig(
            LanguageMap()
                    .addLang(CustomLang("EN_GB", null, null, null, mapOf(Pair(LangTerm.BOT_WELCOME, "Welcome **user**!")))))
    
    //TODO add check that a config is being written to the correct location, such as preventing a filteringmap instance being written to a non-filteringmap.yaml file

    checkConfigs()
}

private fun checkConfigs() {
    if (!Configs.salt.MAIN_CONFIG.exists()) {
        logFatal("The SaltConfig for the Bot is non-existent!", "BOOT")
        System.exit(-1)
    }

    val mconf = Configs.salt.MAIN_CONFIG.getConfig(SaltConfig::class.java)

    if (mconf?.defaultLangCode == null) {
        logFatal("The Bot's MainConfig file lacks a value for defaultLangCode!", "BOOT")
        System.exit(-1)

        //TODO consider reworking configs to be able to mark fields as required/non-null
    }

    val lconf = Configs.salt.LANGUAGE_MAP.getConfig(LanguageMap::class.java)
    if (lconf == null) {
        logFatal("The LanguageConfig for the Bot is non-existent!", "BOOT")
        System.exit(-1)
    } else if (lconf.languages.size != LangTerm.values().size) {

        logFatal("The fallback language - ${mconf?.defaultLangCode} - is lacking ${LangTerm.values().size - lconf.languages.size}/${LangTerm.values().size} LangTerm definitions!", "BOOT")
        System.exit(-1)
    }
}
