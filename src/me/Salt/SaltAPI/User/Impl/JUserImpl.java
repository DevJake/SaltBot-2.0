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

import me.Salt.SaltAPI.User.JUser;
import me.Salt.SaltAPI.Util.PrivilegeState;
import me.Salt.SaltAPI.Util.WarningBuilder;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 10/04/2017.
 */
public class JUserImpl implements JUser {
    private User user;
    private List<WarningBuilder.Warning> warnings;
    private PrivilegeState privilegeState;

    public JUserImpl(User user, List<WarningBuilder.Warning> warnings, PrivilegeState privilegeState) {
        this.user = user;
        this.warnings = warnings;
        this.privilegeState = privilegeState;

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
