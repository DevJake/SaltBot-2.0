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

import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Permissions.Perm;
import me.Salt.Util.Cooldown;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This class aims to act as a builder for building instances of {@link CommandDescription}.
 */
public class CommandDescriptionBuilder {
    private List<Field> fields = new ArrayList<>(); //List of parameters and definers
    private String name;
    private String description;
    private String fullDescription; //A full description of the command, including how it's used, example usages, etc.
    private List<User> authors = new ArrayList<>();
    private String helpMessage;
    private boolean isComplete;
    private List<Perm> requiredPermissions = new ArrayList<>(); //TODO convert to HashMap of Perm, followed by a boolean of if it's required to use the command at all.
    private boolean deprecated;
    private List<String> aliases = new ArrayList<>();
    private Cooldown cooldown;
    
    /**
     * Adds an alias to this command.
     *
     * @param alias String - The alias to add.
     * @return CommandDescriptionBuilder - The instance of this CommandDescriptionBuilder
     */
    public CommandDescriptionBuilder addAlias(String alias) {
        this.aliases.add(alias.toLowerCase()); //TODO convert all elements to lowercase
        return this;
    }
    
    /**
     * Sets the cooldown period of this command.
     *
     * @param cooldown {@link Cooldown} - The new Cooldown container for this command
     * @return CommandDescriptionBuilder - The instance of this CommandDescriptionBuilder
     */
    public CommandDescriptionBuilder setCooldown(Cooldown cooldown) {
        this.cooldown = cooldown;
        return this;
    }
    
    /**
     * Sets the name of this command.
     *
     * @param name String - The name for this command
     * @return CommandDescriptionBuilder - The instance of this CommandDescriptionBuilder
     */
    public CommandDescriptionBuilder setName(String name) {
        this.name = name;
        return this;
    }
    
    /**
     * Sets the description of this command.
     *
     * @param description String - The description for this command
     * @return CommandDescriptionBuilder - The instance of this CommandDescriptionBuilder
     */
    public CommandDescriptionBuilder setDescription(String description) {
        this.description = description;
        return this;
    }
    
    /**
     * Sets the full description of this command.
     *
     * @param fullDescription String - The full description of this command
     * @return CommandDescriptionBuilder - The instance of this CommandDescriptionBuilder
     */
    public CommandDescriptionBuilder setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
        return this;
    }
    
    /**
     * Adds an author of this command.
     *
     * @param author {@link User} - The User to be added as an author of this command
     * @return CommandDescriptionBuilder - The instance of this CommandDescriptionBuilder
     */
    public CommandDescriptionBuilder addAuthor(User author) {
        this.authors.add(author);
        return this;
    }
    
    /**
     * Adds a {@link Field} to this command.
     *
     * @param field {@link Field} - The field to be added
     * @return CommandDescriptionBuilder - The instance of this CommandDescriptionBuilder
     */
    public CommandDescriptionBuilder addField(Field field) {
        this.fields.add(field);
        return this;
    }
    
    /**
     * Sets the help message of this command.
     *
     * @param helpMessage String - The help message for this command
     * @return CommandDescriptionBuilder - The instance of this CommandDescriptionBuilder
     */
    public CommandDescriptionBuilder setHelpMessage(String helpMessage) {
        this.helpMessage = helpMessage;
        return this;
    }
    
    /**
     * Sets the completion state of this command.
     *
     * @param complete Boolean - The completion state of this command
     * @return CommandDescriptionBuilder - The instance of this CommandDescriptionBuilder
     */
    public CommandDescriptionBuilder setComplete(boolean complete) {
        isComplete = complete;
        return this;
    }
    
    /**
     * Adds a required permission to this command.
     *
     * @param permission {@link Perm} - The permission to be added as 'required' for this command's execution
     * @return CommandDescriptionBuilder - The instance of this CommandDescriptionBuilder
     */
    public CommandDescriptionBuilder addRequiredPermissions(Perm permission) {
        this.requiredPermissions.add(permission);
        return this;
    }
    
    /**
     * Sets the deprecation state of this command.
     *
     * @param deprecated Boolean - The deprecation state of this command
     * @return CommandDescriptionBuilder - The instance of this CommandDescriptionBuilder
     */
    public CommandDescriptionBuilder setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }
    
    /**
     * This method builds a new instance of {@link CommandDescription} with the information passed to this class.
     *
     * @return CommandDescription - The new instance of CommandDescription, containing the information passed to this
     * class
     * @throws MissingDataException Thrown if this class is missing information required for the CommandDescription
     */
    public CommandDescription build() throws MissingDataException {
        Checker checker = () -> true; //TODO Rework for new variables
        if (checker.check())
            return new CommandDescription(fields, name, description, fullDescription, authors, isComplete,
                    requiredPermissions, helpMessage, deprecated, aliases, cooldown);
        else throw new MissingDataException("Invalid data entered! Likely null values.");
        //TODO add Logger.write(...) call
    }
    
    private interface Checker {
        boolean check();
    }
    //TODO put checking code in each setter
}
