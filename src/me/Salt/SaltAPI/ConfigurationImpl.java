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

import me.Salt.SaltAPI.Guild.JGuild;
import me.Salt.SaltAPI.User.JUser;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 10/04/2017.
 */
public class ConfigurationImpl implements IConfiguration {
    private String cmdPrefix;
    private HashMap<String, JUser> JUsers; //Store by ID, JUser
    private HashMap<String, JGuild> JGuilds; //As above; Store by ID, Guild
    private Map<User, Rank> staff;

    public ConfigurationImpl(String cmdPrefix, Map<User, Rank> staff) {
        this.cmdPrefix = cmdPrefix;
        this.staff = staff;
    }

    @Override
    public String getCmdPrefix() {
        return cmdPrefix;
    }

    @Override
    public List<JUser> getJUsers() {
        return new ArrayList<>(JUsers.values());
    }

    @Override
    public JUser getJUserByID(String id) {
        return JUsers.get(id); //TODO Add safety check
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
    public JGuild getJGuildByID(String id) {
        return JGuilds.get(id); //TODO Add safety check
    }

    @Override
    public List<JGuild> getJGuildsByName(String name) {
        return JGuilds.size() > 0 ? JGuilds.values().stream().filter(jUser -> jUser.getGuild().getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList()) : null;
    }

    @Override
    public Map<User, Rank> getTeam() {
        return staff;
    }


}