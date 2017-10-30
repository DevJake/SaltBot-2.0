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


    fun getConfigByName(ctx: Context): () -> (Context) {
        when (ctx.param("name")) {
            "saltconfig" -> return { ctx.json(Configs.salt.MAIN_CONFIG.getConfig(SaltConfig::class.java) ?: ctx.status(404)) }
            else -> return { ctx.status(404) }
        }
    }
}
