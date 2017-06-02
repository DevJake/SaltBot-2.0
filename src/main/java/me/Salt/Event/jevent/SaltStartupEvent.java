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

import me.Salt.Event.util.JEvent;

/**
 * This event is fired upon starting up of the bot.
 */
public class SaltStartupEvent extends JEvent {
    /**
     * The Epoch time, representing the time at which the bot was started. This value will likely differ from the
     * value returned from {@link JEvent#getEventTime()}, as the creation of the startup time is the first code
     * within {@link me.Salt.Main#main(String[])}.
     * <p>
     * This value is assigned using {@link System#currentTimeMillis()}.
     */
    private long startupTimeMillis;
    
    public SaltStartupEvent(long startupTimeMillis) {
        this.startupTimeMillis = startupTimeMillis;
    }
    
    public long getStartupTimeMillis() {
        return startupTimeMillis;
    }
}
