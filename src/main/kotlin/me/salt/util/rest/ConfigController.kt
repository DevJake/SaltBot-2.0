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

    internal fun getGuildConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getGuildMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getChannelConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getChannelMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getUserConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getUserMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getAllConfigs(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getAllConfigsByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getAllConfigsByCategory(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun searchAllConfigsByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun searchAllConfigsByCategoryAndType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getAllMaps(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getAllMapsByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getAllMapsByCategory(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun searchAllMapsByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun searchAllMapsByCategoryAndType(ctx: Context): () -> (Context) {
        TODO()
    }


    internal fun deleteSaltConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteSaltMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteGuildConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteGuildMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteChannelConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteChannelMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteUserConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteUserMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    /*
    Simply delete and regen the config. Should create again if it doesn't exist in the first place
     */

    internal fun resetSaltConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun resetSaltMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun resetGuildConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun resetGuildMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun resetChannelConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun resetChannelMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun resetUserConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun resetUserMapByType(ctx: Context): () -> (Context) {
        TODO()
    }


    internal fun patchSaltConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun patchSaltMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun patchGuildConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun patchGuildMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun patchChannelConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun patchChannelMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun patchUserConfigByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun patchUserMapByType(ctx: Context): () -> (Context) {
        TODO()
    }

    //TODO making most of bot internal/avoiding exposing internal workings
}