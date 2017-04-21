/*
 * Copyright (c) 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.Salt.Command.Commands;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Main;
import me.Salt.Util.Reminder.Reminder;
import me.Salt.Util.Reminder.ReminderBuilder;
import me.Salt.Util.Reminder.ReminderHandler;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Allows the user to establish a timescale, in which a timer shall be started. At the end of the timer, the user shall be notified that their timer has expired.
 * This allows an individual to set up a reminder, to remind them in any range of time periods.
 */
public class ReminderCommand extends Command implements ICommand {
    final Pattern TIMEMEASURE = Pattern.compile("\\d*[smhd]{1}"); //Filters out the remind time (such as 3s10m for 10 minutes and 3 seconds)
    final Pattern MESSAGE = Pattern.compile("\"{1}.+\"{1}"); //Filters out the message to be displayed

    public ReminderCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) {
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        ReminderBuilder rb = new ReminderBuilder();
        rb.addChannel(e.getChannel());
        rb.addMentionable(e.getAuthor());
        rb.setCreator(e.getAuthor());

        Matcher m = TIMEMEASURE.matcher(cmd.getRawText().toLowerCase().replaceFirst(Main.salt.getCmdPrefix() + cmd.getCmd() + " ", ""));
        while (m.find()) {
            String n = m.group();
            switch (n.substring(n.length() - 1, n.length())) {
                case "s":
                    rb.addEndTime(Long.valueOf(n.substring(0, n.length() - 1)), TimeUnit.SECONDS);
                    continue;
                case "m":
                    rb.addEndTime(Long.valueOf(n.substring(0, n.length() - 1)), TimeUnit.MINUTES);
                    continue;
                case "h":
                    rb.addEndTime(Long.valueOf(n.substring(0, n.length() - 1)), TimeUnit.HOURS);
                    continue;
                case "d":
                    rb.addEndTime(Long.valueOf(n.substring(0, n.length() - 1)), TimeUnit.DAYS);
                    continue;
            }
        }

        if (MESSAGE.matcher(cmd.getRawText()).find())
            rb.setMessage(MESSAGE.matcher(cmd.getRawText()).group()); //Don't convert to lower
        else rb.setMessage("Here is your reminder " + e.getAuthor().getAsMention() + "!");

        rb.setStartTime(System.currentTimeMillis()); //Do last to get the closest possible timing
        Reminder r = rb.build();
        ReminderHandler.setNewReminder(r);

        ZonedDateTime z = Instant.ofEpochMilli(System.currentTimeMillis() + r.getEndTime()).atZone(ZoneId.of("Europe/London"));
        e.getChannel().sendMessage("Scheduled a new reminder for " + z.format(DateTimeFormatter.ofPattern("EEE, dd MMM")) + " at " + z.format(DateTimeFormatter.ofPattern("K:m:s a"))).queue(j -> j.delete().queueAfter(10, TimeUnit.SECONDS)); //TODO format to be like 1st, 2nd and 3rd, etc.
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
