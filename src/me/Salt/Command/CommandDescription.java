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

package me.Salt.Command;

import com.google.gson.annotations.Expose;
import me.Salt.Permissions.Perm;
import me.Salt.Util.Cooldown;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 09/04/2017.
 */
public class CommandDescription {
    private List<Field> fields; //List of parameters and definers
    //TODO Make wiki entry on what parameters and definers are
    private String name;
    private String description; //Summary of fullDescription
    private String fullDescription; //A full description of the command, including how it's used, example usages, etc.
    private List<User> authors;
    private boolean isComplete;

    private List<Perm> requiredPermissions;
    private String helpMessage;
    private boolean deprecated;
    private List<String> aliases; //What text is to be identified by the program as pointing to this command
    private Cooldown cooldown;
    private boolean hasCooldown = false;

    public CommandDescription(List<Field> fields, String name, String description, String fullDescription, List<User> authors, boolean isComplete, List<Perm> requiredPermissions, String helpMessage, boolean deprecated, List<String> aliases, Cooldown cooldown) {
        this.fields = fields;
        this.name = name;
        this.description = description;
        this.fullDescription = fullDescription;
        this.authors = authors;
        this.isComplete = isComplete;
        this.requiredPermissions = requiredPermissions;
        this.helpMessage = helpMessage;
        this.deprecated = deprecated;
        this.aliases = aliases;
        this.cooldown = cooldown;
    }

    public boolean hasCooldown() {
        return this.cooldown != null;
    }

    public List<Field> getFields() {
        return fields;
    }

    public Cooldown getCooldown() {
        return cooldown;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public List<User> getAuthors() {
        return authors;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public List<Perm> getRequiredPermissions() {
        return requiredPermissions;
    }

    public String getHelpMessage() {
        return helpMessage;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public String toString() {
        return "CommandDescription{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author='" + authors.toString() + '\'' +
                ", helpMessage='" + helpMessage + '\'' +
                '}';
    }
}