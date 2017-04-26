package me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Impl;

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.PlayerOverallStats;

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

    public int getRevives() {
        return revives;
    }

    public int getSuicides() {
        return suicides;
    }

    public int getReinforcementsDeployed() {
        return reinforcementsDeployed;
    }

    public int getBarricadedBuilt() {
        return barricadedBuilt;
    }

    public int getStepsMoved() {
        return stepsMoved;
    }

    public int getBulletsFired() {
        return bulletsFired;
    }

    public int getBulletsHit() {
        return bulletsHit;
    }

    public int getHeadshots() {
        return headshots;
    }

    public int getMeleeKills() {
        return meleeKills;
    }

    public int getPenetrationKills() {
        return penetrationKills;
    }

    public int getAssists() {
        return assists;
    }
}
