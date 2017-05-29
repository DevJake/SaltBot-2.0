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

import me.Salt.Exception.Generic.DuplicateDataException;
import me.Salt.Permissions.Perm;
import me.Salt.Permissions.Permission;
import me.Salt.SaltAPI.Util.PrivilegeState;
import me.Salt.SaltAPI.Util.WarningBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 10/04/2017.
 */
public interface JUser {
    public User getUser();
    
    public List<WarningBuilder.Warning> getWarnings();
    
    public List<Permission> getPermissions();
    
    public PrivilegeState getPrivilegeState();
    
    public JUser setPrivilegeState(PrivilegeState privilegeState);
    
    public long getUserId();
    
    public LocalDateTime getLastMessage();
    
    public JUser setLastMessage(LocalDateTime lastMessage);
    
    public LocalDateTime getLastOnline();
    
    public JUser setLastOnline(LocalDateTime lastOnline);
    
    public Guild getLastSpokenGuild();
    
    public JUser setLastSpokenGuild(Guild lastSpokenGuild);
    
    public TextChannel getLastTextChannel();
    
    public JUser setLastTextChannel(TextChannel lastTextChannel);
    
    public String getLastNickname();
    
    public JUser setLastNickname(String lastNickname);
    
    public JUser addWarning(WarningBuilder.Warning warning);
    
    public JUser removeWarning(WarningBuilder.Warning warning);
    
    public JUser addPermission(Permission permission) throws DuplicateDataException;
    
    public JUser removePermission(Perm permission);
    
    public boolean hasPermission(Perm permission);
    
    public boolean hasPermission(Perm permission, Permission.Range range);
}
