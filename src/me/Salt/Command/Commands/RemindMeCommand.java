package me.Salt.Command.Commands;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Util.Reminder.Reminder;
import me.Salt.Util.Reminder.ReminderBuilder;
import me.Salt.Util.Reminder.ReminderHandler;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.concurrent.TimeUnit;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 19/04/2017.
 */
public class RemindMeCommand extends Command implements ICommand {

    public RemindMeCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, Event event) {
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        Reminder r = new ReminderBuilder()
                .addChannel(e.getChannel())
                .addMentionable(e.getAuthor())
                .addRemindTime(10, TimeUnit.SECONDS)
                .setCreator(e.getAuthor())
                .addEndTime(3, TimeUnit.SECONDS)
                .addEndTime(5, TimeUnit.SECONDS)
                .setMessage("Timer finished!")
                .setRecursive(false)
                .setStartTime(System.currentTimeMillis())
                .build();
        ReminderHandler.setNewReminder(r);


        e.getChannel().sendMessage("Scheduled a test reminder for " + TimeUnit.MILLISECONDS.toSeconds(r.getEndTime()) + " seconds from now").queue();
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
