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
package me.Salt.SaltAPI.Guild.Impl;

import com.google.gson.annotations.Expose;
import me.Salt.Exception.Generic.DuplicateDataException;
import me.Salt.Permissions.Perm;
import me.Salt.SaltAPI.Guild.JGuild;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A container class for guilds.
 */
public class JGuildImpl implements JGuild {
    /**
     * The guild which this class references to.
     */
    private Guild guild;
    /**
     * The ID of the guild. Although storing the ID may seem pointless (due to storing the guild itself), it's stored
     * for the purpose of allowing the gathering of guilds after restarts, where the guild ID is stored in plaintext.
     */
    @Expose
    private long guildId;
    /**
     * The permissions registered to this guild
     */
    @Expose
    private List<Perm> permissions = new ArrayList<>();
    /**
     * The last message received from this guild.
     */
    @Expose
    private String lastMessage;
    /**
     * The last activity seen in this guild. Unlike the {@link JGuildImpl#lastMessage}, the activity is updated to
     * reflect when users come online, send a message, join a voicechannel, or begin typing.
     */
    @Expose
    private LocalDateTime lastActivity;
    /**
     * The last textchannel of this guild which received a message.
     */
    @Expose
    private TextChannel lastTextChannelMessaged;
    
    public JGuildImpl(Guild guild, long guildId, List<Perm> permissions, String lastMessage, LocalDateTime lastActivity,
                      TextChannel lastTextChannelMessaged) {
        this.guild = guild;
        this.guildId = guildId;
        this.permissions = permissions;
        this.lastMessage = lastMessage;
        this.lastActivity = lastActivity;
        this.lastTextChannelMessaged = lastTextChannelMessaged;
    }
    
    @Override
    public Guild getGuild() {
        return guild;
    }
    
    @Override
    public long getGuildId() {
        return guildId;
    }
    
    @Override
    public JGuild addPermission(Perm permission) throws DuplicateDataException {
        if (this.permissions.contains(permission))
            throw new DuplicateDataException("This guild already has this permission!");
        else this.permissions.add(permission);
        return this;
    }
    
    @Override
    public JGuild removePermission(Perm permission) {
        if (this.permissions.contains(permission)) this.permissions.remove(permission);
        return this;
    }
    
    @Override
    public List<Perm> getPermissions() {
        return permissions;
    }
    
    @Override
    public String getLastMessage() {
        return lastMessage;
    }
    
    @Override
    public LocalDateTime getLastActivity() {
        return lastActivity;
    }
    
    @Override
    public TextChannel getLastTextChannelMessaged() {
        return lastTextChannelMessaged;
    }
}
