package me.Salt.Util;

import me.Salt.Command.ICommand;
import me.Salt.Container.CommandParser;
import me.Salt.Exception.MalformedParametersException;

/**
 * Manages the execute cycle of a command
 */
public class CommandExecutor {
    public static void execute(ICommand command, CommandParser.CommandContainer cmd) throws MalformedParametersException {

        Executor ex = (c, d) -> {
            if (c.preExecution(d)) {
                c.execute(cmd);
                c.postExecution(cmd);
            } else throw new MalformedParametersException("Malformed command parameters!");
            //TODO add Logger.write(...) call
        };

        ex.execute(command, cmd);
    }

    private interface Executor {
        void execute(ICommand command, CommandParser.CommandContainer cmd) throws MalformedParametersException;
    }
}
