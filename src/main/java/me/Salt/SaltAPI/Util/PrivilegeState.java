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
package me.Salt.SaltAPI.Util;

import net.dv8tion.jda.core.entities.User;

import java.time.OffsetDateTime;

/**
 * This class represents the privilege state held by a user. Privilege states are used to determine if an individual
 * should be capable of performing restricted commands and using restricted tools. Such examples include premium-only
 * features.
 */
public class PrivilegeState {
    /**
     * The privilege state of the user.
     */
    private State state;
    /**
     * The time at which the state was applied.
     */
    private OffsetDateTime timeGiven;
    /**
     * The user who assigned the user this state.
     */
    private User giver; //Who assigned this individual the state
    
    public PrivilegeState(State state, OffsetDateTime timeGiven, User giver) {
        this.state = state;
        this.timeGiven = timeGiven;
        this.giver = giver;
    }
    
    public State getState() {
        return state;
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public OffsetDateTime getTimeGiven() {
        return timeGiven;
    }
    
    public void setTimeGiven(OffsetDateTime timeGiven) {
        this.timeGiven = timeGiven;
    }
    
    public User getGiver() {
        return giver;
    }
    
    public void setGiver(User giver) {
        this.giver = giver;
    }
    
    public enum State {
        REGULAR,
        PREMIUM,
        //TODO add more
    }
}
