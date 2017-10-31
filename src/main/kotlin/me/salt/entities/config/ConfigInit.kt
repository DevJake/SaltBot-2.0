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

import me.salt.entities.config.entities.SaltConfigBuilder
import me.salt.entities.config.entities.SaltLogConfig
import me.salt.entities.objects.writeConfig

fun initConfigs() {
//TODO each Guild directory has their own sub-folders for TextChannels
    Configs.salt.MAIN_CONFIG.writeConfig(SaltConfigBuilder("bottoken").setRollbarAccessToken("token").build())
    Configs.salt.LOG_CONFIG.writeConfig(SaltLogConfig(true, true, false, true, mutableListOf()))
    //TODO add check that a config is being written to the correct location, such as preventing a filteringmap instance being written to a non-filteringmap.yaml file
}