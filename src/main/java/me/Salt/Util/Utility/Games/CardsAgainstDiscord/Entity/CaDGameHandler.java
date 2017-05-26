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

import me.Salt.Util.Utility.Games.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CaDGameHandler implements Game {
    private List<Player> players = new ArrayList<>();
    private int winningScore; //Score required by anyone to win the game
    private int cardCount; //How many cards should each person get?
    private List<BlackCard> previousBlackCards = new ArrayList<>();

    public CaDGameHandler(int winningScore, int cardCount) {
        this.winningScore = winningScore;
        this.cardCount = cardCount;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getWinningScore() {
        return winningScore;
    }

    public int getCardCount() {
        return cardCount;
    }

    public boolean addPlayer(Player player) { //Boolean represents if the player already exists
        if (this.getPlayers().stream().filter(player1 -> player1.getUser() == player.getUser()).count() >= 1) return true;
        else {
            this.players.add(player);
            return false;
        }
    }

    public void removePlayer(Player player) {
        if (this.players.contains(player)) this.players.remove(player);
    }

    public void addPreviousBlackCard(BlackCard blackCard) {
        if (!this.previousBlackCards.contains(blackCard)) this.previousBlackCards.add(blackCard);
    }
    // TODO: 26/05/2017 ensure cardCount is between 0 and 10 (1-9)
}
