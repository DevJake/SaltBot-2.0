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

package me.salt.objects

import me.salt.permissions.Authority
import me.salt.permissions.Node

enum class Action(val node: Node, val description: String? = null) {
    GUILD_PERMISSION_CREATE(Authority.Guild(Interaction.WRITE).getAuthorityNodes()[0]),
    GUILD_PERMISSION_VIEW(Authority.Guild(Interaction.READ).getAuthorityNodes()[0]),
    GUILD_PERMISSION_REMOVE(Authority.Guild(Interaction.REMOVE).getAuthorityNodes()[0]),

    CHANNEL_PERMISSION_CREATE(Authority.Channel(Interaction.WRITE).getAuthorityNodes()[0]),
    CHANNEL_PERMISSION_VIEW(Authority.Channel(Interaction.READ).getAuthorityNodes()[0]),
    CHANNEL_PERMISSION_REMOVE(Authority.Channel(Interaction.REMOVE).getAuthorityNodes()[0]),

    BOT_PERMISSION_CREATE(Authority.Bot(Interaction.WRITE).getAuthorityNodes()[0]),
    BOT_PERMISSION_VIEW(Authority.Bot(Interaction.READ).getAuthorityNodes()[0]),
    BOT_PERMISSION_REMOVE(Authority.Bot(Interaction.REMOVE).getAuthorityNodes()[0]);

    private constructor(node: String, description: String? = null): this(Node(node), description)
}

enum class Interaction {
    READ,
    WRITE,
    REMOVE,
    ALL;
}

enum class PointMethod {
    STANDARD_MESSAGE,
    MESSAGE_WITH_URL,
    MESSAGE_WITH_INVITE,
    MESSAGE_WITH_EMOJI,
    URL,
    INVITE,
    EMOJI,
    IMAGE,
    GIF,
    VIDEO;
}

enum class TrackableStats {
    MESSAGE_SEND,
    MESSAGE_EDIT,
    MESSAGE_DELETE,
    GUILD_JOIN,
    GUILD_LEAVE,
    REACTION_ADD,
    REACTION_REMOVE,
    TYPING_BEGIN,
    TYPING_END,
    ACTIVITY_STATUS_ONLINE,
    ACTIVITY_STATUS_OFFLINE,
    ACTIVITY_STATUS_DO_NOT_DISTURB,
    VOICECHANNEL_JOIN,
    VOICECHANNEL_LEAVE,
    PLAY_STATUS_UPDATE;
}

enum class RoleRequirementState {
    /**
     * The individual -must- have this role
     */
    REQUIRED,
    /**
     * The individual -must not- have this role
     */
    UNREQUIRED,
    /**
     * The individual is unaffected, regardless of having this role or not
     */
    OPTIONAL,
    /**
     * The individual must have this role, if they have no other role with a state of [REQUIRED] or [REQUIRED_OPTIONAL]
     */
    REQUIRED_OPTIONAL,
    /**
     * The individual -must not- have this role, unless they also lack any other roles marked as [UNREQUIRED] or [UNREQUIRED_OPTIONAL]; of a range of roles, they must lack at least one
     */
    UNREQUIRED_OPTIONAL;
}

enum class LevelRange {
    GLOBAL,
    GUILD,
    TEXTCHANNEL;
}

enum class Colour(val red: Int, val green: Int, val blue: Int) {
    RED(255, 0, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    YELLOW(0, 255, 255);
}

enum class Entity {
    GLOBAL,
    GUILD,
    TEXTCHANNEL,
    USER;
}

enum class Modules {
    //TODO a list of modules, complete with their matching Module instance
}

