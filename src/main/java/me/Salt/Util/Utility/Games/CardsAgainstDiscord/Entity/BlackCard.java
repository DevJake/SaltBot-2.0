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
 * This class stores details exclusively present to a Black card, for Cards Against Discord.
 */
public class BlackCard extends Card {
    /**
     * An integer representing how many blank fields are present. This therefore also represents how many corresponding
     * white cards are expected.
     */
    private int blankFields;
    
    public BlackCard(String text, int blankFields) {
        super(text);
        this.blankFields = blankFields;
        // TODO: 26/05/2017 calculate amount of blank fields. Throw exception if missing fields.
    }
    
    public int getBlankFields() {
        return blankFields;
    }
}
