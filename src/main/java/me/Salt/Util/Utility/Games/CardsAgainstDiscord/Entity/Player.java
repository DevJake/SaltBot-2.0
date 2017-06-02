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
package me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity;

import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as a container for players of the Cards Against Discord game.
 */
public class Player {
    /**
     * A list of this player's {@link WhiteCard}s.
     */
    private List<WhiteCard> cards = new ArrayList<>();
    /**
     * An integer representing the amount of rounds lost overall.
     */
    private int roundsLost = 0;
    /**
     * An integer representing the amount of rounds won overall.
     */
    private int roundsWon = 0;
    /**
     * The {@link User} that this class references to.
     */
    private User user;
    
    public Player(User user) {
        this.user = user;
    }
    
    public List<WhiteCard> getCards() {
        return cards;
    }
    
    public void addCard(WhiteCard card) {
        this.cards.add(card);
    }
    
    public void incrementRoundsWon() {
        this.roundsWon += 1;
    }
    
    public void incrementRoundsLost() {
        this.roundsLost += 1;
    }
    
    public User getUser() {
        return user;
    }
    
    public int getRoundsLost() {
        return roundsLost;
    }
    
    public int getRoundsWon() {
        return roundsWon;
    }
    // TODO: 26/05/2017 calculate total rounds from sum of roundsLost and roundsWon
}
