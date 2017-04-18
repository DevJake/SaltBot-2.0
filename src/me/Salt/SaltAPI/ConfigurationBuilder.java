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
import me.Salt.Exception.DuplicateDataException;
import me.Salt.Exception.MissingDataException;
import me.Salt.Logging.JLogger;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 10/04/2017.
 */
public class ConfigurationBuilder {
    private long startupTime;
    private String cmdPrefix;
    private String name;
    private String website;
    private HashMap<User, List<IConfiguration.Authority>> staff = new HashMap<>();
    private HashMap<String, ICommand> commands = new HashMap<>();
    private boolean debugMode;
    private Color embedColour;

    public ConfigurationBuilder(String cmdPrefix) {
        this.cmdPrefix = cmdPrefix;
    }

    public ConfigurationBuilder addTeamMember(User user, List<IConfiguration.Authority> authority) {
        staff.put(user, authority);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        authority.forEach(a -> {
            if (authority.indexOf(a) < authority.size()-1) sb.append(a.name() + ", ");
            else sb.append("and " + a.name());
        });
        JLogger.writeToConsole("SETUP", JLogger.Level.CONFIG, "Added a team member (\'" + user.getName() + "\', ID#" + user.getId() + ") with Authority levels of " + sb.toString());
        return this;
    }

    public ConfigurationBuilder addTeamMember(User user, IConfiguration.Authority authority) {
        staff.put(user, Collections.singletonList(authority));
        JLogger.writeToConsole("SETUP", JLogger.Level.CONFIG, "Added a team member (" + user.getName() + ") with Authority levels of " + authority.name());
        return this;
    }

    public ConfigurationBuilder setStartupTime(long startupTime) {
        this.startupTime = startupTime;
        JLogger.writeToConsole("SETUP", JLogger.Level.CONFIG, "Set the startup time"); //TODO Insert the startup time, in regular times, into the string
        return this;
    }

    public ConfigurationBuilder registerCommand(String commandCaller, ICommand command) throws DuplicateDataException, MissingDataException {
        if (commands.containsKey(commandCaller.toLowerCase()))
            throw new DuplicateDataException("Command already added!");
        commands.put(commandCaller.toLowerCase(), command);
        JLogger.writeToConsole("SETUP", JLogger.Level.CONFIG, "Registered a new command (\'" + command.getCmdContainer().getCommandDescription().getName() + "\') and assigned a command-caller of \"" + commandCaller.toLowerCase() + "\"");
        return this;
    }

    public ConfigurationBuilder setName(String name) {
        this.name = name;
        JLogger.writeToConsole("SETUP", JLogger.Level.CONFIG, "Set the name of the bot to \"" + name + "\"");
        return this;
    }

    public ConfigurationBuilder setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
        JLogger.writeToConsole("SETUP", JLogger.Level.CONFIG, "Set the debugging state to \'" + debugMode + "\'");
        return this;
    }

    public ConfigurationBuilder setWebsite(String url) {
        this.website = url;
        JLogger.writeToConsole("SETUP", JLogger.Level.CONFIG, "Set the website of the bot (\"" + url + "\")");
        return this;
    }

    public ConfigurationBuilder setEmbedColour(Color colour) {
        this.embedColour = colour;
        JLogger.writeToConsole("SETUP", JLogger.Level.CONFIG, "Set the Embed colour to " + colour.toString());
        return this;
    }

    public IConfiguration build() throws MissingDataException {
        SafetyChecker c = (cmdPrefix) -> cmdPrefix != null && cmdPrefix.length() >= 1; //TODO Update
        if (c.isSafe(cmdPrefix))
            return new ConfigurationImpl(startupTime, cmdPrefix, name, website, staff, commands, debugMode, embedColour);
        else throw new MissingDataException("Missing data for ConfigurationBuilder!");
        //TODO add Logger.write(...) call
    }

    //TODO add checkers to the builder

    private interface SafetyChecker {
        boolean isSafe(String cmdPrefix);
    }
}
