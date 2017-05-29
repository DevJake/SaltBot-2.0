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

import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.BlackCard;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.CaDGameHandler;

/**
 * This event is fired when a black card is added to the list of previous black cards in a Cards Against Discord game.
 */
public class CaDGameAddPreviousBlackCardEvent extends GenericCaDGameEvent {
    /**
     * The {@link CaDGameHandler} instance that the black card was added to.
     */
    private CaDGameHandler gameHandler;
    /**
     * The {@link BlackCard} that was added to the CaDGameHandler instance.
     */
    private BlackCard blackCard;
    
    public CaDGameAddPreviousBlackCardEvent(CaDGameHandler gameHandler, BlackCard blackCard) {
        this.gameHandler = gameHandler;
        this.blackCard = blackCard;
    }
    
    public CaDGameHandler getGameHandler() {
        return gameHandler;
    }
    
    public BlackCard getBlackCard() {
        return blackCard;
    }
}
