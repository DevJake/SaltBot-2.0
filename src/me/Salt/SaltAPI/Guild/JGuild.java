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

package me.Salt.SaltAPI.Guild;

import me.Salt.Exception.DuplicateDataException;
import me.Salt.Permissions.Perm;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Salt001 on 10/04/2017.
 */
public interface JGuild {
    public Guild getGuild();

    public long getGuildId();

    public JGuild addPermission(Perm permission) throws DuplicateDataException;

    public JGuild removePermission(Perm permission);

    public List<Perm> getPermissions();

    public String getLastMessage();

    public LocalDateTime getLastActivity();

    public TextChannel getLastTextChannelMessaged();
}
