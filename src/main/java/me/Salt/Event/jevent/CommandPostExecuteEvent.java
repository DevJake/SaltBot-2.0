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
import me.Salt.Event.jevent.util.JEvent;
import net.dv8tion.jda.core.events.Event;

public class CommandPostExecuteEvent extends JEvent {
    private CommandParser.ParsedCommandContainer cmd;
    private Event event;

    public CommandPostExecuteEvent(CommandParser.ParsedCommandContainer cmd, Event event) {
        this.cmd = cmd;
        this.event = event;
    }

    public CommandParser.ParsedCommandContainer getCmd() {
        return cmd;
    }

    public Event getEvent() {
        return event;
    }
}
