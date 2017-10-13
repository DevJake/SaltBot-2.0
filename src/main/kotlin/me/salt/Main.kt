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

package me.salt

import me.salt.config.Configs
import me.salt.config.entities.*
import me.salt.config.initConfigs
import me.salt.lang.LangCode
import me.salt.lang.initLangs
import me.salt.logging.LogUtils
import me.salt.logging.logEasy
import me.salt.logging.logInfo
import me.salt.logging.logWarn
import me.salt.objects.*
import me.salt.permissions.*
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder

class Main {
    companion object {
        lateinit var jda: JDA
            private set

        @JvmStatic
        fun main(args: Array<String>) {
            initConfigs() //Calls init method for configs
            initLangs()
            println(LangCode.en_GB.getLang())
//            Configs.SALT.MAIN_CONFIG.overwriteConfig(SaltConfigBuilder("mytoken").build())
            Configs.SALT.LOG_CONFIG.writeConfig(SaltLogConfig(true, true, false, true, mutableListOf()))
            logEasy("Simple, easy logging")
            logWarn("A warning log")
            LogUtils.DEBUG("MAINTHREAD").log("A testing debug log entry")
            jda = JDABuilder(AccountType.BOT).setToken(Configs.SALT.MAIN_CONFIG.getConfig(SaltConfig::class.java)?.botToken).buildAsync()
            //TODO accept runtime params, such as regen-default-configs to regenerate default config files
            Configs.SALT.PERMISSIONS_MAP.overwriteConfig(
                    PermissionMapBuilder().addGroups(
                            GroupPermission("PermGroup1",
                                    listOf(
                                            Node("my.first.node", Authority.Guild(Interaction.WRITE)),
                                            Node("my.second.node", type = Node.NodeType.PERMISSION, negate = true),
                                            Node("my.*", type = Node.NodeType.PERMISSION, negate = true)),
                                    listOf(
                                            Node("my.first.enforcement.node", type = Node.NodeType.ENFORCEMENT),
                                            Node("my.second.enforcement.node", type = Node.NodeType.ENFORCEMENT, negate = true),
                                            Node("my.enforcement.*", type = Node.NodeType.ENFORCEMENT, negate = true))
                                    , null, false, null),
                            GroupPermission("Group2",
                                    listOf(
                                            Node("second.perm.node"),
                                            Node("third.perm.node"),
                                            Node("fourth.perm.node", negate = true)
                                    ), null, null, false, null)
                    ).addUsers(
                            UserPermission("112633500447838208", listOf("Group2"),
                                    listOf(
                                            Node("user.perm.node.one", type = Node.NodeType.PERMISSION),
                                            Node("user.perm.node.two", type = Node.NodeType.PERMISSION, negate = false),
                                            Node("user.perm.node.*", type = Node.NodeType.PERMISSION, negate = true)),
                                    listOf(
                                            Node("user.enforcement.node.one", type = Node.NodeType.ENFORCEMENT, authority = Authority.None()),
                                            Node("user.enforcement.node.two", type = Node.NodeType.ENFORCEMENT, negate = false, authority = Authority.Bot(Interaction.READ)
                                                    .addInteractions(Interaction.REMOVE)
                                                    .addLevels(Authority.Level.GUILD)),
                                            Node("user.enforcement.node.*", type = Node.NodeType.ENFORCEMENT, negate = true, authority = Authority.Guild(Interaction.WRITE)))
                            )
                    ).build()
            )

            println(Configs.SALT.PERMISSIONS_MAP.getConfig(PermissionMap::class.java).toString())

            println("hasperm=" +
                    "${jda.getUserById("112633500447838208").hasBotPermissions(nodes = "second.perm.node")}")
        }
    }
}
