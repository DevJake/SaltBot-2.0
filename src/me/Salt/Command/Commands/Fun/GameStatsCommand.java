package me.Salt.Command.Commands.Fun;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Exception.Command.DisabledCommandException;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Exception.Permission.LackingPermissionException;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class GameStatsCommand extends Command implements ICommand {
    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) throws LackingPermissionException, MissingDataException, DisabledCommandException {
        throw new DisabledCommandException("Sorry! This command is currently under construction...");
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {

    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }

    public GameStatsCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }
}
