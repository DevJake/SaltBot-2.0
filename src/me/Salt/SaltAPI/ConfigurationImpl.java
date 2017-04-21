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

package me.Salt.SaltAPI;

import me.Salt.Command.ICommand;
import me.Salt.Exception.MissingDataException;
import me.Salt.Permissions.PermissionHandler;
import me.Salt.SaltAPI.Guild.JGuild;
import me.Salt.SaltAPI.User.JUser;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 10/04/2017.
 */
public class ConfigurationImpl implements IConfiguration {
    private final long startupTime;
    private String cmdPrefix;
    private String name;
    private String website;
    private HashMap<String, JUser> JUsers = new HashMap<>(); //Store by ID, JUser
    private HashMap<String, JGuild> JGuilds = new HashMap<>(); //As above; Store by ID, Guild
    private HashMap<User, List<IConfiguration.Authority>> staff = new HashMap<>();
    private HashMap<String, ICommand> commands = new HashMap<>();
    private boolean debugMode;
    private Color embedColour;
    private int commandCount;
    private int messageCount;
    private PermissionHandler permissionHandler;


    public ConfigurationImpl(long startupTime, String cmdPrefix, String name, String website, HashMap<User, List<Authority>> staff, HashMap<String, ICommand> commands, boolean debugMode, Color embedColour, PermissionHandler permissionHandler) {
        this.startupTime = startupTime;
        this.cmdPrefix = cmdPrefix;
        this.name = name;
        this.website = website;
        this.staff = staff;
        this.commands = commands;
        this.debugMode = debugMode;
        this.embedColour = embedColour;
        this.permissionHandler = permissionHandler;

    }

    @Override
    public String getCmdPrefix() {
        return cmdPrefix;
    }

    @Override
    public HashMap<User, List<Authority>> getStaff() {
        return staff;
    }

    @Override
    public HashMap<String, ICommand> getCommands() {
        return commands;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getWebsite() {
        return website;
    }

    @Override
    public long getStartupTime() {
        return startupTime;
    }

    @Override
    public long getUptime() {
        return System.currentTimeMillis() - startupTime;
    }

    @Override
    public boolean isDebugMode() {
        return debugMode;
    }

    @Override
    public Color getEmbedColour() {
        return embedColour;
    }

    @Override
    public int getCommandCount() {
        return commandCount;
    }

    @Override
    public int getMessageCount() {
        return messageCount;
    }

    @Override
    public void incrementCommandCount() {
        commandCount++;
    }

    @Override
    public void incrementMessageCount() {
        messageCount++;

    }

    @Override
    public void setJUser(JUser user) {
        if (this.JUsers == null || user == null) return;
        try {
            if (!this.JUsers.containsKey(user.getUser().getId())) this.JUsers.put(user.getUser().getId(), user);
            else this.JUsers.replace(user.getUser().getId(), user);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setJGuild(JGuild guild) {
        if (this.JGuilds == null || guild == null) return;
        if (!this.JGuilds.containsKey(guild.getGuild().getId())) this.JGuilds.put(guild.getGuild().getId(), guild);
        else this.JGuilds.replace(guild.getGuild().getId(), guild);
    }

    @Override
    public JUser getJUserById(String id) throws MissingDataException {
        if (this.JUsers.containsKey(id)) return this.JUsers.get(id);
        else {
            throw new MissingDataException("User could not be found!"); //TODO perhaps replace with a JLogger call...
        }
    }

    @Override
    public JGuild getJGuildById(String id) throws MissingDataException {
        if (this.JGuilds.containsKey(id)) return this.JGuilds.get(id);
        else {
            throw new MissingDataException("Guild could not be found!"); //TODO as above
        }
    }

    @Override
    public List<JUser> getJUsers() {
        return new ArrayList<>(JUsers.values());
    }

    @Override
    public List<JUser> getJUsersByName(String name) {
        return JUsers.size() > 0 ? JUsers.values().stream().filter(jUser -> jUser.getUser().getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList()) : null;
    }

    @Override
    public List<JGuild> getJGuilds() {
        return new ArrayList<>(JGuilds.values());
    }

    @Override
    public List<JGuild> getJGuildsByName(String name) {
        return JGuilds.size() > 0 ? JGuilds.values().stream().filter(jUser -> jUser.getGuild().getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList()) : null;
    }

    public PermissionHandler getPermissionHandler() {
        return permissionHandler;
    }


}
