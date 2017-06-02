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

import me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.CaDGameManager;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.HandlerContainer;

/**
 * This event is fired when the CaDManager modifies an existing CaDHandler instance.
 */
public class CaDManagerModifyHandlerEvent extends GenericCaDManagerEvent {
    /**
     * The ID that was specified as being the owner of the CaDGameHandler
     */
    private String specifiedOwnerId;
    /**
     * The {@link HandlerContainer} that encases the CaDHandler instance.
     */
    private HandlerContainer handlerContainer;
    /**
     * The old playstate of the handler.
     */
    private CaDGameManager.PlayState oldPlayState;
    /**
     * The new playstate being assigned to this handler.
     */
    private CaDGameManager.PlayState newPlayState;
    
    public CaDManagerModifyHandlerEvent(String specifiedOwnerId, HandlerContainer handlerContainer,
                                        CaDGameManager.PlayState oldPlayState, CaDGameManager.PlayState newPlayState) {
        this.specifiedOwnerId = specifiedOwnerId;
        this.handlerContainer = handlerContainer;
        this.oldPlayState = oldPlayState;
        this.newPlayState = newPlayState;
    }
    
    public String getSpecifiedOwnerId() {
        return specifiedOwnerId;
    }
    
    public HandlerContainer getHandlerContainer() {
        return handlerContainer;
    }
    
    public CaDGameManager.PlayState getOldPlayState() {
        return oldPlayState;
    }
    
    public CaDGameManager.PlayState getNewPlayState() {
        return newPlayState;
    }
}
