package me.Salt.Command;

import me.Salt.Exception.MissingDataException;
import me.Salt.Permissions.Permission;
import net.dv8tion.jda.core.entities.User;

import java.util.HashMap;
import java.util.List;


/**
 * ->> SaltBot-2.0 <<-
 * Created by Salt on 09/04/2017.
 */
public class CommandDescriptionBuilder {

    private HashMap<String, Boolean> parameters;
    private HashMap<String, Boolean> definers;
    private String name;
    private String description;
    private User author;
    private String helpMessage;
    private boolean isComplete;
    private boolean supportsPermissions;
    private List<Permission> requiredPermissions;
    private boolean deprecated;
    private List<String> commandCallers;

    public CommandDescriptionBuilder setCommandCallers(List<String> commandCallers) {
        this.commandCallers = commandCallers; //TODO convert all elements to lowercase
        return this;
    }

    public CommandDescriptionBuilder setParameters(HashMap<String, Boolean> parameters) {
        this.parameters = parameters;
        return this;
    }

    public CommandDescriptionBuilder setDefiners(HashMap<String, Boolean> definers) {
        this.definers = definers;
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

    public CommandDescriptionBuilder setAuthor(User author) {
        this.author = author;
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

    public CommandDescriptionBuilder setSupportsPermissions(boolean supportsPermissions) {
        this.supportsPermissions = supportsPermissions;
        return this;
    }

    public CommandDescriptionBuilder setRequiredPermissions(List<Permission> requiredPermissions) {
        this.requiredPermissions = requiredPermissions;
        return this;
    }

    public CommandDescriptionBuilder setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    public CommandDescription build() throws MissingDataException {
        Checker checker = () -> name != null && description != null && author != null; //TODO Rework for new variables
        if (checker.check())
            return new CommandDescription(parameters, definers, name, description, author, isComplete, supportsPermissions, requiredPermissions, helpMessage, deprecated, commandCallers);
        else throw new MissingDataException("Invalid data entered! Likely null values.");
        //TODO add Logger.write(...) call

    }

    private interface Checker {
        boolean check();
    }

    //TODO put checking code in each setter
}
