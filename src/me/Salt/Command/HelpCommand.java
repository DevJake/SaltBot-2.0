package me.Salt.Command;

import me.Salt.Container.CommandParser;

/**
 * Created by Salt001 on 10/04/2017.
 */
public class HelpCommand implements ICommand {

    @Override
    public boolean preExecution(CommandParser.CommandContainer cmd) {
        return false;
    }

    @Override
    public void execute(CommandParser.CommandContainer cmd) {

    }

    @Override
    public void postExecution(CommandParser.CommandContainer cmd) {

    }
}
