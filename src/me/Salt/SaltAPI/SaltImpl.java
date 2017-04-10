package me.Salt.SaltAPI;

import me.Salt.SaltAPI.Guild.JGuild;
import me.Salt.SaltAPI.User.JUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ->> SaltBot-2.0 <<-
 * Created by Salt on 10/04/2017.
 */
public class SaltImpl implements Salt {
    private String cmdPrefix;
    private HashMap<String, JUser> JUsers; //Store by ID, JUser
    private HashMap<String, JGuild> JGuilds; //As above; Store by ID, Guild

    public SaltImpl(String cmdPrefix) {
        this.cmdPrefix = cmdPrefix;
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
}
