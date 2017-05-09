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

import main.java.me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.PlayerOverallStats;

public class PlayerOverallStatsImpl implements PlayerOverallStats {
    private int revives;
    private int suicides;
    private int reinforcementsDeployed;
    private int barricadedBuilt;
    private int stepsMoved;
    private int bulletsFired;
    private int bulletsHit;
    private int headshots;
    private int meleeKills;
    private int penetrationKills;
    private int assists;

    public PlayerOverallStatsImpl(int revives, int suicides, int reinforcementsDeployed, int barricadedBuilt, int stepsMoved, int bulletsFired, int bulletsHit, int headshots, int meleeKills, int penetrationKills, int assists) {

        this.revives = revives;
        this.suicides = suicides;
        this.reinforcementsDeployed = reinforcementsDeployed;
        this.barricadedBuilt = barricadedBuilt;
        this.stepsMoved = stepsMoved;
        this.bulletsFired = bulletsFired;
        this.bulletsHit = bulletsHit;
        this.headshots = headshots;
        this.meleeKills = meleeKills;
        this.penetrationKills = penetrationKills;
        this.assists = assists;
    }

    @Override
    public String toString() {
        return "PlayerOverallStatsImpl{" +
                "revives=" + revives +
                ", suicides=" + suicides +
                ", reinforcementsDeployed=" + reinforcementsDeployed +
                ", barricadedBuilt=" + barricadedBuilt +
                ", stepsMoved=" + stepsMoved +
                ", bulletsFired=" + bulletsFired +
                ", bulletsHit=" + bulletsHit +
                ", headshots=" + headshots +
                ", meleeKills=" + meleeKills +
                ", penetrationKills=" + penetrationKills +
                ", assists=" + assists +
                '}';
    }

    public int getRevives() {
        return revives;
    }

    public int getSuicides() {
        return suicides;
    }

    @Override
    public int getReinforcementsDeployed() {
        return reinforcementsDeployed;
    }

    @Override
    public int getBarricadedBuilt() {
        return barricadedBuilt;
    }

    @Override
    public int getStepsMoved() {
        return stepsMoved;
    }

    @Override
    public int getBulletsFired() {
        return bulletsFired;
    }

    @Override
    public int getBulletsHit() {
        return bulletsHit;
    }

    @Override
    public int getHeadshots() {
        return headshots;
    }

    @Override
    public int getMeleeKills() {
        return meleeKills;
    }

    @Override
    public int getPenetrationKills() {
        return penetrationKills;
    }

    @Override
    public int getAssists() {
        return assists;
    }
}
