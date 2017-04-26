package me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Impl;

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.PlayerStats;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.R6Player;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Util.Platform;
import net.dv8tion.jda.core.entities.User;

import java.time.LocalDateTime;

public class R6PlayerImpl implements R6Player {
    private String username;
    private Platform platform;
    private String ubisoft_id;
    private LocalDateTime indexed_at;
    private LocalDateTime update_at;
    private PlayerStats stats;
    private User tiedUser; //TODO look into tying an account to a user
}
