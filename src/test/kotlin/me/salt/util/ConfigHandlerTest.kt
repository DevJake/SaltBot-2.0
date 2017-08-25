package me.salt.util

import com.winterbe.expekt.should
import me.salt.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


class ConfigHandlerTest : Spek({
    val subject = ConfigHandler
    given("a Config Handler") {
        on("requesting the SaltConfigHandler instance") {
            it("should return an instance of SaltConfigHandler") {
                subject.saltConfigHandler.should.be.an.instanceof(SaltConfigHandler::class.java)
            }
        }

        on("requesting the GuildConfigHandler instance") {
            it("should return an instance of GuildConfigHandler") {
                subject.guildConfigHandler.should.be.an.instanceof(GuildConfigHandler::class.java)
            }
        }

        on("requesting the TextChannelConfigHandler instance") {
            it("should return an instance of TextChannelConfigHandler") {
                subject.textChannelConfigHandler.should.be.an.instanceof(TextChannelConfigHandler::class.java)
            }
        }

        on("requesting the UserConfigHandler instance") {
            it("should return an instance of UserConfigHandler") {
                subject.userConfigHandler.should.be.an.instanceof(UserConfigHandler::class.java)
            }
        }
    }
})