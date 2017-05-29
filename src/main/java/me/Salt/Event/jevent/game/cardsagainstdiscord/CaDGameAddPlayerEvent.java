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
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.Player;

/**
 * Fired when a player is added to a Cards Against Discord game.
 */
public class CaDGameAddPlayerEvent extends GenericCaDGameEvent {
    /**
     * The {@link CaDGameHandler} instance that the player was added to.
     */
    private CaDGameHandler gameHandler;
    /**
     * The {@link Player} that was added to the CaDGameHandler instance.
     */
    private Player player;
    
    public CaDGameAddPlayerEvent(CaDGameHandler gameHandler, Player player) {
        this.gameHandler = gameHandler;
        this.player = player;
    }
    
    public CaDGameHandler getGameHandler() {
        return gameHandler;
    }
    
    public Player getPlayer() {
        return player;
    }
}
