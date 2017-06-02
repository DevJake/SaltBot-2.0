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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.Salt.Exception.Generic.DuplicateDataException;
import me.Salt.Exception.Permission.UnregisteredPermissionException;
import me.Salt.Logging.LogUtils;
import me.Salt.Permissions.Perm;
import me.Salt.Permissions.Permission;
import me.Salt.SaltAPI.User.JUser;
import me.Salt.SaltAPI.Util.PrivilegeState;
import me.Salt.SaltAPI.Util.WarningBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A container class for users.
 */
public class JUserImpl implements JUser {
    /**
     * The {@link User} that this class references to.
     */
    private User user;
    /**
     * A list of this user's warnings.
     */
    @Expose
    private List<WarningBuilder.Warning> warnings = new ArrayList<>();
    /**
     * A list of this user's permissions.
     */
    @Expose
    private List<Permission> permissions = new ArrayList<>();
    /**
     * A HashMap of each {@link Perm}'s, and their corresponding {@link Permission}'s for this user.
     */
    private HashMap<Perm, Permission> perms = new HashMap<>();
    /**
     * The {@link PrivilegeState} of this user.
     */
    @Expose
    private PrivilegeState privilegeState;
    /**
     * This user's ID. Used for auto-loading and recovery after the bot restarts.
     */
    @Expose
    private long userId;
    /**
     * The time at which the last message was sent by this user.
     */
    @Expose
    private LocalDateTime lastMessage;
    /**
     * The time at which the user was last seen online.
     */
    @Expose
    private LocalDateTime lastOnline;
    /**
     * The last guild that the user sent a message in.
     */
    @Expose
    @SerializedName("lastSpokenGuildId")
    private Guild lastSpokenGuild;
    /**
     * The last textchannel that the user sent a message in.
     */
    @Expose
    @SerializedName("lastTextChannelId")
    private TextChannel lastTextChannel;
    /**
     * The last-known nickname of the user.
     */
    @Expose
    private String lastNickname;
    
    //TODO add a currency variable
    public JUserImpl(User user, List<WarningBuilder.Warning> warnings, List<Permission> permissions,
                     PrivilegeState privilegeState, long userId, LocalDateTime lastMessage, LocalDateTime lastOnline,
                     Guild lastSpokenGuild, TextChannel lastTextChannel, String lastNickname) {
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
    public String toString() {
        return "JUserImpl{" + "user=" + user + ", warnings=" + warnings.toString() + ", permissions=" + permissions.toString() + ", privilegeState=" + privilegeState + ", userId='" + userId + '\'' + ", lastMessage=" + lastMessage + ", lastOnline=" + lastOnline + ", lastSpokenGuild=" + lastSpokenGuild + ", lastTextChannel=" + lastTextChannel + ", lastNickname='" + lastNickname + '\'' + '}';
    }
    
    @Override
    public User getUser() {
        return user;
    }
    
    @Override
    public List<WarningBuilder.Warning> getWarnings() {
        return warnings;
    }
    
    @Override
    public List<Permission> getPermissions() {
        return permissions;
    }
    
    @Override
    public PrivilegeState getPrivilegeState() {
        return privilegeState;
    }
    
    @Override
    public JUser setPrivilegeState(PrivilegeState privilegeState) {
        this.privilegeState = privilegeState;
        return this;
    }
    
    @Override
    public long getUserId() {
        return userId;
    }
    
    @Override
    public LocalDateTime getLastMessage() {
        return lastMessage;
    }
    
    @Override
    public JUser setLastMessage(LocalDateTime lastMessage) {
        this.lastMessage = lastMessage;
        return this;
    }
    
    @Override
    public LocalDateTime getLastOnline() {
        return lastOnline;
    }
    
    @Override
    public JUser setLastOnline(LocalDateTime lastOnline) {
        this.lastOnline = lastOnline;
        return this;
    }
    
    @Override
    public Guild getLastSpokenGuild() {
        return lastSpokenGuild;
    }
    
    @Override
    public JUser setLastSpokenGuild(Guild lastSpokenGuild) {
        this.lastSpokenGuild = lastSpokenGuild;
        return this;
    }
    
    @Override
    public TextChannel getLastTextChannel() {
        return lastTextChannel;
    }
    
    @Override
    public JUser setLastTextChannel(TextChannel lastTextChannel) {
        this.lastTextChannel = lastTextChannel;
        return this;
    }
    
    @Override
    public String getLastNickname() {
        return lastNickname;
    }
    
    @Override
    public JUser setLastNickname(String lastNickname) {
        this.lastNickname = lastNickname;
        return this;
    }
    
    @Override
    public JUser addWarning(WarningBuilder.Warning warning) {
        this.warnings.add(warning);
        return this;
    }
    
    @Override
    public JUser removeWarning(WarningBuilder.Warning warning) {
        if (this.warnings.contains(warning)) this.warnings.remove(warning);
        return this;
    }
    
    @Override
    public JUser addPermission(Permission permission) throws DuplicateDataException {
        if (this.permissions.contains(permission))
            throw new DuplicateDataException("This user already has this permission!");
        else this.permissions.add(permission);
        this.perms.put(permission.getPermEnum(), permission);
        return this;
    }
    
    @Override
    public JUser removePermission(Perm perm) {
        if (this.perms.containsKey(perm)) {
            if (this.permissions.contains(perms.get(perm))) {
                this.permissions.remove(perms.get(perm));
                this.perms.remove(perm);
            }
        }
        return this;
    }
    
    @Override
    public boolean hasPermission(Perm perm) {
        return this.perms.get(perm).getPermEnum().equals(perm);
    }
    
    @Override
    public boolean hasPermission(Perm perm, Permission.Range range) {
        if (!this.perms.containsKey(perm)) try {
            throw new UnregisteredPermissionException("This permission has not been registered!");
        } catch (UnregisteredPermissionException e) {
            LogUtils.log(e.getMessage(), LogUtils.Severity.WARNING);
        }
        return this.perms.get(perm).getPermEnum().equals(perm) && this.perms.get(perm).getRange().equals(range);
    }
}
