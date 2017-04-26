package me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Impl;

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.PlayerGamemodeStats;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Util.Gamemode;

public class PlayerGamemodeStatsImpl implements PlayerGamemodeStats {
    private Gamemode gamemode;
    private boolean hasPlayed;
    private int wins;
    private int losses;
    private int kills;
    private int deaths;
    private int playtime;

    public PlayerGamemodeStatsImpl(Gamemode gamemode, boolean hasPlayed, int wins, int losses, int kills, int deaths, int playtime) {

        this.gamemode = gamemode;
        this.hasPlayed = hasPlayed;
        this.wins = wins;
        this.losses = losses;
        this.kills = kills;
        this.deaths = deaths;
        this.playtime = playtime;
    }

    public Gamemode getGamemode() {
        return gamemode;
    }

    public boolean isHasPlayed() {
        return hasPlayed;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getPlaytime() {
        return playtime;
    }


}
