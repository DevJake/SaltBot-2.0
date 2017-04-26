package me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Impl;

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.PlayerGamemodeStats;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.PlayerOverallStats;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.PlayerProgressionStats;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.PlayerStats;

public class PlayerStatsImpl implements PlayerStats {
    private PlayerGamemodeStats ranked;
    private PlayerGamemodeStats casual;
    private PlayerOverallStats overall;
    private PlayerProgressionStats progression;

    public PlayerStatsImpl(PlayerGamemodeStats ranked, PlayerGamemodeStats casual, PlayerOverallStats overall, PlayerProgressionStats progression) {
        this.ranked = ranked;
        this.casual = casual;
        this.overall = overall;
        this.progression = progression;
    }

    public PlayerGamemodeStats getRanked() {
        return ranked;
    }

    public PlayerGamemodeStats getCasual() {
        return casual;
    }

    public PlayerOverallStats getOverall() {
        return overall;
    }

    public PlayerProgressionStats getProgression() {
        return progression;
    }
}
