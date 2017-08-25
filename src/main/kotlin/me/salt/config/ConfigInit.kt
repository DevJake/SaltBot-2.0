package me.salt.config

import me.salt.config.entities.PermissionMap
import me.salt.config.entities.SaltConfig
import me.salt.Admin
import me.salt.GroupPermission
import me.salt.Module
import me.salt.UserPermission
import me.salt.lang.LangCode
import me.salt.ConfigHandler
import me.salt.GenUtil
import me.salt.SimpleRGBColour

fun initConfigs() {
    GenUtil.createFileFromResources("Data/Admin", "SaltConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Configs", "SaltConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Configs", "GuildConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Configs", "TextChannelConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Configs", "UserConfig.yaml")

    GenUtil.createFileFromResources("Data/Admin", "SaltPermConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Permissions", "SaltPermConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Permissions", "GuildPermConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Permissions", "TextChannelPermConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Permissions", "UserPermConfig.yaml")

    /*
    Things configured to be logged
     */
    GenUtil.createFileFromResources("Data/Admin", "SaltLogConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Logs", "SaltLogsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Logs", "GuildLogsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Logs", "TextChannelLogsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Logs", "UserLogsConfig.yaml")
//Stats
    GenUtil.createFileFromResources("Data/Admin", "SaltStatsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Stats", "SaltStatsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Stats", "GuildStatsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Stats", "TextChannelStatsConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Stats", "UserStatsConfig.yaml")
// Languages
    GenUtil.createFileFromResources("Data/Admin", "SaltLanguageConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Languages", "SaltLanguageConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Languages", "GuildLanguageConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Languages", "TextChannelLanguageConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Languages", "UserLanguageConfig.yaml")
// Levelling
    GenUtil.createFileFromResources("Data/Admin", "SaltLevellingConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Levelling", "SaltLevellingConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Levelling", "GuildLevellingConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Levelling", "TextChannelLevellingConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Levelling", "UserLevellingConfig.yaml")
// Chat Filtering
    GenUtil.createFileFromResources("Data/Admin", "SaltFilteringConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Filtering", "SaltFilteringConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Filtering", "GuildFilteringConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Filtering", "TextChannelFilteringConfig.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultConfigs/Filtering", "UserFilteringConfig.yaml")

    GenUtil.createFileFromResources("Data/Admin/DefaultMaps", "PermissionsMap.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultMaps", "SelfAssignableRoleMap.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultMaps", "FilteringMap.yaml")
    GenUtil.createFileFromResources("Data/Admin/DefaultMaps", "LevellingMap.yaml")

    GenUtil.createDirFromResources("Data/Guilds")
    GenUtil.createDirFromResources("Data/Users")
//TODO add maps to defaultconfigs, rename defaultconfigs to defaults, and add system of copying relevant contents to new guilds/textchannels/users
//TODO each Guild directory has their own sub-folders for TextChannels

    ConfigHandler.writeValue("Data/Admin/DefaultConfigs/Configs/SaltConfig.yaml",
            SaltConfig("MyBotToken", false,
                    listOf(Module("Module 1", "Desc 1", true),
                            Module("Module 2", "Desc 2", false)),
                    listOf(Admin("John", "302483")),
                    true, listOf("", "salt", "!"), true, 3.0,
                    SimpleRGBColour(200, 20, 15),
                    LangCode.en_GB, true, true,
                    true, false, 0.0))

    ConfigHandler.writeValue("Data/Admin/DefaultMaps/PermissionsMap.yaml",
            PermissionMap(
                    listOf(
                            GroupPermission("Group 1", listOf("Perm 1", "Perm 2", "Perm 3")),
                            GroupPermission("Group 2", listOf("Perm 1", "Perm 2", "Perm 3"))),
                    listOf(
                            UserPermission("925872398572598275",
                                    listOf("Group 1", "Group 2"),
                                    listOf("UserPerm 1", "UserPerm 2", "UserPerm 3")),
                            UserPermission("949574953458903465",
                                    listOf("Group 2"),
                                    listOf("UserPerm 1"))
                    )))
}