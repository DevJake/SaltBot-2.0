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
package me.Salt.Event.util;

/**
 * This class acts as a top-level class for all events. For a class to be passable as an event, it must extend this
 * class.
 */
public class JEvent {
    /**
     * The time that the event was fired, stored as the millisecond Epoch time.
     */
    private long eventTime;
    
    /**
     * This constructor takes no parameters, but is used to assign the event's fire time. It assigns the value of
     * {@link System#currentTimeMillis()} to {@link JEvent#eventTime}.
     */
    public JEvent() {
        this.eventTime = System.currentTimeMillis();
    }
    
    /**
     * @return Long - The time at which the event was fired
     */
    public long getEventTime() {
        return eventTime;
    }
}
