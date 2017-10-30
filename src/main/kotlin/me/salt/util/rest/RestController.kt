package me.salt.util.rest

import io.javalin.Context
import io.javalin.Javalin
import me.salt.entities.permissions.UserPermission
import me.salt.util.logging.logDebug
import me.salt.util.rest.RestController.addGet
import java.time.OffsetDateTime

object RestController {
    private val javalin = Javalin.create().port(7000)
    private val tokens = mutableListOf<AccessToken>()

    fun genToken(oldToken: AccessToken): String {
        TODO()
    }

    fun tokenIsValid(token: String) { }
    fun tokenIsValid(token: AccessToken) = tokenIsValid(token.token)

    fun addGet(path: String, call: (Context)->()->(Context)) {
        javalin.get(path, {call.invoke(it).invoke(); callLog(it, "get")})
        regLog(path, "get")
    }

    fun addPost(path: String, call: (Context)->()->(Context)) {
        javalin.post(path, {call.invoke(it).invoke(); callLog(it, "post")})
        regLog(path, "post")
    }

    fun addDelete(path: String, call: (Context)->()->(Context)) {
        javalin.delete(path, {call.invoke(it).invoke(); callLog(it, "delete")})
        regLog(path, "delete")
    }

    fun addPut(path: String, call: (Context)->()->(Context)) {
        javalin.put(path, {call.invoke(it).invoke(); callLog(it, "put")})
        regLog(path, "put")
    }

    fun addPatch(path: String, call: (Context)->()->(Context)) {
        javalin.patch(path, {call.invoke(it).invoke(); callLog(it, "patch")})
        regLog(path, "patch")
    }

    fun addTrace(path: String, call: (Context)->()->(Context)) {
        javalin.trace(path, {call.invoke(it).invoke(); callLog(it, "trace")})
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



fun initRest(){
    addGet("/configs/salt/:type", { ConfigController.getConfigsByType(it) })
    //TODO /maps/salt/...
}