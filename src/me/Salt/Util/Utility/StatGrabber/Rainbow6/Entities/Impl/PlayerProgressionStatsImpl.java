package me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Impl;

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.PlayerProgressionStats;

public class PlayerProgressionStatsImpl implements PlayerProgressionStats {
    private int level;
    private int levelUpXp;

    public PlayerProgressionStatsImpl(int level, int levelUpXp) {

        this.level = level;
        this.levelUpXp = levelUpXp;
    }

    public int getLevel() {
        return level;
    }

    public int getLevelUpXp() {
        return levelUpXp;
    }
}
