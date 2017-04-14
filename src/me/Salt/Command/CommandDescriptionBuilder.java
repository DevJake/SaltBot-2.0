package me.Salt.Command;

import me.Salt.Exception.MissingDataException;
import me.Salt.Permissions.Permission;

import java.util.HashMap;
import java.util.List;


/**
 ->> SaltBot-2.0 <<-
 * Created by Salt on 09/04/2017.
 */
public class CommandDescriptionBuilder {

    private HashMap<String, Boolean> parameters;
    private HashMap<String, Boolean> definers;
    private String name;
    private String description;
    private String author;
    private String helpMessage;
    private boolean isComplete;
    private boolean supportsPermissions;
    private List<Permission> requiredPermissions;

    public CommandDescriptionBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CommandDescriptionBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandDescriptionBuilder setAuthor(String author) {
        this.author = author;
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

    public CommandDescriptionBuilder setHelpMessage(String helpMessage) {
        this.helpMessage = helpMessage;
        return this;
    }

    public CommandDescription build() throws MissingDataException {
        Checker checker = () -> name != null && description != null && author != null;
        if (checker.check())
            return new CommandDescription(parameters, definers, name, description, author, isComplete, supportsPermissions, requiredPermissions, helpMessage);
        else throw new MissingDataException("Invalid data entered! Likely null values.");
        //TODO add Logger.write(...) call

    }

    private interface Checker {
        boolean check();
    }

    //TODO put checking code in each setter
}
