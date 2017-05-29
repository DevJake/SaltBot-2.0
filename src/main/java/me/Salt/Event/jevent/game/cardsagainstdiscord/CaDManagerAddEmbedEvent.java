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
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.ResponseContainer;

/**
 * This event is fired when a Cards Against Discord message is registered to the CaDGameManager.
 */
public class CaDManagerAddEmbedEvent extends GenericCaDManagerEvent {
    /**
     * The {@link CaDGameHandler} instance that the embedded message is associated with.
     */
    private CaDGameHandler gameHandler;
    /**
     * The old {@link me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.ResponseContainer.ResponseExpector} of the
     * CaDGameHandler instance.
     */
    private ResponseContainer.ResponseExpector oldResponseExpector;
    /**
     * The new {@link me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.ResponseContainer.ResponseExpector} of the
     * CaDGameHandler instance.
     */
    private ResponseContainer.ResponseExpector newResponseExpector;
    /**
     * The message ID of the newly-added embed.
     */
    private String messageId;
    
    public CaDManagerAddEmbedEvent(CaDGameHandler gameHandler, ResponseContainer.ResponseExpector oldResponseExpector,
                                   ResponseContainer.ResponseExpector newResponseExpector, String messageId) {
        this.gameHandler = gameHandler;
        this.oldResponseExpector = oldResponseExpector;
        this.newResponseExpector = newResponseExpector;
        this.messageId = messageId;
    }
    
    public CaDGameHandler getGameHandler() {
        return gameHandler;
    }
    
    public ResponseContainer.ResponseExpector getOldResponseExpector() {
        return oldResponseExpector;
    }
    
    public ResponseContainer.ResponseExpector getNewResponseExpector() {
        return newResponseExpector;
    }
    
    public String getMessageId() {
        return messageId;
    }
}
