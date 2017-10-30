package me.salt.util.rest

import io.javalin.Context
import me.salt.entities.config.Configs
import me.salt.entities.config.entities.SaltConfig
import me.salt.entities.objects.getConfig

//@Controller
//@Path("/configs")
//@Produces("application/json")
//@Api(value = "/configs", description = "Does some stuff")
object ConfigController {
    //    @GET
//    @ApiOperation(value = "/configs", response = String::class)

    fun getConfigsByType(ctx: Context): () -> (Context) {
        when (ctx.param("type")) {
            "main" -> return {
                ctx.json(Configs.salt.MAIN_CONFIG.getConfig(SaltConfig::class.java)
                    ?: ctx.status(404)) }
            "language", "lang" -> return {
                ctx.json(Configs.salt.LANGUAGE_CONFIG.getConfig(SaltLanguageConfig::class.java)
                    ?: ctx.status(404)) }
            else -> return { ctx.status(404) }
        }
    }

//    fun getConfigsByType(ctx: Context): () -> (Context) = getConfigsByType(ctx.param())
//    fun getAllConfigs(ctx: Context): () -> (Context) = getConfigsByType(ctx.param())
//TODO configs here are, so far, for salt configs only. Other conf's require specifying an entityId

//    fun searchConfigsByCategory(ctx: Context): () -> (Context){}
//    fun searchConfigsByType(ctx: Context): () -> (Context){}
//    fun searchConfigsByCategoryAndType(ctx: Context): () -> (Context){}
}