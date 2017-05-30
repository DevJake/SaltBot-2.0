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
 * This interface can be used in two implementations;
 * <ul>
 * <li>Through {@link JListenerAdapter}, or;</li>
 * <li>Through direct implementation</li>
 * </ul>
 * <p>
 * If this interface is directly implemented, implementing classes will be responsible for receiving and handling
 * events through {@link JEventListener#onEvent(JEvent)}. The instance type of the event isn't specified, therefore
 * requiring the class to check the instance type themselves:
 * <p>
 * <code>
 * public class MyCustomListener implements JEventListener {
 *
 *     public void onEvent(JEvent event) {
 *         if (event instanceof GenericCommandEvent){
 *         //Do stuff
 *         } else if (event instanceof GenericCaDEvent){
 *         //Do other stuff
 *         }
 *     }
 * }
 * </code>
 */
public interface JEventListener {
    /**
     * This method is called upon when {@link EventInitiator#fire(JEvent)} is called. Therefore, any implementing
     * class of this interface will receive the fired event.
     *
     * @param event {@link JEvent} - The event being fired
     */
    public void onEvent(JEvent event);
}
