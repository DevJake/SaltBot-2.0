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
import me.salt.util.logging.logDebug
import me.salt.util.rest.RestController.addDelete
import me.salt.util.rest.RestController.addGet
import me.salt.util.rest.RestController.addPatch
import me.salt.util.rest.RestController.addPost
import java.time.OffsetDateTime

object RestController {
    private val javalin = Javalin.create().port(7000)
    private val tokens = mutableListOf<AccessToken>()

    fun genToken(oldToken: AccessToken): String {
        TODO()
    }

    fun tokenIsValid(token: String) { }
    fun tokenIsValid(token: AccessToken) = tokenIsValid(token.token)

    fun addGet(path: String, call: (Context) -> () -> (Context)) {
        javalin.get(path, { call.invoke(it).invoke(); callLog(it, "get") })
        regLog(path, "get")
    }

    fun addPost(path: String, call: (Context) -> () -> (Context)) {
        javalin.post(path, { call.invoke(it).invoke(); callLog(it, "post") })
        regLog(path, "post")
    }

    fun addDelete(path: String, call: (Context) -> () -> (Context)) {
        javalin.delete(path, { call.invoke(it).invoke(); callLog(it, "delete") })
        regLog(path, "delete")
    }

    fun addPut(path: String, call: (Context) -> () -> (Context)) {
        javalin.put(path, { call.invoke(it).invoke(); callLog(it, "put") })
        regLog(path, "put")
    }

    fun addPatch(path: String, call: (Context) -> () -> (Context)) {
        javalin.patch(path, { call.invoke(it).invoke(); callLog(it, "patch") })
        regLog(path, "patch")
    }

    fun addTrace(path: String, call: (Context) -> () -> (Context)) {
        javalin.trace(path, { call.invoke(it).invoke(); callLog(it, "trace") })
        regLog(path, "trace")
    }

    private fun regLog(path: String, type: String) = logDebug("Registered a new ${type.toUpperCase()} endpoint at path $path")
    private fun callLog(ctx: Context, type: String) = logDebug("Received ${type.toUpperCase()} request at endpoint ${ctx.path()} IP: ${ctx.ip()}")
    //TODO replace with call to ctx to obtain specific type, rather than relying on it being passed in as a parameter

    fun start() { javalin.start() }

    fun stop() { javalin.stop() }

}

data class AccessToken(
        val token: String,
        val active: Boolean,
        val tiedUserId: String,
        val genDateTime: OffsetDateTime,
        val accessPerms: List<UserPermission>,
        val hourlyRequestCap: Int
)

fun initRest() {
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

    addDelete("/configs/salt/:type", { ConfigController.deleteSaltConfigByType(it) })
    addDelete("/maps/salt/:type", { ConfigController.deleteSaltMapByType(it) })

    addDelete("/configs/guild/:id/:type", { ConfigController.deleteGuildConfigByType(it) })
    addDelete("/maps/guild/:id/:type", { ConfigController.deleteGuildMapByType(it) })

    addDelete("/configs/channel/:id/:type", { ConfigController.deleteChannelConfigByType(it) })
    addDelete("/maps/channel/:id/:type", { ConfigController.deleteChannelMapByType(it) })

    addDelete("/configs/user/:id/:type", { ConfigController.deleteUserConfigByType(it) })
    addDelete("/maps/user/:id/:type", { ConfigController.deleteUserMapByType(it) })

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

    //TODO /maps/salt/...
}