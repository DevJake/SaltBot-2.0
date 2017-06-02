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
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.CaDGameManager;

/**
 * This event is fired when the CaDManager invokes a specified state.
 */
public class CaDManagerInvokeStateEvent extends GenericCaDManagerEvent {
    /**
     * The {@link CaDGameHandler} instance that was passed to the invoke handler.
     */
    private CaDGameHandler gameHandler;
    /**
     * The {@link me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.CaDGameManager.PlayState} of the CaDGameHandler
     * instance that was invoked upon.
     */
    private CaDGameManager.PlayState playState;
    
    public CaDManagerInvokeStateEvent(CaDGameHandler gameHandler, CaDGameManager.PlayState playState) {
        this.gameHandler = gameHandler;
        this.playState = playState;
    }
    
    public CaDGameHandler getGameHandler() {
        return gameHandler;
    }
    
    public CaDGameManager.PlayState getPlayState() {
        return playState;
    }
}
