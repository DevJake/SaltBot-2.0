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

import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as a container for a single round of the Cards Against Discord game.
 */
public class Round {
    /**
     * The current {@link BlackCard} for this round.
     */
    private BlackCard question;
    /**
     * The {@link Player} who is the Card Czar for this round.
     */
    private Player cardCzar;
    private List<CardSubmission> cardSubmissions = new ArrayList<>();
    
    // TODO: 30/05/2017 Add fields for winning card and winning player. This allows for us to go back through a game's history
    public Round(BlackCard question, Player cardCzar) {
        this.question = question;
        this.cardCzar = cardCzar;
    }
    
    public List<CardSubmission> getCardSubmissions() {
        return cardSubmissions;
    }
    
    public void addCardSubmission(CardSubmission submission) {
        this.cardSubmissions.add(submission);
    }
    
    public BlackCard getQuestion() {
        return question;
    }
    
    public Player getCardCzar() {
        return cardCzar;
    }
}
