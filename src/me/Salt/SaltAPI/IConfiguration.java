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
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Permissions.PermissionHandler;
import me.Salt.SaltAPI.Entities.RoadItem;
import me.Salt.SaltAPI.Guild.JGuild;
import me.Salt.SaltAPI.User.JUser;
import me.Salt.Util.Language.LangCode;
import me.Salt.Util.Language.LanguageHandler;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 10/04/2017.
 */
public interface IConfiguration {
    public String getCmdPrefix();

//    public List<JUser> getjUsers();
//
//    public JUser getJUserByID(String id); //TODO Develop unique method of converting users to an ID. Could use inbuilt IDs, but would be difficult to discriminate between default ID and API-wide ID.
//
//    public List<JUser> getJUsersByName(String name);
//
//    public List<JGuild> getjGuilds();
//
//    public JGuild getJGuildByID(String id);
//
//    public List<JGuild> getJGuildsByName(String name);

    public HashMap<User, List<IConfiguration.Authority>> getStaff(); //TODO

    public HashMap<String, ICommand> getCommands();

    public String getName();

    public String getWebsite();

    public long getStartupTime();

    public long getUptime();

    public boolean isDebugMode();

    public Color getEmbedColour();

    public int getCommandCount();

    public int getMessageCount();

    public void incrementCommandCount();

    public void incrementMessageCount();

    public void setJUser(JUser user);

    public void setJGuild(JGuild guild);

    public JUser getJUserById(String id) throws MissingDataException;

    public JGuild getJGuildById(String id) throws MissingDataException;

    public List<JUser> getjUsers();

    public List<JUser> getJUsersByName(String name);

    public List<JGuild> getjGuilds();

    public List<JGuild> getJGuildsByName(String name);

    public PermissionHandler getPermissionHandler();

    public List<RoadItem> getRoadmap();

    public boolean addRoadmapItem(RoadItem r);

    public void removeRoadmapItem(RoadItem r);

    public void shutdown(boolean saveData);

    public void init();

    public LanguageHandler getLanguageHandler();

    public LangCode getDefaultLangCode();

    public enum Authority {
        OWNER,
        DEVELOPER,
        CONTRIBUTOR,
        TESTER
    }
}
