/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.util.rest

import io.javalin.Context
import me.salt.entities.config.Configs
import me.salt.entities.config.entities.*
import me.salt.entities.objects.getConfig

//@Controller
//@Path("/configs")
//@Produces("application/json")
//@Api(value = "/configs", description = "Does some stuff")
/**
 * This class handles REST endpoints relating to the bot's Configuration System.
 */
object ConfigController {
    //    @GET
//    @ApiOperation(value = "/configs", response = String::class)

    fun getSaltConfigByType(ctx: Context): () -> (Context) {
        when (ctx.param("type")) {
            "main" -> return {
                ctx.json(Configs.salt.MAIN_CONFIG.getConfig(SaltConfig::class.java)
                        ?: ctx.status(204))
            }

            "language", "lang" -> return {
                ctx.json(Configs.salt.LANGUAGE_CONFIG.getConfig(SaltLanguageConfig::class.java)
                        ?: ctx.status(204))
            }

            "log" -> return {
                ctx.json(Configs.salt.LOG_CONFIG.getConfig(SaltLogConfig::class.java)
                        ?: ctx.status(204))
            }

            "stats", "statistics" -> return {
                ctx.json(Configs.salt.STATS_CONFIG.getConfig(SaltStatsConfig::class.java)
                        ?: ctx.status(204))
            }

            else -> return { ctx.status(404) }
        }
    }

    fun getSaltMapByType(ctx: Context): () -> (Context) {
        when (ctx.param("type")) {
            "filters", "filtering" -> return {
                ctx.json(Configs.salt.FILTERING_MAP.getConfig(FilteringMap::class.java)
                        ?: ctx.status(204))
            }

            "language", "lang" -> return {
                ctx.json(Configs.salt.LANGUAGE_MAP.getConfig(LanguageMap::class.java)
                        ?: ctx.status(204))
            }

            "levels", "levelling" -> return {
                ctx.json(Configs.salt.LEVELLING_MAP.getConfig(LevellingMap::class.java)
                        ?: ctx.status(204))
            }

            "perms", "permissions" -> return {
                ctx.json(Configs.salt.PERMISSIONS_MAP.getConfig(PermissionMap::class.java)
                        ?: ctx.status(204))
            }

            "sar", "self-roles" -> return {
                ctx.json(Configs.salt.SELF_ASSIGNABLE_ROLES_MAP.getConfig(RolesMap::class.java)
                        ?: ctx.status(204))
            }

            "stats", "statistics" -> return {
                ctx.json(Configs.salt.STAT_TRACKING_MAP.getConfig(StatTrackingMap::class.java)
                        ?: ctx.status(204))
            }

            else -> return { ctx.status(404) }
        }
    }

    fun getGuildConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getGuildMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getChannelConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getChannelMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getUserConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getUserMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getAllConfigs(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getAllConfigsByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getAllConfigsByCategory(ctx: Context): () -> (Context) {
        TODO()
    }

    fun searchAllConfigsByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun searchAllConfigsByCategoryAndType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getAllMaps(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getAllMapsByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getAllMapsByCategory(ctx: Context): () -> (Context) {
        TODO()
    }

    fun searchAllMapsByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun searchAllMapsByCategoryAndType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteSaltConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteSaltMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteGuildConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteGuildMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteChannelConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteChannelMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteUserConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteUserMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    /*
    Simply delete and regen the config. Should create again if it doesn't exist in the first place
     */

    fun resetSaltConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun resetSaltMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun resetGuildConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun resetGuildMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun resetChannelConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun resetChannelMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun resetUserConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun resetUserMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun patchSaltConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun patchSaltMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun patchGuildConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun patchGuildMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun patchChannelConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun patchChannelMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun patchUserConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun patchUserMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun downloadSaltConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun downloadSaltMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun downloadGuildConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun downloadGuildMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun downloadChannelConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun downloadChannelMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun downloadUserConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun downloadUserMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    //TODO making most of bot internal/avoiding exposing workings
}