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

/**
 * This class represents a Card; neither a Black nor White card.
 *
 * @see BlackCard
 * @see WhiteCard
 */
public class Card {
    /**
     * The text present on the card.
     */
    private String text;
    
    public Card(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
}
