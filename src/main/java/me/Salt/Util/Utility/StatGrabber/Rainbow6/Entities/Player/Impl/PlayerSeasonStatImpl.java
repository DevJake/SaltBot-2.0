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

package me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.Impl;

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.PlayerSeasonStat;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.PlayerSeasonalRankingStat;

public class PlayerSeasonStatImpl implements PlayerSeasonStat {
    private int wins;
    private int losses;
    private int abandons;
    private int season;
    private String region;
    private PlayerSeasonalRankingStat ranking;

    public PlayerSeasonStatImpl(int wins, int losses, int abandons, int season, String region, PlayerSeasonalRankingStat ranking) {

        this.wins = wins;
        this.losses = losses;
        this.abandons = abandons;
        this.season = season;
        this.region = region;
        this.ranking = ranking;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public int getLosses() {
        return losses;
    }

    @Override
    public int getAbandons() {
        return abandons;
    }

    @Override
    public int getSeason() {
        return season;
    }

    @Override
    public String getRegion() {
        return region;
    }

    @Override
    public PlayerSeasonalRankingStat getRanking() {
        return ranking;
    }
}
