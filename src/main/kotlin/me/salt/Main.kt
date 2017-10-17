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

package me.salt

import me.salt.config.Configs
import me.salt.config.entities.*
import me.salt.config.initConfigs
import me.salt.lang.LangCode
import me.salt.lang.initLangs
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
            Configs.SALT.LOG_CONFIG.writeConfig(SaltLogConfig(true, true, false, true, mutableListOf()))
            Configs.SALT.MAIN_CONFIG.overwriteConfig(SaltConfigBuilder("").build())
//            logEasy("Simple, easy logging")
//            logWarn("A warning log")
//            LogUtils.DEBUG("MAINTHREAD").log("A testing debug log entry")
            jda = JDABuilder(AccountType.BOT).setToken(Configs.SALT.MAIN_CONFIG.getConfig(SaltConfig::class.java)?.botToken).buildAsync()
            //TODO accept runtime params, such as regen-default-configs to regenerate default config files
//            Configs.SALT.PERMISSIONS_MAP.overwriteConfig(
//                    PermissionMapBuilder().addGroups(
//                            GroupPermissionBuilder("PermGroup1")
//                                    .addPermissions(
//                                            NodeBuilder("my.first.node", Authority.Guild(Interaction.WRITE)).build(),
//                                            NodeBuilder("my.second.node", Node.NodeType.PERMISSION, true).build(),
//                                            NodeBuilder("my.*", Node.NodeType.PERMISSION, true).build())
//                                    .addEnforcements(
//                                            NodeBuilder("my.first.enforcement.node", Node.NodeType.ENFORCEMENT).build(),
//                                            NodeBuilder("my.second.enforcement.node", Node.NodeType.ENFORCEMENT, true).build(),
//                                            NodeBuilder("my.enforcement.*", Node.NodeType.ENFORCEMENT, true).build())
//                                    .build(),
//                            GroupPermissionBuilder("Group2")
//                                    .addPermissions(
//                                            NodeBuilder("second.perm.node").build(),
//                                            NodeBuilder("third.perm.node").build(),
//                                            NodeBuilder("fourth.perm.node", true).build())
//                                    .addGroups("PermGroup1")
//                                    .build()
//                    ).addUsers(
//                            UserPermissionBuilder("112633500447838208")
//                                    .addPermissions(
//                                            NodeBuilder("user.perm.node.one", Node.NodeType.PERMISSION).build(),
//                                            NodeBuilder("user.perm.node.two", Node.NodeType.PERMISSION, false).build(),
//                                            NodeBuilder("user.perm.node.*", Node.NodeType.PERMISSION, true).build())
//                                    .addEnforcements(
//                                            NodeBuilder("user.enforcement.node.one", Node.NodeType.ENFORCEMENT, Authority.None()).build(),
//                                            NodeBuilder("user.enforcement.node.two", Node.NodeType.ENFORCEMENT, false,
//                                                    Authority.Bot(Interaction.READ)
//                                                    .addInteractions(Interaction.REMOVE)
//                                                    .addLevels(Authority.Level.GUILD)).build(),
//                                            NodeBuilder("user.enforcement.node.*", Node.NodeType.ENFORCEMENT, true, Authority.Guild(Interaction.WRITE)).build()
//                                    )
//                                    .addGroups("Group2")
//                                    .build()
//                    ).build())

            println(Configs.SALT.PERMISSIONS_MAP.getConfig(PermissionMap::class.java).toString())

            println("hasperm=" +
                    "${jda.getUserById("112633500447838208").hasBotPermissions("my.first.node")}")
        }
    }
}
