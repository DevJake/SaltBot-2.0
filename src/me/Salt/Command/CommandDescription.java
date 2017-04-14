package me.Salt.Command;

import me.Salt.Permissions.Permission;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Salt001 on 09/04/2017.
 */
class CommandDescription {
    private final HashMap<String, Boolean> parameters;
    private final HashMap<String, Boolean> definers;
    private String name;
    private String description;
    private String author;
    private boolean isComplete;
    private boolean supportsPermissions;
    private List<Permission> requiredPermissions;
    private String helpMessage;


    public CommandDescription(HashMap<String, Boolean> parameters, HashMap<String, Boolean> definers, String name, String description, String author, boolean isComplete, boolean supportsPermissions, List<Permission> requiredPermissions, String helpMessage) {
        this.parameters = parameters;
        this.definers = definers;
        this.name = name;
        this.description = description;
        this.author = author;
        this.isComplete = isComplete;
        this.supportsPermissions = supportsPermissions;
        this.requiredPermissions = requiredPermissions;
        this.helpMessage = helpMessage;
    }

    @Override
    public String toString() {
        return "CommandDescription{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", helpMessage='" + helpMessage + '\'' +
                '}';
    }
}
