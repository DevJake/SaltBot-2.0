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

import me.Salt.Event.jevent.game.cardsagainstdiscord.CaDGameAddPlayerEvent;
import me.Salt.Event.jevent.game.cardsagainstdiscord.CaDGameAddPreviousBlackCardEvent;
import me.Salt.Event.jevent.game.cardsagainstdiscord.CaDGameRemovePlayerEvent;
import me.Salt.Event.jevent.game.cardsagainstdiscord.CaDGameUpdateActiveEvent;
import me.Salt.Event.util.EventInitiator;
import me.Salt.Util.Utility.Games.Game;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class acts as a container for a Cards Against Discord game session.
 */
public class CaDGameHandler implements Game {
    /**
     * A List of {@link Player}s registered to this game.
     */
    private List<Player> players = new ArrayList<>();
    /**
     * An integer representing the score required by any player to win the game.
     */
    private int winningScore; //Score required by anyone to win the game
    /**
     * An integer representing how many cards each player should consistently have.
     */
    private int cardCount; //How many cards should each person get?
    /**
     * A List containing all previously-used {@link BlackCard}s. This List serves to prevent the rare chance of a
     * game using duplicate black cards.
     */
    private List<BlackCard> previousBlackCards = new ArrayList<>();
    /**
     * The {@link Player} who owns the game.
     */
    private Player owner;
    /**
     * A boolean representing if the game is currently in progress.
     */
    private boolean active = false; //is the game running?
    /**
     * The {@link Player} who is the current Card Czar.
     */
    private Player cardCzar; //The current Card Czar of the game
    
    public CaDGameHandler(int winningScore, int cardCount, Player owner) {
        this.winningScore = winningScore;
        this.cardCount = cardCount;
        this.owner = owner;
    }
    
    public Player getCardCzar() {
        return cardCzar;
    }
    
    public void setCardCzar(Player cardCzar) {
        this.cardCzar = cardCzar;
    }
    
    public Player getOwner() {
        return owner;
    }
    
    /**
     * @return A List of all {@link Player}s within the game, <b>excluding</b> the game's owner.
     */
    public List<Player> getPlayers() {
        return players;
    }
    
    /**
     * @return A List of all {@link Player}s within the game, <b>including</b> the game's owner.
     */
    public List<Player> getAllPlayers() {
        List<Player> p = new ArrayList<>();
        p.addAll(players);
        p.add(owner);
        return p;
    }
    
    /**
     * @return A List of all {@link Player}s within the game, <b>excluding</b> the game's owner and the current Card
     * Czar.
     */
    public List<Player> getPlayersNonCzar() {
        List<Player> p = new ArrayList<>();
        p.addAll(players);
        p.remove(cardCzar);
        return p;
    }
    
    /**
     * @return A List of all {@link Player}s within the game, <b>including</b> the game's owner and <b>excluding</b>
     * the current Card Czar.
     */
    public List<Player> getAllPlayersNonCzar() {
        List<Player> p = new ArrayList<>();
        p.addAll(players);
        p.add(owner);
        p.remove(cardCzar);
        return p;
    }
    
    public int getWinningScore() {
        return winningScore;
    }
    
    public int getCardCount() {
        return cardCount;
    }
    
    public boolean addPlayer(Player player) { //Boolean represents if the player already exists
        if (containsPlayer(player.getUser())) return true;
        else {
            this.players.add(player);
            EventInitiator.fire(new CaDGameAddPlayerEvent(this, player));
            return false;
        }
    }
    
    public boolean containsPlayer(User player) { //Boolean represents if the player already exists
        if (this.getAllPlayers()
                .stream()
                .filter(player1 -> Objects.equals(player1.getUser().getId(), player.getId()))
                .count() >= 1) return true;
        else return false;
    }
    
    public void removePlayer(Player player) {
        if (this.players.contains(player)) {
            this.players.remove(player);
            EventInitiator.fire(new CaDGameRemovePlayerEvent(this, player));
        }
    }
    
    public void addPreviousBlackCard(BlackCard blackCard) {
        if (!this.previousBlackCards.contains(blackCard)) {
            this.previousBlackCards.add(blackCard);
            EventInitiator.fire(new CaDGameAddPreviousBlackCardEvent(this, blackCard));
        }
    }
    
    public List<BlackCard> getPreviousBlackCards() {
        return previousBlackCards;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        EventInitiator.fire(new CaDGameUpdateActiveEvent(this, this.active, active));
        this.active = active;
    }
    // TODO: 26/05/2017 ensure cardCount is between 0 and 10 (1-9)
}
