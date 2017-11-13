/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.entities.config.entities

import com.winterbe.expekt.should
import me.salt.entities.lang.LangCode
import me.salt.entities.objects.Admin
import me.salt.entities.objects.Module
import me.salt.util.SimpleRGBColour
import me.salt.util.exception.ExceptionHandler
import me.salt.util.exception.ExcessiveValueException
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.util.concurrent.TimeUnit

class SaltConfigBuilderTest : Spek({
    ExceptionHandler.isTesting = true
    context("default values") {
        val subject = SaltConfigBuilder("").build() as SaltConfig
        given("an instance of SaltConfig built by SaltConfigBuilder with no changes") {
            on("requesting defaultLangCode") {
                it("should return LangCode.en_GB") {
                    subject.defaultLangCode.should.equal(LangCode.EN_GB)
                }
            }

            on("requesting botSafeMode") {
                it("should return false") {
                    subject.botSafeMode.should.equal(false)
                }
            }

            on("requesting modules") {
                it("should return an empty list of Module type") {
                    subject.modules?.size.should.equal(0)
                }
            }

            on("requesting globalAdmins") {
                it("should return a list of set instances of Admin") {
                    subject.globalAdmins.should.equal(listOf(Admin("Salt", "tobesorted")))
                }
            }

            on("requesting respondToMentions") {
                it("should return true") {
                    subject.respondToMentions.should.equal(true)
                }
            }

            on("requesting globalPrefixes") {
                it("should return a list of set Strings") {
                    subject.globalPrefixes.should.equal(listOf("."))
                }
            }

            on("requesting defaultCooldownValue") {
                it("should return a value of 10") {
                    subject.defaultCooldownValue.should.equal(10)
                }
            }

            on("requesting defaultEmbedColour") {
                it("should return a Pink instance of SimpleRGBColour") {
                    subject.defaultEmbedColour.should.equal(SimpleRGBColour(255, 175, 175))
                }
            }
        }
    }
    var subject = SaltConfigBuilder("")
    beforeEachTest { subject = SaltConfigBuilder("") }
    context("custom values") {
        given("an instance of SaltConfigBuilder") {
            on("setting the bot token") {
                subject.setBotToken("mybottoken")
                it("should update the bot token to our input") {
                    (subject.build() as SaltConfig).botToken.should.equal("mybottoken")
                }
            }

            on("updating the botSafeMode value to true") {
                subject.setBotSafeMode(true)
                it("should update botSafeMode to true") {
                    (subject.build() as SaltConfig).botSafeMode.should.be.`true`
                }
            }

            on("updating the respondToMentions value to false") {
                subject.setRespondToMentions(false)
                it("should update respondToMentions to false") {
                    (subject.build() as SaltConfig).respondToMentions.should.be.`false`
                }
            }

            context("modules") {
                on("adding a single module") {
                    subject.addModules(Module("myname", "mydesc", true))
                    it("should add our Module to the list of modules") {
                        (subject.build() as SaltConfig).modules.should.contain(Module("myname", "mydesc", true))


                    }

                    it("should contain only one Module") {
                        (subject.build() as SaltConfig).modules?.size.should.equal(1)
                    }
                }

                on("adding multiple modules") {
                    subject.addModules(Module("myname", "mydesc", true), Module("myname2", "mydesc2", false))
                    it("should add our Modules to the list of modules") {
                        (subject.build() as SaltConfig).modules.should.contain(Module("myname", "mydesc", true))
                        (subject.build() as SaltConfig).modules.should.contain(Module("myname2", "mydesc2", false))
                    }

                    it("should contain two Modules") {
                        (subject.build() as SaltConfig).modules?.size.should.equal(2)
                    }
                }

                on("removing one existing module") {
                    subject.addModules(Module("myname", "mydesc", true))
                    it("should remove our specified module") {
                        subject.removeModules(Module("myname", "mydesc", true))
                        (subject.build() as SaltConfig).modules.should.not.contain(Module("myname", "mydesc", true))
                    }

                    it("should contain zero modules") {
                        (subject.build() as SaltConfig).modules?.size.should.equal(0)
                    }
                }

                on("removing multiple existing modules where both are present") {
                    subject.addModules(Module("myname", "mydesc", true), Module("myname2", "mydesc2", false))
                    it("should remove both of our specified modules") {
                        subject.removeModules(
                                Module("myname", "mydesc", true),
                                Module("myname2", "mydesc2", false))
                        (subject.build() as SaltConfig).modules.should.not.contain(Module("myname", "mydesc", true))
                        (subject.build() as SaltConfig).modules.should.not.contain(Module("myname2", "mydesc2", false))
                    }

                    it("should contain zero modules") {
                        (subject.build() as SaltConfig).modules?.size.should.equal(0)
                    }
                }

                on("removing multiple modules where only one is present") {
                    subject.addModules(Module("myname", "mydesc", true), Module("myname3", "mydesc2", false))
                    it("should remove only the existing specified modules") {
                        subject.removeModules(
                                Module("myname", "mydesc", true),
                                Module("myname2", "mydesc2", false))
                        (subject.build() as SaltConfig).modules.should.not.contain(Module("myname", "mydesc", true))
                        (subject.build() as SaltConfig).modules.should.contain(Module("myname3", "mydesc2", false))
                    }

                    it("should contain zero modules") {
                        (subject.build() as SaltConfig).modules?.size.should.equal(1)
                    }
                }
            }

            context("global admins") {
                on("adding a single global admin") {
                    subject.addGlobalAdmins(Admin("myadmin", "myadminid"))
                    it("should add our Admin to the list of global admins") {
                        (subject.build() as SaltConfig).globalAdmins.should.contain(Admin("myadmin", "myadminid"))
                    }

                    it("should contain only one global admin") {
                        (subject.build() as SaltConfig).globalAdmins?.size.should.equal(1)
                    }
                }

                on("adding multiple Admins") {
                    subject.addGlobalAdmins(Admin("myadmin", "myadminid"), Admin("myadmin2", "myadminid2"))
                    it("should add our Admins to the list of admins") {
                        (subject.build() as SaltConfig).globalAdmins.should.contain(Admin("myadmin", "myadminid"))
                        (subject.build() as SaltConfig).globalAdmins.should.contain(Admin("myadmin2", "myadminid2"))
                    }

                    it("should contain two Admins") {
                        (subject.build() as SaltConfig).globalAdmins?.size.should.equal(2)
                    }
                }

                on("removing one existing admin") {
                    subject.addGlobalAdmins(Admin("myadmin", "myadminid"))
                    it("should remove our specified Admin") {
                        subject.removeGlobalAdmins(Admin("myadmin", "myadminid"))
                        (subject.build() as SaltConfig).globalAdmins.should.not.contain(Admin("myadmin", "myadminid"))
                    }

                    it("should contain zero Admins") {
                        (subject.build() as SaltConfig).globalAdmins?.size.should.equal(0)
                    }
                }

                on("removing multiple existing Admins where both are present") {
                    subject.addGlobalAdmins(Admin("myadmin", "myadminid"), Admin("myadmin2", "myadminid2"))
                    it("should remove both of our specified Admins") {
                        subject.removeGlobalAdmins(
                                Admin("myadmin", "myadminid"),
                                Admin("myadmin2", "myadminid2"))
                        (subject.build() as SaltConfig).globalAdmins.should.not.contain(Admin("myadmin", "myadminid"))
                        (subject.build() as SaltConfig).globalAdmins.should.not.contain(Admin("myadmin2", "myadminid2"))
                    }

                    it("should contain zero admins") {
                        (subject.build() as SaltConfig).globalAdmins?.size.should.equal(0)
                    }
                }

                on("removing multiple admins where only one is present") {
                    subject.addGlobalAdmins(Admin("myadmin", "myadminid"), Admin("myadmin2", "myadminid2"))
                    it("should remove only the existing specified admins") {
                        subject.removeGlobalAdmins(
                                Admin("myadmin", "myadminid"), Admin("myadmin3", "myadminid2"))
                        (subject.build() as SaltConfig).globalAdmins.should.not.contain(Admin("myadmin", "myadminid"))
                        (subject.build() as SaltConfig).globalAdmins.should.contain(Admin("myadmin2", "myadminid2"))
                    }

                    it("should contain zero admins") {
                        (subject.build() as SaltConfig).globalAdmins?.size.should.equal(1)
                    }
                }
            }

            context("global prefixes") {
                on("adding a single global prefix") {
                    subject.addGlobalPrefixes(".")
                    it("should add our prefix to the list of global prefixes") {
                        (subject.build() as SaltConfig).globalPrefixes.should.contain(".")
                    }

                    it("should contain only one global prefix") {
                        (subject.build() as SaltConfig).globalPrefixes?.size.should.equal(1)
                    }
                }

                on("adding multiple prefixes") {
                    subject.addGlobalPrefixes(".", ",")
                    it("should add our prefixes to the list of prefixes") {
                        (subject.build() as SaltConfig).globalPrefixes.should.contain(".")
                        (subject.build() as SaltConfig).globalPrefixes.should.contain(",")
                    }

                    it("should contain two prefixes") {
                        (subject.build() as SaltConfig).globalPrefixes?.size.should.equal(2)
                    }
                }

                on("removing one existing prefix") {
                    subject.addGlobalPrefixes(".")
                    it("should remove our specified prefix") {
                        subject.removeGlobalPrefixes(".")
                        (subject.build() as SaltConfig).globalPrefixes.should.not.contain(".")
                    }

                    it("should contain zero prefixes") {
                        (subject.build() as SaltConfig).globalPrefixes?.size.should.equal(0)
                    }
                }

                on("removing multiple existing prefixes where both are present") {
                    subject.addGlobalPrefixes(".", ",")
                    it("should remove both of our specified prefixes") {
                        subject.removeGlobalPrefixes(".", ",")
                        (subject.build() as SaltConfig).globalPrefixes.should.not.contain(".")
                        (subject.build() as SaltConfig).globalPrefixes.should.not.contain(",")
                    }

                    it("should contain zero prefixes") {
                        (subject.build() as SaltConfig).globalPrefixes?.size.should.equal(0)
                    }
                }

                on("removing multiple prefixes where only one is present") {
                    subject.addGlobalPrefixes(".", ">")
                    it("should remove only the existing specified prefixes") {
                        subject.removeGlobalPrefixes(".", ",")
                        (subject.build() as SaltConfig).globalPrefixes.should.not.contain(".")
                        (subject.build() as SaltConfig).globalPrefixes.should.contain(">")
                    }

                    it("should contain zero prefixes") {
                        (subject.build() as SaltConfig).globalPrefixes?.size.should.equal(1)
                    }
                }
            }

            on("setting the default cooldown value to 200 SECONDS") {
                subject.setDefaultCooldownValue(TimeUnit.SECONDS, 200)
                it("should update defaultCooldownValue to 200") {
                    (subject.build() as SaltConfig).defaultCooldownValue.should.equal(200)
                }
            }

            on("setting the default cooldown value to 5 MINUTES") {
                subject.setDefaultCooldownValue(TimeUnit.MINUTES, 5)
                it("should update defaultCooldownValue to 1800") {
                    (subject.build() as SaltConfig).defaultCooldownValue.should.equal(300)
                }
            }

            on("attempting to set the default cooldown value to below 0") {
                it("should throw an ExcessiveValueException") {
                    subject.setDefaultCooldownValue(TimeUnit.MINUTES, -1)
                    ExceptionHandler.latestException.should.be.instanceof(ExcessiveValueException::class.java)
                }
            }

            on("attempting to set the default cooldown value to above 24 hours (86400 seconds)") {
                it("should throw an ExcessiveValueException") {
                    subject.setDefaultCooldownValue(TimeUnit.SECONDS, 86401)
                    ExceptionHandler.latestException.should.be.instanceof(ExcessiveValueException::class.java)
                }
            }

            on("setting the default embed colour") {
                subject.setDefaultEmbedColour(SimpleRGBColour(255, 0, 77))
                it("should update the value of defaultEmbedColour") {
                    (subject.build() as SaltConfig).defaultEmbedColour.should.equal(SimpleRGBColour(255, 0, 77))
                }
            }

            on("setting the default LangCode") {
                subject.setDefaultLangCode(LangCode.EN_GB)
                it("should update the value of defaultLangCode to LangCode.en_GB") {
                    (subject.build() as SaltConfig).defaultLangCode.should.equal(LangCode.EN_GB)
                }
            }
        }
    }
})
