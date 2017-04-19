package me.Salt.Util.Reminder;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 19/04/2017.
 */
public class ReminderHandler {

    //TODO make sure a bot restart doesn't stop a reminder, or reset the timer.
    public static void setNewReminder(Reminder reminder) {
        Runnable r = () -> {
            reminder.getChannels().forEach(textChannel -> textChannel.sendMessage(reminder.getMessage()).queue()); //TODO Convert to Embed
        };

//        System.out.println(System.currentTimeMillis());
//        System.out.println(reminder.getEndTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//        System.out.println(reminder.getEndTime().toInstant(ZoneOffset.ofHours(0)).getEpochSecond());
//        System.out.println();


        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); //TODO use algorithm to determine an appropriate value. Perhaps the amount of users, minus inactive users, divided by a value.
        ScheduledFuture s = scheduler.schedule(r, reminder.getEndTime(), TimeUnit.MILLISECONDS);
        //ScheduledFuture s = scheduler.schedule(r, reminder.getEndTime(), TimeUnit.SECONDS);

    }
}
