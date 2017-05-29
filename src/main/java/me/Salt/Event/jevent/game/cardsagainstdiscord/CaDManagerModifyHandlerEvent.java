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

public class CaDManagerModifyHandlerEvent extends GenericCaDManagerEvent {
    private String specifiedOwnerId;
    private HandlerContainer handlerContainer;
    private CaDGameManager.PlayState oldPlayState;
    private CaDGameManager.PlayState newPlayeState;
    
    public CaDManagerModifyHandlerEvent(String specifiedOwnerId, HandlerContainer handlerContainer,
                                        CaDGameManager.PlayState oldPlayState, CaDGameManager.PlayState newPlayeState) {
        this.specifiedOwnerId = specifiedOwnerId;
        this.handlerContainer = handlerContainer;
        this.oldPlayState = oldPlayState;
        this.newPlayeState = newPlayeState;
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
    
    public CaDGameManager.PlayState getNewPlayeState() {
        return newPlayeState;
    }
}
