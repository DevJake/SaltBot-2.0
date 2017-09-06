/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.config

import me.salt.config.entities.*
import me.salt.util.*

fun initConfigs() {
    GenUtil.createFileFromResources("Data/Admin/Configs", "SaltConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Configs", "SaltConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Configs", "GuildConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Configs", "TextChannelConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Configs", "UserConfig.yaml")

    /*
    Things configured to be logged
     */
    GenUtil.createFileFromResources("Data/Admin/Configs", "SaltLogConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Logs", "SaltLogsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Logs", "GuildLogsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Logs", "TextChannelLogsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Logs", "UserLogsConfig.yaml")
//Stats
    GenUtil.createFileFromResources("Data/Admin/Configs", "SaltStatsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Stats", "SaltStatsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Stats", "GuildStatsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Stats", "TextChannelStatsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Stats", "UserStatsConfig.yaml")
// Languages
    GenUtil.createFileFromResources("Data/Admin/Configs", "SaltLanguageConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Languages", "SaltLanguageConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Languages", "GuildLanguageConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Languages", "TextChannelLanguageConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Languages", "UserLanguageConfig.yaml")
//Maps
    GenUtil.createFileFromResources("Data/Admin/DefaultMaps", "PermissionsMap.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultMaps", "SelfAssignableRoleMap.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultMaps", "FilteringMap.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultMaps", "StatTrackingMap.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultMaps", "LevellingMap.yaml")
    GenUtil.createFileFromResources("Data/Admin/Maps", "PermMap.yaml")
    GenUtil.createFileFromResources("Data/Admin/Maps", "SelfAssignableRoleMap.yaml")
    GenUtil.createFileFromResources("Data/Admin/Maps", "FilteringMap.yaml")
    GenUtil.createFileFromResources("Data/Admin/Maps", "StatTrackingMap.yaml")
    GenUtil.createFileFromResources("Data/Admin/Maps", "LevellingMap.yaml")

    GenUtil.createDirFromResources("Data/Guilds")
    GenUtil.createDirFromResources("Data/Users")
//TODO each Guild directory has their own sub-folders for TextChannels

//    ConfigHandler.writeValue("Data/Admin/DefaultConfigs/Configs/SaltConfig.yaml",
//            SaltConfigBuilder("MyBotToken", false,
//                    listOf(Module("Module 1", "Desc 1", true),
//                            Module("Module 2", "Desc 2", false)),
//                    listOf(Admin("John", "302483")),
//                    true, listOf("", "salt", "!"), true, 3.0,
//                    SimpleRGBColour(200, 20, 15),
//                    LangCode.en_GB, true, true,
//                    true, false, 0.0))

    //TODO add check that a config is being written to the correct location, such as preventing a filteringmap instance being written to a non-filteringmap.yaml file
    ConfigHandler.writeConfig(Configs.SALT.FILTERING_MAP,
            FilteringMap(true,
                    listOf(
                            FilterMap("Fuck", false, true, true, true),
                            FilterMap("Shit", false, true, true, true)),
                    true)
    )

    ConfigHandler.writeConfig(Configs.SALT.LEVELLING_MAP,
            LevellingMap(listOf(
                    LevellingPointComponent(LevellingPointComponent.PointMethod.EMOJI, 4),
                    LevellingPointComponent(LevellingPointComponent.PointMethod.MESSAGE_WITH_URL, 8)),
                    2.6, 500, Entity.GLOBAL)
    )

    ConfigHandler.writeConfig(Configs.SALT.MAIN_CONFIG,
            SaltConfigBuilder("tokeynbois")
                    .addModules(Module("module1", "desc1", false))
                    .build()
    )
}

fun checkForBrokenConfigs(){
    //TODO checking that there are no empty default configs or broken configs, such as broken formatting
}