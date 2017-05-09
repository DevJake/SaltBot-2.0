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

package main.java.me.Salt.SaltAPI.Guild.Impl;

import com.google.gson.annotations.Expose;
import main.java.me.Salt.Exception.Generic.DuplicateDataException;
import main.java.me.Salt.Permissions.Perm;
import main.java.me.Salt.SaltAPI.Guild.JGuild;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salt001 on 10/04/2017.
 */
public class JGuildImpl implements JGuild {
    private Guild guild;
    @Expose
    private long guildId;
    @Expose
    private List<Perm> permissions = new ArrayList<>();
    @Expose
    private String lastMessage;
    @Expose
    private LocalDateTime lastActivity;
    @Expose
    private TextChannel lastTextChannelMessaged;

    public JGuildImpl(Guild guild, long guildId, List<Perm> permissions, String lastMessage, LocalDateTime lastActivity, TextChannel lastTextChannelMessaged) {
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
