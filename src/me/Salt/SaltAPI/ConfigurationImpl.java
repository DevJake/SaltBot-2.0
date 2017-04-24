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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.ICommand;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Handler.Main;
import me.Salt.Permissions.PermissionHandler;
import me.Salt.SaltAPI.Entities.RoadItem;
import me.Salt.SaltAPI.Guild.JGuild;
import me.Salt.SaltAPI.User.JUser;
import me.Salt.Util.Serialiser.*;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.time.LocalDateTime;
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
    private HashMap<String, JUser> jUsers = new HashMap<>(); //Store by ID, JUser
    private HashMap<String, JGuild> jGuilds = new HashMap<>(); //As above; Store by ID, Guild
    private HashMap<User, List<IConfiguration.Authority>> staff = new HashMap<>(); //TODO add to Json output
    private HashMap<String, ICommand> commands = new HashMap<>();
    @Expose private List<JUser> jUserList = new ArrayList<>();
    @Expose private List<JGuild> jGuildList = new ArrayList<>();
    private boolean debugMode;
    private Color embedColour;
    @Expose private int commandCount;
    @Expose private int messageCount;
    private PermissionHandler permissionHandler;
    private List<RoadItem> roadmap = new ArrayList<>();


    public ConfigurationImpl(long startupTime, String cmdPrefix, String name, String website, HashMap<User, List<Authority>> staff, HashMap<String, ICommand> commands, boolean debugMode, Color embedColour, PermissionHandler permissionHandler, List<RoadItem> roadmap) {
        this.startupTime = startupTime;
        this.cmdPrefix = cmdPrefix;
        this.name = name;
        this.website = website;
        this.staff = staff;
        this.commands = commands;
        this.debugMode = debugMode;
        this.embedColour = embedColour;
        this.permissionHandler = permissionHandler;
        this.roadmap = roadmap;

        jUsers.values().addAll(jUserList);
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
        if (this.jUsers == null || user == null) return;
        try {
            if (!this.jUsers.containsKey(user.getUser().getId())) this.jUsers.put(user.getUser().getId(), user);
            else this.jUsers.replace(user.getUser().getId(), user);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        jUserList.removeAll(this.jUserList.stream().filter(jUser -> jUser.getUserId() == user.getUserId()).collect(Collectors.toList()));
        jUserList.add(user);
    }

    @Override
    public void setJGuild(JGuild guild) {
        if (this.jGuilds == null || guild == null) return;
        if (!this.jGuilds.containsKey(guild.getGuild().getId())) this.jGuilds.put(guild.getGuild().getId(), guild);
        else this.jGuilds.replace(guild.getGuild().getId(), guild);
    }

    @Override
    public JUser getJUserById(String id) throws MissingDataException {
        if (this.jUsers.containsKey(id)) return this.jUsers.get(id);
        else {
            throw new MissingDataException("User could not be found!"); //TODO perhaps replace with a JLogger call...
        }
    }

    @Override
    public JGuild getJGuildById(String id) throws MissingDataException {
        if (this.jGuilds.containsKey(id)) return this.jGuilds.get(id);
        else {
            throw new MissingDataException("Guild could not be found!"); //TODO as above
        }
    }

    @Override
    public List<JUser> getjUsers() {
        return new ArrayList<>(jUsers.values());
    }

    @Override
    public List<JUser> getJUsersByName(String name) {
        return jUsers.size() > 0 ? jUsers.values().stream().filter(jUser -> jUser.getUser().getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList()) : null;
    }

    @Override
    public List<JGuild> getjGuilds() {
        return new ArrayList<>(jGuilds.values());
    }

    @Override
    public List<JGuild> getJGuildsByName(String name) {
        return jGuilds.size() > 0 ? jGuilds.values().stream().filter(jUser -> jUser.getGuild().getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList()) : null;
    }

    public PermissionHandler getPermissionHandler() {
        return permissionHandler;
    }

    @Override
    public List<RoadItem> getRoadmap() {
        return roadmap;
    }

    @Override
    public boolean addRoadmapItem(RoadItem r) {
        if (!this.roadmap.contains(r)) this.roadmap.add(r);
        else return false;
        return true;
    }

    @Override
    public void removeRoadmapItem(RoadItem r) {
        if (this.roadmap.contains(r)) this.roadmap.remove(r);
    }

    public void shutdown(boolean saveCurrentState) {
        //TODO add system of saving data to text files.
        if (saveCurrentState) saveCurrentState();

        /*
        Do shutdown stuff, if needed.
         */

        Main.jda.shutdown();
    }

    public void init() {
        //TODO add system of re-reading data files into the application.

//                    Gson gson = new GsonBuilder()
//                    .serializeNulls()
//                    .excludeFieldsWithoutExposeAnnotation()
//                    .registerTypeAdapter(TextChannel.class, new TextChannelSerialiser())
//                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerialiser())
//                    .create();
//
//            try {
//                event.getChannel().sendMessage("```" + gson.toJsonTree(Main.salt.getJUserById("112633500447838208")).toString() + "```").queue();
//            } catch (MissingDataException e) {
//                System.out.println(e.getMessage());
//            }

        Gson g = new GsonBuilder()
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .registerTypeAdapter(ICommand.class, new ICommandSerialiser())
                .registerTypeAdapter(User.class, new UserSerialiser())
                .registerTypeAdapter(Guild.class, new GuildSerialiser())
                .registerTypeAdapter(CommandContainer.class, new CommandContainerSerialiser())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerialiser())
                //.registerTypeAdapter(JUser.class, new JUserSerialiser())
                .create();

        System.out.println(g.toJsonTree(this).toString());
    }

    private void saveCurrentState() {

    }


}
