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
import io.javalin.Javalin
import me.salt.entities.permissions.UserPermission
import me.salt.util.RestEndpointSearchElement
import me.salt.util.SearchUtil
import me.salt.util.exception.DuplicateRestEndpointException
import me.salt.util.exception.exception
import me.salt.util.logging.logDebug
import me.salt.util.logging.logInfo
import me.salt.util.rest.RestController.addDelete
import me.salt.util.rest.RestController.addGet
import me.salt.util.rest.RestController.addPatch
import me.salt.util.rest.RestController.addPost
import java.time.OffsetDateTime

object RestController {
    private val javalin = Javalin.create().port(80)

    private val tokens = mutableListOf<AccessToken>().plus(AccessToken("pp487", false, "salt", OffsetDateTime.now(), emptyList(), 0)).toMutableList()

    private val paths = mutableMapOf<String, (Context) -> () -> (Context)>()

    init {
        tokens.add(genToken())
        logInfo("Started Javalin REST API server on port 80", "API")
    }

    internal fun genToken(oldToken: AccessToken): AccessToken {
        logDebug("An old REST auth token has been traded for a new one. Old token=$oldToken")
        return genToken()
    }

    internal fun genToken(): AccessToken {
        return AccessToken("pp486", true, "salt", OffsetDateTime.now(), emptyList(), 0)
        /*
        Receive request -> Look for param declaring full name (or ID) of discord user to link to) -> message discord user -> if a
         */
    }

    private fun accessManager(ctx: Context): Context {
        val authHeader: String = ctx.header("authorization")
                ?: return ctx.status(400).result("You must include an auth token!")

        val tokenMap = tokens.map { it.token }
        if (!tokenMap.contains(authHeader)) return ctx.status(401).result("The specified auth token is non-existent!")
        else if (!tokens.first { it.token == authHeader }.active) return ctx.status(403).result("The specified token has been marked inactive!")
        return ctx.status(200)
    }

    internal fun addGet(path: String, call: (Context) -> () -> (Context)) {
        javalin.get("/api" + path, {
            invoke(it, call)
            callLog(it, "get")
        })
        postInvoke(path, call)
        regLog("/api" + path, "get")
    }

    internal fun addPost(path: String, call: (Context) -> () -> (Context)) {
        javalin.post("/api" + path, {
            invoke(it, call)
            callLog(it, "post")
        })
        postInvoke(path, call)
        regLog("/api" + path, "post")
    }

    internal fun addDelete(path: String, call: (Context) -> () -> (Context)) {
        javalin.delete("/api" + path, {
            invoke(it, call)
            callLog(it, "delete")
        })
        postInvoke(path, call)
        regLog("/api" + path, "delete")
    }

    internal fun addPut(path: String, call: (Context) -> () -> (Context)) {
        javalin.put("/api" + path, {
            invoke(it, call)
            callLog(it, "put")
        })
        postInvoke(path, call)
        regLog("/api" + path, "put")
    }

    internal fun addPatch(path: String, call: (Context) -> () -> (Context)) {
        javalin.patch("/api" + path, {
            invoke(it, call)
            callLog(it, "patch")
        })
        postInvoke(path, call)
        regLog("/api" + path, "patch")
    }

    internal fun addTrace(path: String, call: (Context) -> () -> (Context)) {
        javalin.trace("/api" + path, {
            invoke(it, call)
            callLog(it, "trace")
        })
        postInvoke(path, call)
        regLog("/api" + path, "trace")
    }

    private fun invoke(it: Context, call: (Context) -> () -> (Context)) {
        val ctx0 = accessManager(it)
        if (!listOf(400, 401, 403).contains(ctx0.status()))
            call.invoke(ctx0).invoke()
    }

    private fun postInvoke(path: String, call: (Context) -> () -> Context) {
        if (paths.filter { it.key == path || it.value == call }.isEmpty()) {
            paths.put(path, call)
            SearchUtil.addSearchables(RestEndpointSearchElement(SearchUtil.SearchCategory.REST_ENDPOINT, path.split("/"), null, call, path))
        }
        else
            exception(DuplicateRestEndpointException("The endpoint with path $path already shares either the path, function call, or both factors with another endpoint!"))
    }

    private fun regLog(path: String, type: String) = logDebug("Registered a new ${type.toUpperCase()} endpoint at path $path")
    private fun callLog(ctx: Context, type: String) = logDebug("Received ${type.toUpperCase()} request at endpoint ${ctx.path()} IP: ${ctx.ip()}")
    //TODO replace with call to ctx to obtain specific type, rather than relying on it being passed in as a parameter

    fun start() {
        javalin.start()
    }

    fun stop() {
        javalin.stop()
    }

}

data class AccessToken(
        val token: String,
        val active: Boolean,
        val tiedUserId: String,
        val genDateTime: OffsetDateTime,
        val accessPerms: List<UserPermission>,
        val hourlyRequestCap: Int
)

internal fun initRest() {
    addGet("/configs/salt/:type", { ConfigController.getSaltConfigByType(it) })
    addGet("/maps/salt/:type", { ConfigController.getSaltMapByType(it) })
    addGet("/configs/guild/:id/:type", { ConfigController.getGuildConfigByType(it) })
    addGet("/maps/guild/:id/:type", { ConfigController.getGuildMapByType(it) })
    addGet("/configs/channel/:id/:type", { ConfigController.getChannelConfigByType(it) })
    addGet("/maps/channel/:id/:type", { ConfigController.getChannelMapByType(it) })
    addGet("/configs/user/:id/:type", { ConfigController.getUserConfigByType(it) })
    addGet("/maps/user/:id/:type", { ConfigController.getUserMapByType(it) })
    addGet("/configs", { ConfigController.getAllConfigs(it) })
    addGet("/maps", { ConfigController.getAllMaps(it) })
    addGet("/configs/:type", { ConfigController.getAllConfigsByType(it) })
    addGet("/configs/:category", { ConfigController.getAllConfigsByCategory(it) })
    addGet("/maps/:type", { ConfigController.getAllMapsByType(it) })
    addGet("/maps/:category", { ConfigController.getAllMapsByCategory(it) })
    addGet("/configs/search/:type", { ConfigController.searchAllConfigsByType(it) })
    addGet("/maps/search/:type", { ConfigController.searchAllMapsByType(it) })
    addGet("/configs/search/:category/:type", { ConfigController.searchAllConfigsByCategoryAndType(it) })
    addGet("/maps/search/:category/:type", { ConfigController.searchAllMapsByCategoryAndType(it) })

    addDelete("/configs/salt/:type/delete", { ConfigController.deleteSaltConfigByType(it) })
    addDelete("/maps/salt/:type/delete", { ConfigController.deleteSaltMapByType(it) })
    addDelete("/configs/guild/:id/:type/delete", { ConfigController.deleteGuildConfigByType(it) })
    addDelete("/maps/guild/:id/:type/delete", { ConfigController.deleteGuildMapByType(it) })
    addDelete("/configs/channel/:id/:type/delete", { ConfigController.deleteChannelConfigByType(it) })
    addDelete("/maps/channel/:id/:type/delete", { ConfigController.deleteChannelMapByType(it) })
    addDelete("/configs/user/:id/:type/delete", { ConfigController.deleteUserConfigByType(it) })
    addDelete("/maps/user/:id/:type/delete", { ConfigController.deleteUserMapByType(it) })

    addPost("/configs/salt/:type/reset", { ConfigController.resetSaltConfigByType(it) })
    addPost("/maps/salt/:type/reset", { ConfigController.resetSaltMapByType(it) })
    addPost("/configs/guild/:id/:type/reset", { ConfigController.resetGuildConfigByType(it) })
    addPost("/maps/guild/:id/:type/reset", { ConfigController.resetGuildMapByType(it) })
    addPost("/configs/channel/:id/:type/reset", { ConfigController.resetChannelConfigByType(it) })
    addPost("/maps/channel/:id/:type/reset", { ConfigController.resetChannelMapByType(it) })
    addPost("/configs/user/:id/:type/reset", { ConfigController.resetUserConfigByType(it) })
    addPost("/maps/user/:id/:type/reset", { ConfigController.resetUserMapByType(it) })

    addPatch("/configs/salt/:type/patch", { ConfigController.patchSaltConfigByType(it) })
    addPatch("/maps/salt/:type/patch", { ConfigController.patchSaltMapByType(it) })
    addPatch("/configs/guild/:id/:type/patch", { ConfigController.patchGuildConfigByType(it) })
    addPatch("/maps/guild/:id/:type/patch", { ConfigController.patchGuildMapByType(it) })
    addPatch("/configs/channel/:id/:type/patch", { ConfigController.patchChannelConfigByType(it) })
    addPatch("/maps/channel/:id/:type/patch", { ConfigController.patchChannelMapByType(it) })
    addPatch("/configs/user/:id/:type/patch", { ConfigController.patchUserConfigByType(it) })
    addPatch("/maps/user/:id/:type/patch", { ConfigController.patchUserMapByType(it) })

    addGet("/logs/any/:id", { LogController.getLogById(it) })
    addGet("/logs/salt/:id/:datetime", { LogController.getSaltLogByIdAndDateTime(it) })
    addGet("/logs/guild/:id/:datetime", { LogController.getGuildLogByIdAndDateTime(it) })
    addGet("/logs/voicechannel/:id/:datetime", { LogController.getVoiceChannelLogByIdAndDateTime(it) })
    addGet("/logs/channel/:id/:datetime", { LogController.getChannelLogByIdAndDateTime(it) })
    addGet("/logs/user/:id/:datetime", { LogController.getUserLogByIdAndDateTime(it) })
    addGet("/logs/salt/:id/:entry", { LogController.getSaltLogByIdAndEntryType(it) })
    addGet("/logs/guild/:id/:entry", { LogController.getGuildLogByIdAndEntryType(it) })
    addGet("/logs/voicechannel/:id/:entry", { LogController.getVoiceChannelLogByIdAndEntryType(it) })
    addGet("/logs/channel/:id/:entry", { LogController.getChannelLogByIdAndEntryType(it) })
    addGet("/logs/user/:id/:entry", { LogController.getUserLogByIdAndEntryType(it) })
    addGet("/logs/salt/:id/:datetime/:entry", { LogController.getSaltLogByIdAndDateTimeAndEntryType(it) })
    addGet("/logs/guild/:id/:datetime/:entry", { LogController.getGuildLogByIdAndDateTimeAndEntryType(it) })
    addGet("/logs/voicechannel/:id/:datetime/:entry", { LogController.getVoiceChannelLogByIdAndDateTimeAndEntryType(it) })
    addGet("/logs/channel/:id/:datetime/:entry", { LogController.getChannelLogByIdAndDateTimeAndEntryType(it) })
    addGet("/logs/user/:id/:datetime/:entry", { LogController.getUserLogByIdAndDateTimeAndEntryType(it) })
    addGet("/logs/salt/:entry", { LogController.getSaltLogByEntryType(it) })
    addGet("/logs/guild/:entry", { LogController.getGuildLogByEntryType(it) })
    addGet("/logs/voicechannel/:entry", { LogController.getVoiceChannelLogByEntryType(it) })
    addGet("/logs/channel/:entry", { LogController.getChannelLogByEntryType(it) })
    addGet("/logs/user/:entry", { LogController.getUserLogByEntryType(it) })
    //TODO Search feature. Annotations for these methods
    addDelete("/logs/:id/delete") { LogController.deleteLogById(it) }
    addDelete("/logs/salt/:idstart/:idend/delete") { LogController.deleteAllSaltLogsByIdRange(it) }
    addDelete("/logs/guild/:idstart/:idend/delete") { LogController.deleteAllGuildLogsByIdRange(it) }
    addDelete("/logs/voicechannel/:idstart/:idend/delete") { LogController.deleteAllVoiceChannelLogsByIdRange(it) }
    addDelete("/logs/channel/:idstart/:idend/delete") { LogController.deleteAllChannelLogsByIdRange(it) }
    addDelete("/logs/user/:idstart/:idend/delete") { LogController.deleteAllUserLogsByIdRange(it) }
    addDelete("/logs/delete") { LogController.deleteAllLogs(it) }

    //TODO replace paths with :id/:entry/:datetime/:etc with form param calls. Therefore also replace duplicate check with form param check (through bool passed as addGet() param)
}