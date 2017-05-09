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

package main.java.me.Salt.SaltAPI.Guild;

import main.java.me.Salt.Exception.Generic.DuplicateDataException;
import main.java.me.Salt.Permissions.Perm;
import main.java.me.Salt.SaltAPI.Guild.Impl.JGuildImpl;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JGuildBuilder {
    private Guild guild;
    private long guildId;
    private List<Perm> permissions = new ArrayList<>();
    private String lastMessage;
    private LocalDateTime lastActivity;
    private TextChannel lastTextChannelMessaged;

    public JGuildBuilder setGuild(Guild guild) {
        this.guild = guild;
        this.guildId = guild.getIdLong();
        return this;
    }

    public JGuildBuilder addPermission(Perm permission) throws DuplicateDataException {
        if (!this.permissions.contains(permission)) this.permissions.add(permission);
        else throw new DuplicateDataException("This guild already has this permission!");
        return this;
    }

    public JGuildBuilder setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
        return this;
    }

    public JGuildBuilder setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
        return this;
    }

    public JGuildBuilder setLastTextChannelMessaged(TextChannel lastTextChannelMessaged) {
        this.lastTextChannelMessaged = lastTextChannelMessaged;
        return this;
    }

    public JGuild build() {
        return new JGuildImpl(guild, guildId, permissions, lastMessage, lastActivity, lastTextChannelMessaged);
    }
}