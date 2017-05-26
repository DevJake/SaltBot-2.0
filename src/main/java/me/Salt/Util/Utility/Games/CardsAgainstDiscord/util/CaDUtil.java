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

package me.Salt.Util.Utility.Games.CardsAgainstDiscord.util;

import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.BlackCard;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.WhiteCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CaDUtil {
    private static List<WhiteCard> whiteCards = new ArrayList<>();
    private static List<BlackCard> blackCards = new ArrayList<>();

    {
        whiteCards.addAll(Arrays.asList(
                new WhiteCard("Jason Statham"),
                new WhiteCard("Barely edible cheese slices")
        ));

        blackCards.addAll(Arrays.asList(
                new BlackCard("Question 1", 1),
                new BlackCard("Question 2?", 2)
        ));
    }

    public static List<WhiteCard> getWhiteCards() {
        return whiteCards;
    }

    public static List<BlackCard> getBlackCards() {
        return blackCards;
    }
}
