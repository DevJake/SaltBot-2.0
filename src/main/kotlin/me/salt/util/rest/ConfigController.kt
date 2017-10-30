package me.salt.util.rest

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import me.salt.entities.config.Configs
import me.salt.entities.config.entities.Config
import me.salt.entities.config.entities.SaltConfig
import me.salt.entities.objects.getConfig
import org.springframework.stereotype.Controller
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Controller
@Path("/configs")
@Produces("application/json")
@Api(value = "/configs", description = "Does some stuff")
class ConfigController {
    @GET
    @ApiOperation(value = "/configs", response = String::class)
    fun getConfig(configName: String): Config? = when (configName.toLowerCase()) {
        "saltconfig" -> Configs.salt.MAIN_CONFIG.getConfig(SaltConfig::class.java)
        else -> null
    }
}