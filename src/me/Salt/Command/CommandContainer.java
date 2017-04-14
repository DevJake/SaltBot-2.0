package me.Salt.Command;

import java.util.List;

/**
 * ->> SaltBot-2.0 <<-
 * Created by Salt on 14/04/2017.
 * <p>
 * This class contains details about a Command, and is used to control the functionality of the CommandExecutor.
 * It determines which commands are listening to which events, and if the command is deprecated.
 */
public class CommandContainer {
    private ICommand command;
    private CommandDescription commandDescription;
    private List<JEvent> events;

    public CommandContainer(ICommand command, CommandDescription commandDescription, List<JEvent> events) {
        this.command = command;
        this.commandDescription = commandDescription;
        this.events = events;

    }

    public ICommand getCommand() {
        return command;
    }

    public CommandDescription getCommandDescription() {
        return commandDescription;
    }

    public List<JEvent> getEvents() {
        return events;
    }

    public enum JEvent {
        GENERIC_MESSAGE,
        PRIVATE_MESSAGE,
        GUILD_MESSAGE
    }
}
