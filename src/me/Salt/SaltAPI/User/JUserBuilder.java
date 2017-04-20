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
    private PrivilegeState privilegeState;
    private String userId;
    private LocalDateTime lastMessage;
    private LocalDateTime lastOnline;
    private Guild lastSpokenGuild;
    private TextChannel lastTextChannel;
    private String lastNickname;

    public JUserBuilder(JUser user) {
        this.user = user.getUser();
        this.warnings = user.getWarnings();
        this.privilegeState = user.getPrivilegeState();
        this.userId = user.getUserId();
        this.lastMessage = user.getLastMessage();
        this.lastOnline = user.getLastOnline();
        this.lastSpokenGuild = user.getLastSpokenGuild();
        this.lastTextChannel = user.getLastTextChannel();
        this.lastNickname = user.getLastNickname();
    }

    public JUserBuilder() {
    }

    public JUserBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public JUserBuilder addWarning(WarningBuilder.Warning warning) {
        this.warnings.add(warning);
        return this;
    }

    public JUserBuilder setPrivilegeState(PrivilegeState privilegeState) {
        this.privilegeState = privilegeState;
        return this;
    }

    public JUserBuilder setUserId(String userId) {
        this.userId = userId;
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
        if (userId == null) if (user != null) userId = user.getId();
        return new JUserImpl(user, warnings, privilegeState, userId, lastMessage, lastOnline, lastSpokenGuild, lastTextChannel, lastNickname);
        //TODO do checks
    }
}
