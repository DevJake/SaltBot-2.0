package me.Salt.Command;

import me.Salt.Permissions.Permission;
import net.dv8tion.jda.core.entities.User;

import java.util.HashMap;
import java.util.List;

/**
 * ->> SaltBot-2.0 <<-
 * Created by Salt on 09/04/2017.
 */
public class CommandDescription {
    private HashMap<String, Boolean> parameters;
    private HashMap<String, Boolean> definers;
    private String name;
    private String description;
    private User author;
    private boolean isComplete;
    private boolean supportsPermissions;
    private List<Permission> requiredPermissions;
    private String helpMessage;
    private boolean deprecated;
    private List<String> commandCallers; //What text is to be identified by the program as pointing to this command

    public CommandDescription(HashMap<String, Boolean> parameters, HashMap<String, Boolean> definers, String name, String description, User author, boolean isComplete, boolean supportsPermissions, List<Permission> requiredPermissions, String helpMessage, boolean deprecated, List<String> commandCallers) {
        this.parameters = parameters;
        this.definers = definers;
        this.name = name;
        this.description = description;
        this.author = author;
        this.isComplete = isComplete;
        this.supportsPermissions = supportsPermissions;
        this.requiredPermissions = requiredPermissions;
        this.helpMessage = helpMessage;
        this.deprecated = deprecated;
        this.commandCallers = commandCallers;
    }

    public HashMap<String, Boolean> getParameters() {
        return parameters;
    }

    public HashMap<String, Boolean> getDefiners() {
        return definers;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getAuthor() {
        return author;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public boolean isSupportsPermissions() {
        return supportsPermissions;
    }

    public List<Permission> getRequiredPermissions() {
        return requiredPermissions;
    }

    public String getHelpMessage() {
        return helpMessage;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public List<String> getCommandCallers() {
        return commandCallers;
    }

    @Override
    public String toString() {
        return "CommandDescription{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author.getName() + '\'' +
                ", helpMessage='" + helpMessage + '\'' +
                '}';
    }
}
