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

package me.Salt.Util.Utility.Reminder;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 19/04/2017.
 */
public class ReminderBuilder {
    private User creator;
    private long startTime;
    private long endTime;
    private List<User> mentionables = new ArrayList<>();
    private String message;
    private boolean recursive;
    private List<Long> remindTimes = new ArrayList<>();
    private List<TextChannel> channels = new ArrayList<>();

    public ReminderBuilder setStartTime(long startTime) {
        this.startTime = startTime;
        return this;
    }

    public ReminderBuilder addChannel(TextChannel channel) {
        this.channels.add(channel);
        return this;
    }

    public ReminderBuilder setCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public ReminderBuilder addEndTime(long value, TimeUnit timeUnit) {
        this.endTime += timeUnit.toMillis(value);
        return this;
    }

    public ReminderBuilder addMentionable(User mentionable) {
        this.mentionables.add(mentionable);
        return this;
    }

    public ReminderBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public ReminderBuilder setRecursive(boolean recursive) {
        this.recursive = recursive;
        return this;
    }

    public ReminderBuilder addRemindTime(long value, TimeUnit timeUnit) {
        this.remindTimes.add(timeUnit.toMillis(value)); //TODO Check works
        return this;
    }

    public Reminder build() {
        return new Reminder(creator, startTime, endTime, mentionables, message, recursive, remindTimes, channels);
    }
}
