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

package me.Salt.Event.jevent;

import me.Salt.Command.Container.CommandParser;
import net.dv8tion.jda.core.events.Event;

public class CommandPreExecuteEvent extends GenericCommandEvent {
    private CommandParser.ParsedCommandContainer cmd;
    private Event event;
    private boolean executionSuccess;

    public CommandPreExecuteEvent(CommandParser.ParsedCommandContainer cmd, Event event, boolean executionSuccess) {
        this.cmd = cmd;
        this.event = event;
        this.executionSuccess = executionSuccess;
    }

    public CommandParser.ParsedCommandContainer getCmd() {
        return cmd;
    }

    public Event getEvent() {
        return event;
    }

    public boolean isExecutionSuccess() {
        return executionSuccess;
    }
}
