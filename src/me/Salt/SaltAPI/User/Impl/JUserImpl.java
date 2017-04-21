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

package me.Salt.SaltAPI.User.Impl;

import me.Salt.Exception.DuplicateDataException;
import me.Salt.Permissions.Perm;
import me.Salt.SaltAPI.User.JUser;
import me.Salt.SaltAPI.Util.PrivilegeState;
import me.Salt.SaltAPI.Util.WarningBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 10/04/2017.
 */
public class JUserImpl implements JUser {
    private User user;
    private List<WarningBuilder.Warning> warnings = new ArrayList<>();
    private List<Perm> permissions = new ArrayList<>();
    private PrivilegeState privilegeState;
    private String userId;
    private LocalDateTime lastMessage;
    private LocalDateTime lastOnline;
    private Guild lastSpokenGuild;
    private TextChannel lastTextChannel;
    private String lastNickname;

    public JUserImpl(User user, List<WarningBuilder.Warning> warnings, List<Perm> permissions, PrivilegeState privilegeState, String userId, LocalDateTime lastMessage, LocalDateTime lastOnline, Guild lastSpokenGuild, TextChannel lastTextChannel, String lastNickname) {
        this.user = user;
        this.warnings = warnings;
        this.permissions = permissions;
        this.privilegeState = privilegeState;
        this.userId = userId;
        this.lastMessage = lastMessage;
        this.lastOnline = lastOnline;
        this.lastSpokenGuild = lastSpokenGuild;
        this.lastTextChannel = lastTextChannel;
        this.lastNickname = lastNickname;
    }

    @Override
    public JUser addWarning(WarningBuilder.Warning warning) {
        this.warnings.add(warning);
        return this;
    }

    @Override
    public JUser addPermission(Perm permission) throws DuplicateDataException {
        if (this.permissions.contains(permission))
            throw new DuplicateDataException("This user already has this permission!");
        else this.permissions.add(permission);
        return this;
    }

    @Override
    public JUser removePermission(Perm permission) {
        if (this.permissions.contains(permission)) this.permissions.remove(permission);
        return this;
    }

    @Override
    public JUser removeWarning(WarningBuilder.Warning warning) {
        if (this.warnings.contains(warning)) this.warnings.remove(warning);
        return this;
    }

    @Override
    public JUser setPrivilegeState(PrivilegeState privilegeState) {
        this.privilegeState = privilegeState;
        return this;
    }


    @Override
    public String toString() {
        return "JUserImpl{" +
                "user=" + user +
                ", warnings=" + warnings.toString() +
                ", permissions=" + permissions.toString() +
                ", privilegeState=" + privilegeState +
                ", userId='" + userId + '\'' +
                ", lastMessage=" + lastMessage +
                ", lastOnline=" + lastOnline +
                ", lastSpokenGuild=" + lastSpokenGuild +
                ", lastTextChannel=" + lastTextChannel +
                ", lastNickname='" + lastNickname + '\'' +
                '}';
    }

    @Override
    public JUser setLastMessage(LocalDateTime lastMessage) {
        this.lastMessage = lastMessage;
        return this;
    }

    @Override
    public JUser setLastOnline(LocalDateTime lastOnline) {
        this.lastOnline = lastOnline;
        return this;
    }

    @Override
    public JUser setLastSpokenGuild(Guild lastSpokenGuild) {
        this.lastSpokenGuild = lastSpokenGuild;
        return this;
    }

    @Override
    public JUser setLastTextChannel(TextChannel lastTextChannel) {
        this.lastTextChannel = lastTextChannel;
        return this;
    }

    @Override
    public JUser setLastNickname(String lastNickname) {
        this.lastNickname = lastNickname;
        return this;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public List<Perm> getPermissions() {
        return permissions;
    }

    @Override
    public LocalDateTime getLastMessage() {
        return lastMessage;
    }

    @Override
    public LocalDateTime getLastOnline() {
        return lastOnline;
    }

    @Override
    public Guild getLastSpokenGuild() {
        return lastSpokenGuild;
    }

    @Override
    public TextChannel getLastTextChannel() {
        return lastTextChannel;
    }

    @Override
    public String getLastNickname() {
        return lastNickname;
    }

    @Override
    public PrivilegeState getPrivilegeState() {
        return privilegeState;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public List<WarningBuilder.Warning> getWarnings() {
        return warnings;
    }
}
