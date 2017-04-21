package me.Salt.Permissions;

/**
 * Created by 15122390 on 21/04/2017.
 */
public enum Perm {
    //Add perm to bypass Cooldowns. Add per command.
    //Guild perms
    //User perms
    //Global perms

    //All permissions
    /**
     * Access to all available permissions
     */
    ALL_GLOBAL_PERMISSIONS,

    /**
     * Access to all Guild-specific permissions
     */
    ALL_GUILD_PERMISSIONS,

    /**
     * Access to all TextChannel-specific permissions
     */
    ALL_TEXTCHANNEL_PERMISSIONS,

    /**
     * Access to all User-specific permissions
     */
    ALL_USER_PERMISSIONS,

    /**
     * Access to all Cooldown permissions
     */
    ALL_COOLDOWN_PERMISSIONS,

    /**
     * Access to all Command-cooldown permissions
     */
    ALL_COOLDOWN_COMMAND_PERMISSIONS,

    /**
     * Access to all Utility-cooldown permissions
     */
    ALL_COOLDOWN_UTILITY_PERMISSIONS,

    //Cooldowns
    BYPASS_ALL_COMMAND_COOLDOWN,
    BYPASS_ALL_UTILITY_COOLDOWN,

    //Independent Command cooldown bypasses
    COMMAND_BYPASS_HELP_COOLDOWN,
    COMMAND_BYPASS_ISSUE_COOLDOWN,
    COMMAND_BYPASS_PING_COOLDOWN,
    COMMAND_BYPASS_RATES_COOLDOWN,
    COMMAND_BYPASS_REMINDER_COOLDOWN,
    COMMAND_BYPASS_SAY_COOLDOWN,
    COMMAND_BYPASS_STATISTICS_COOLDOWN,

    //Independent Utility cooldown bypasses
    UTILITY_BYPASS_SPAMTRACKER_COOLDOWN,
    UTILITY_BYPASS_TUBEMAPPER_COOLDOWN,

    //Say Command
    COMMAND_SAY_SET_TEXTCHANNEL,
    COMMAND_SAY_USE_MENTION,
    COMMAND_SAY__USE_PRIVATE_MESSAGE,
    //TODO add perms per command

}
