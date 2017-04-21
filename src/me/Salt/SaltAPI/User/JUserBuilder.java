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

package me.Salt.SaltAPI.User;

import com.sun.istack.internal.Nullable;
import me.Salt.Exception.DuplicateDataException;
import me.Salt.Exception.MissingDataException;
import me.Salt.Main;
import me.Salt.Permissions.Perm;
import me.Salt.SaltAPI.User.Impl.JUserImpl;
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
 * Authored by Salt on 20/04/2017.
 */
public class JUserBuilder {
    private User user;
    private List<WarningBuilder.Warning> warnings = new ArrayList<>();
    private List<Perm> permissions = new ArrayList<>();
    private PrivilegeState privilegeState;
    private long userId;
    private LocalDateTime lastMessage;
    private LocalDateTime lastOnline;
    private Guild lastSpokenGuild;
    private TextChannel lastTextChannel;
    private String lastNickname;

    public JUserBuilder(@Nullable JUser user) {
        if (user == null) return;
        this.user = user.getUser();
        this.warnings = user.getWarnings();
        this.permissions = user.getPermissions();
        this.privilegeState = user.getPrivilegeState();
        this.userId = user.getUserId();
        this.lastMessage = user.getLastMessage();
        this.lastOnline = user.getLastOnline();
        this.lastSpokenGuild = user.getLastSpokenGuild();
        this.lastTextChannel = user.getLastTextChannel();
        this.lastNickname = user.getLastNickname();
        //TODO improve system of adding users, so that a JUser doesn't need to be specified. JUsers need to be able to be updated, without specifying a JUser for a constructor. Perhaps add setter methods in JUserImpl.
    }

    public JUserBuilder(User user) {
        if (user == null) return;

        try {
            if (Main.salt.getJUserById(user.getId()) != null) {
                JUser jUser = Main.salt.getJUserById(user.getId());

                this.user = jUser.getUser();
                this.warnings = jUser.getWarnings();
                this.permissions = jUser.getPermissions();
                this.privilegeState = jUser.getPrivilegeState();
                this.userId = jUser.getUserId();
                this.lastMessage = jUser.getLastMessage();
                this.lastOnline = jUser.getLastOnline();
                this.lastSpokenGuild = jUser.getLastSpokenGuild();
                this.lastTextChannel = jUser.getLastTextChannel();
                this.lastNickname = jUser.getLastNickname();
            }
        } catch (MissingDataException e) {
            e.printStackTrace();
        }

        this.user = user;
        this.userId = user.getIdLong();
    }

    public JUserBuilder() {
    }

    public JUserBuilder setUser(User user) {
        this.user = user;
        this.userId = user.getIdLong();
        return this;
    }

    public JUserBuilder addPermission(Perm permission) throws DuplicateDataException {
        if (!this.permissions.contains(permission)) {
            this.permissions.add(permission);
            return this;
        } else throw new DuplicateDataException("This user already has this permission!");
    }

    public JUserBuilder addWarning(WarningBuilder.Warning warning) {
        this.warnings.add(warning);
        return this;
    }

    public JUserBuilder setPrivilegeState(PrivilegeState privilegeState) {
        this.privilegeState = privilegeState;
        return this;
    }

    public JUserBuilder setLastMessage(LocalDateTime lastMessage) {
        this.lastMessage = lastMessage;
        return this;
    }

    public JUserBuilder setLastOnline(LocalDateTime lastOnline) {
        this.lastOnline = lastOnline;
        return this;
    }

    public JUserBuilder setLastSpokenGuild(Guild lastSpokenGuild) {
        this.lastSpokenGuild = lastSpokenGuild;
        return this;
    }

    public JUserBuilder setLastTextChannel(TextChannel lastTextChannel) {
        this.lastTextChannel = lastTextChannel;
        return this;
    }

    public JUserBuilder setLastNickname(String lastNickname) {
        this.lastNickname = lastNickname;
        return this;
    }

    public JUser build() {
        return new JUserImpl(user, warnings, permissions, privilegeState, userId, lastMessage, lastOnline, lastSpokenGuild, lastTextChannel, lastNickname);
        //TODO do checks
    }
}
