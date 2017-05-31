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

public class CardSubmission {
    private WhiteCard whiteCard;
    private Player player;
    
    public CardSubmission(WhiteCard whiteCard, Player player) {
        this.whiteCard = whiteCard;
        this.player = player;
    }
    
    public WhiteCard getWhiteCard() {
        return whiteCard;
    }
    
    public Player getPlayer() {
        return player;
    }
}
