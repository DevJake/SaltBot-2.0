package me.Salt.Command;

import me.Salt.Container.CommandParser;

/**
 * Created by Salt001 on 10/04/2017.
 */
public interface ICommand {
    public boolean preExecution(CommandParser.CommandContainer cmd);

    public void execute(CommandParser.CommandContainer cmd);

    public void postExecution(CommandParser.CommandContainer cmd);
}
