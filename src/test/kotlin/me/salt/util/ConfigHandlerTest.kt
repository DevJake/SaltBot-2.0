/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.util

import com.winterbe.expekt.should
import me.salt.config.*
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