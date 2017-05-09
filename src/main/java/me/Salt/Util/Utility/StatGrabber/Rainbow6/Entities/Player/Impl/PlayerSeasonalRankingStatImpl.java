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

package main.java.me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.Impl;

import main.java.me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.PlayerSeasonalRankingStat;

public class PlayerSeasonalRankingStatImpl implements PlayerSeasonalRankingStat {
    private int rating;
    private int nextRating;
    private int previousRating;
    private int mean;
    private int stdev;
    private int rank;

    public PlayerSeasonalRankingStatImpl(int rating, int nextRating, int previousRating, int mean, int stdev, int rank) {
        this.rating = rating;
        this.nextRating = nextRating;
        this.previousRating = previousRating;
        this.mean = mean;
        this.stdev = stdev;
        this.rank = rank;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public int getNextRating() {
        return nextRating;
    }

    @Override
    public int getPreviousRating() {
        return previousRating;
    }

    @Override
    public int getMean() {
        return mean;
    }

    @Override
    public int getStdev() {
        return stdev;
    }

    @Override
    public int getRank() {
        return rank;
    }
}
