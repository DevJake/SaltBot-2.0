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

package main.java.me.Salt.Util.Utility.Reminder;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 19/04/2017.
 */
public class Reminder {
    private User creator;
    private long startTime;
    private long endTime; //In millis
    private List<User> mentionables = new ArrayList<>(); //Who to mention when the timer fires
    private String message; //The message to display when the timer finishes
    private boolean recursive; //Should the timer restart when it finishes?
    private List<Long> remindTimes = new ArrayList<>(); //When should mentionables be told about the reminder? Such as 1 week before.
    private List<TextChannel> channels = new ArrayList<>(); //Channel to put message in
    //TODO allow a specific message to be mentioned. Can then quote the message content, as well as providing date/time/author details about it.

    public Reminder(User creator, long startTime, long endTime, List<User> mentionables, String message, boolean recursive, List<Long> remindTimes, List<TextChannel> channels) {
        this.creator = creator;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mentionables = mentionables;
        this.message = message;
        this.recursive = recursive;
        this.remindTimes = remindTimes;
        this.channels = channels;
    }

    public User getCreator() {
        return creator;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public List<User> getMentionables() {
        return mentionables;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public List<Long> getRemindTimes() {
        return remindTimes;
    }

    public List<TextChannel> getChannels() {
        return channels;
    }
}
