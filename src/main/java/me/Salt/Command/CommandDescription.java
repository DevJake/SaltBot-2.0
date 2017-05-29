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

import me.Salt.Permissions.Perm;
import me.Salt.Util.Cooldown;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

/**
 * This class acts as a container for information about a command.
 */
public class CommandDescription {
    /**
     * The fields (arguments) that this command accepts.
     */
    private List<Field> fields; //List of parameters and definers
    //TODO Make wiki entry on what parameters and definers are
    /**
     * The name of this command.
     */
    private String name;
    /**
     * This command's <b>shortened</b> description. This is not intended to contain help information.
     */
    private String description; //Summary of fullDescription
    /**
     * This command's full-length description.
     */
    private String fullDescription; //A full description of the command, including how it's used, example usages, etc.
    /**
     * The authors of this command.
     */
    private List<User> authors;
    /**
     * A boolean representing if this command is completed.
     */
    private boolean isComplete;
    /**
     * A List of permissions required to execute this command.
     */
    private List<Perm> requiredPermissions; // TODO: 29/05/2017 (Perhaps) move permissions into Fields
    /**
     * The informative helpmessage for this command.
     */
    private String helpMessage; // TODO: 29/05/2017 Add URL variable for a link to more information/in-depth usage/examples
    /**
     * A boolean representing the deprecation-state of this command.
     */
    private boolean deprecated; // TODO: 29/05/2017 Perhaps remove, as commands aren't deprecated, but merely updated
    /**
     * The aliases of this command.
     */
    private List<String> aliases; //What text is to be identified by the program as pointing to this command
    private Cooldown cooldown;
    private boolean hasCooldown = false;
    
    public CommandDescription(List<Field> fields, String name, String description, String fullDescription,
                              List<User> authors, boolean isComplete, List<Perm> requiredPermissions,
                              String helpMessage, boolean deprecated, List<String> aliases, Cooldown cooldown) {
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
    
    // TODO: 29/05/2017 Add setter methods
    @Override
    public String toString() {
        return "CommandDescription{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", author='" + authors
                .toString() + '\'' + ", helpMessage='" + helpMessage + '\'' + '}';
    }
}