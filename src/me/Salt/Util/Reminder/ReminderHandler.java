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

package me.Salt.Util.Reminder;

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

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); //TODO use algorithm to determine an appropriate value. Perhaps the amount of users, minus inactive users, divided by a value.
        ScheduledFuture s = scheduler.schedule(r, reminder.getEndTime(), TimeUnit.MILLISECONDS);
        //ScheduledFuture s = scheduler.schedule(r, reminder.getEndTime(), TimeUnit.SECONDS);

    }
}
