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
package me.Salt.Event.jevent.game.cardsagainstdiscord;

import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.CaDGameHandler;

/**
 * This event is fired when a Cards Against Discord game changes its activity state. Activity states can either be
 * true (in progress), or false (not in progress).
 */
public class CaDGameUpdateActiveEvent extends GenericCaDGameEvent {
    /**
     * The {@link CaDGameHandler} instance that was updated.
     */
    private CaDGameHandler gameHandler;
    /**
     * The old activity state of the CaDGameHandler instance.
     */
    private boolean oldState;
    /**
     * The new activity state of the CaDGameHandler instance.
     */
    private boolean newState;
    
    public CaDGameUpdateActiveEvent(CaDGameHandler gameHandler, boolean oldState, boolean newState) {
        this.gameHandler = gameHandler;
        this.oldState = oldState;
        this.newState = newState;
    }
    
    public CaDGameHandler getGameHandler() {
        return gameHandler;
    }
    
    public boolean isOldState() {
        return oldState;
    }
    
    public boolean isNewState() {
        return newState;
    }
}
