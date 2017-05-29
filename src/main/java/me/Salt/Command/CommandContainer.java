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
package me.Salt.Command;

import java.util.List;

/**
 * This class contains details about a Command, and is used to control the functionality of the CommandExecutor.
 * It determines which commands are listening to which events, and if the command is deprecated.
 */
public class CommandContainer {
    private CommandDescription commandDescription;
    private List<JEventType> events;
    
    /**
     * @param commandDescription CommandDescription - The CommandDescription instance with details about a command.
     * @param events             List - A list of events that this command is set to listen to.
     */
    //TODO remove List<JEventType> parameter, as it's not used.
    public CommandContainer(CommandDescription commandDescription, List<JEventType> events) {
        this.commandDescription = commandDescription;
        this.events = events;
    }
    
    /**
     * @return CommandDescription - This class' CommandDescription instance
     */
    public CommandDescription getCommandDescription() {
        return commandDescription;
    }
    
    /**
     * @return List - A list of events that this command is set to listen to.
     */
    public List<JEventType> getEvents() {
        return events;
    }
    
    /**
     * An enum storing different types of events that a command may choose to listen to.
     */
    public enum JEventType {
        /**
         * A generic message (both Private and Guild messages).
         */
        GENERIC_MESSAGE,
        /**
         * A message sent via private message.
         */
        PRIVATE_MESSAGE,
        /**
         * A message sent to a guild.
         */
        GUILD_MESSAGE
    }
}
