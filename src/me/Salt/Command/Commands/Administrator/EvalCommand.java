package me.Salt.Command.Commands.Administrator;

import bsh.EvalError;
import bsh.Interpreter;
import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Exception.Command.DisabledCommandException;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Exception.Permission.LackingPermissionException;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class EvalCommand extends Command implements ICommand {
    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) throws LackingPermissionException, MissingDataException, DisabledCommandException {
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        Interpreter bsh = new Interpreter();

        try {
            bsh.eval("System.out.println((\"Hello\"))");
        } catch (EvalError evalError) {
            e.getChannel().sendMessage(evalError.getMessage() + "\n" + evalError.getErrorText());
        }
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }

    public EvalCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }
}
