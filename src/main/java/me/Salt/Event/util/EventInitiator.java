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

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for the registering and unregistering of {@link JEventListener}s, as well the
 * distribution of fired events across the registered listeners.
 */
public class EventInitiator {
    /**
     * A list of registered event listeners which are triggered upon the firing of an event.
     */
    private static List<JEventListener> listeners = new ArrayList<>();
    
    /**
     * This method takes in a subclass of {@link JEvent}, and iterates through each registered listener, firing the
     * event to each.
     *
     * @param event {@link JEvent} - The instance of an event to be fired
     */
    public static void fire(JEvent event) {
        listeners.forEach(l -> l.onEvent(event));
    }
    
    /**
     * This method registers any passed-in instances of {@link JEventListener} to the internal list of listeners.
     *
     * @param listener {@link JEventListener} - The instance of JEventListener to be registered
     */
    public static void register(JEventListener listener) {
        listeners.add(listener);
    }
    
    /**
     * Much like {@link EventInitiator#register(JEventListener)}, this method accepts an instance of
     * {@link JEventListener}. If the instance is already registered, it is then removed from the list of registered
     * listeners.
     *
     * @param listener {@link JEventListener} - The instance of JEventListener to be unregistered
     */
    public static void unregister(JEventListener listener) {
        listeners.remove(listener);
    }
}
