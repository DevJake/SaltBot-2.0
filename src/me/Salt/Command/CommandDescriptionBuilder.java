/*
 *
 *  * Copyright (c) 2017 DevJake
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package me.Salt.Command;

import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Permissions.Perm;
import me.Salt.Util.Cooldown;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Project title: SaltBot-2.0
 * Created by Salt on 09/04/2017.
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

    public CommandDescriptionBuilder addAlias(String alias) {
        this.aliases.add(alias.toLowerCase()); //TODO convert all elements to lowercase
        return this;
    }

    public CommandDescriptionBuilder setCooldown(Cooldown cooldown) {
        this.cooldown = cooldown;
        return this;
    }


    public CommandDescriptionBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CommandDescriptionBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandDescriptionBuilder setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
        return this;
    }

    public CommandDescriptionBuilder addAuthor(User author) {
        this.authors.add(author);
        return this;
    }

    public CommandDescriptionBuilder addField(Field field) {
        this.fields.add(field);
        return this;
    }

    public CommandDescriptionBuilder setHelpMessage(String helpMessage) {
        this.helpMessage = helpMessage;
        return this;
    }

    public CommandDescriptionBuilder setComplete(boolean complete) {
        isComplete = complete;
        return this;
    }

    public CommandDescriptionBuilder addRequiredPermissions(Perm permission) {
        this.requiredPermissions.add(permission);
        return this;
    }

    public CommandDescriptionBuilder setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    public CommandDescription build() throws MissingDataException {
        Checker checker = () -> true; //TODO Rework for new variables
        if (checker.check())
            return new CommandDescription(fields, name, description, fullDescription, authors, isComplete, requiredPermissions, helpMessage, deprecated, aliases, cooldown);
        else throw new MissingDataException("Invalid data entered! Likely null values.");
        //TODO add Logger.write(...) call

    }

    private interface Checker {
        boolean check();
    }

    //TODO put checking code in each setter
}
