/*
 * Copyright (c) 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.Salt.Permissions;

/**
 * This class contains a list of all different permissions.
 * These are used for checking which permissions a user/textchannel/guild has,
 * and modifying bot behaviour accordingly.
 */
public enum Perm {
    //Add perm to bypass Cooldowns. Add per command.
    //Guild perms
    //User perms
    //Global perms

    //Configuration permissions. Required to make config changes, including permission systems.
    CONFIGURE_GLOBAL_PERMISSIONS, //For me only!!
    CONFIGURE_GUILD_PERMISSIONS, //TODO give to guild owners by default
    CONFIGURE_TEXTCHANNEL_PERMISSIONS,
    CONFIGURE_USER_PERMISSIONS, //Give to every user by default

    CONFIG_SET_CMDPREFIX, //Let a user/guild change the cmd prefix
    CONFIG_SET_EMBED_COLOUR, //Let a user/guild change the embed colour
    CONFIG_BYPASS_CMD_PREFIX, //Let a user bypass a guild's cmd prefix
    CONFIG_BYPASS_EMBED_COLOUR, //Let a user bypass a guild's embed colour

    //ALIASES
    CONFIG_ADD_ALIAS,
    CONFIG_REMOVE_ALIAS,

    CONFIG_ADD_COMMAND_ALIAS,
    CONFIG_REMOVE_COMMAND_ALIAS,

    CONFIG_ADD_UTILITY_ALIAS,
    CONFIG_REMOVE_UTILITY_ALIAS,

    //All permissions
    /**
     * Access to all available permissions
     */
    ALL_GLOBAL_PERMISSIONS,

    /**
     * Access to all Guild-specific permissions
     */
    ALL_GUILD_PERMISSIONS,

    /**
     * Access to all TextChannel-specific permissions
     */
    ALL_TEXTCHANNEL_PERMISSIONS,

    /**
     * Access to all User-specific permissions
     */
    ALL_USER_PERMISSIONS,

    /**
     * Access to all Cooldown permissions
     */
    ALL_COOLDOWN_PERMISSIONS,

    /**
     * Access to all Command-cooldown permissions
     */
    ALL_COOLDOWN_COMMAND_PERMISSIONS,

    /**
     * Access to all Utility-cooldown permissions
     */
    ALL_COOLDOWN_UTILITY_PERMISSIONS,

    //Cooldowns
    BYPASS_ALL_COMMAND_COOLDOWN,
    BYPASS_ALL_UTILITY_COOLDOWN,

    //Independent Command cooldown bypasses
    COMMAND_BYPASS_HELP_COOLDOWN,
    COMMAND_BYPASS_ISSUE_COOLDOWN,
    COMMAND_BYPASS_PING_COOLDOWN,
    COMMAND_BYPASS_RATES_COOLDOWN,
    COMMAND_BYPASS_REMINDER_COOLDOWN,
    COMMAND_BYPASS_SAY_COOLDOWN,
    COMMAND_BYPASS_STATISTICS_COOLDOWN,
    COMMAND_BYPASS_GUILDINFO_COOLDOWN,
    COMMAND_BYPASS_INFO_COOLDOWN,
    COMMAND_BYPASS_PROFILE_COOLDOWN,
    COMMAND_BYPASS_TEXTCHANNELINFO_COOLDOWN,
    COMMAND_BYPASS_UPTIME_COOLDOWN,
    COMMAND_BYPASS_SOURCE_COOLDOWN,
    COMMAND_BYPASS_CAT_COOLDOWN,

    //Independent Utility cooldown bypasses
    UTILITY_BYPASS_SPAMTRACKER_COOLDOWN,
    UTILITY_BYPASS_TUBEMAPPER_COOLDOWN,

    //Say Command
    COMMAND_SAY_USE,
    COMMAND_SAY_SET_TEXTCHANNEL,
    COMMAND_SAY_USE_MENTION,
    COMMAND_SAY_USE_MENTION_EVERYONE, //Mention @everyone
    COMMAND_SAY_USE_MENTION_HERE, //Mention @here
    COMMAND_SAY_USE_PRIVATE_MESSAGE,
    //TODO add perms per command

    //Profile Command
    COMMAND_PROFILE_USE,
    COMMAND_PROFILE_SET_USER,

    //Shutdown Command
    COMMAND_SHUTDOWN_USE,
    COMMAND_SHUTDOWN_SET_DELAY,
}
