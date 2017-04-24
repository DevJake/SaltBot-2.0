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

package me.Salt.Handler;

import me.Salt.Command.CommandContainer;
import me.Salt.Command.CommandDescriptionBuilder;
import me.Salt.Command.Commands.Fun.CatCommand;
import me.Salt.Command.Commands.Informative.*;
import me.Salt.Command.Commands.IssueCommand;
import me.Salt.Command.Commands.Administrator.PermissionCommand;
import me.Salt.Command.Commands.Utility.ReminderCommand;
import me.Salt.Command.Commands.Debugging.SayCommand;
import me.Salt.Event.EventDistributor;
import me.Salt.Exception.Generic.DuplicateDataException;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Permissions.Perm;
import me.Salt.Permissions.Permission;
import me.Salt.Permissions.PermissionBuilder;
import me.Salt.SaltAPI.ConfigurationBuilder;
import me.Salt.SaltAPI.IConfiguration;
import me.Salt.Util.Cooldown;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * SaltBot 2.0 -- The original, rebuilt!
 */
public class Main {
    private static final long n = System.currentTimeMillis(); //Final, to ensure it cannot be changed
    public static JDA jda;
    public static IConfiguration salt;

    public static void main(String[] args) throws LoginException, InterruptedException, RateLimitedException, MissingDataException, DuplicateDataException, IOException {
        jda = new JDABuilder(AccountType.BOT).setToken("MjQ2MzA5NDI1OTAyNjQ5MzQ1.C9uEqA.lj7dGD3MnPJKJVwwrc6buxx-RRs").addEventListener(new EventDistributor()).buildBlocking(); //TODO Read bot token from config, and generate new token to prevent others from using the bot with this token.
        //TODO improve above method. Currently a temporary fix to an exploit.
        //TODO also, change token, as a failsafe.

        salt = new ConfigurationBuilder(".")
                .setDebugMode(false)
                .setStartupTime(n)
                .setName("SaltBot-2.0")
                .setEmbedColour(Color.BLUE)
                .addTeamMember(jda.getUserById("112633500447838208"),
                        Arrays.asList(IConfiguration.Authority.DEVELOPER, IConfiguration.Authority.OWNER, IConfiguration.Authority.TESTER))
                .registerCommand("help", new HelpCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAlias("h")
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setDescription("Provides help on any aspect of any command")
                                        .setName("Help")
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE))))
                .registerCommand("ping", new PingCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAlias("p")
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setDescription("Request a ping from the bot")
                                        .setName("Ping")
                                        .setCooldown(new Cooldown(15, TimeUnit.SECONDS))
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE)
                        )))
                .registerCommand("rates", new RatesCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAlias("r") //TODO get aliases working
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .setComplete(true)
                                        .setDeprecated(false)
                                        .setDescription("Request a list of rate limits applicable to Discord bots")
                                        .setName("Rate-limits")
                                        .setCooldown(new Cooldown(30, TimeUnit.SECONDS))
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE)
                        )))
                .registerCommand("issue", new IssueCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAlias("reportissue")
                                        .addAlias("addissue")
                                        .addAlias("issues")
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setDescription("Provides help on any aspect of any command")
                                        .setName("Issue Tracker")
                                        .setCooldown(new Cooldown(1, TimeUnit.MINUTES))
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE))))
                .registerCommand("stats", new StatisticsCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAlias("statistics")
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setDescription("Provides details of current bot statistics")
                                        .setName("Statistics Viewer")
                                        .setCooldown(new Cooldown(20, TimeUnit.SECONDS))
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE))))
                .registerCommand("say", new SayCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAlias("echo")
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setDescription("Echoes any arguments given")
                                        .setName("Say")
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE))))
                .registerCommand("remind", new ReminderCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAlias("remindme")
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setCooldown(new Cooldown(5, TimeUnit.MINUTES))
                                        .setDescription("Reminds the individual about something at a specified date")
                                        .setName("Scheduled Reminder")
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE))))
                .registerCommand("perms", new PermissionCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAlias("permissions")
                                        .addAlias("permission")
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setDescription("Allows an individual to manage the permissions system for themselves, their textchannel, guild, or globally")
                                        .setName("Permissions Manager")
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE))))
                .registerCommand("guildinfo", new GuildInfoCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAlias("gi")
                                        .addAlias("guild") //TODO make sure aliases do not intersect with other aliases
                                        .addAlias("ginfo")
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setDescription("Allows an individual to view details about the current guild")
                                        .setName("Guild Information")
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE))))
                .registerCommand("channelinfo", new TextChannelInfoCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAlias("ci")
                                        .addAlias("channel")
                                        .addAlias("cinfo")
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setDescription("Allows an individual to view details about the current TextChannel")
                                        .setName("Channel Information")
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE))))
                .registerCommand("uptime", new UptimeCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setDescription("Calculates and displays the current uptime of the bot")
                                        .setName("Uptime")
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE))))
                .registerCommand("profile", new ProfileInfoCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .addAlias("pinfo")
                                        .addAlias("profileinfo")
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setDescription("Displays info about a user's profile")
                                        .setName("Profile Info")
                                        .addRequiredPermissions(Perm.COMMAND_PROFILE_USE)
                                        .addRequiredPermissions(Perm.COMMAND_PROFILE_SET_USER)
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE))))
                .registerCommand("cat", new CatCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .addAlias("cats")
                                        .addAlias("kitty")
                                        .addAlias("cade")
                                        .addAlias("kitten")
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setDescription("Cats!! ;)")
                                        .setName("Kitties <3")
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE))))
                .build();

        Main.salt.getPermissionHandler()
                .registerPermission(
                        Permission.Range.ALL,
                        new PermissionBuilder()
                                .setPermEnum(Perm.ALL_GLOBAL_PERMISSIONS)
                                .setPermission("Salt")
                                .setDescription("The parent permission of all possible permissions")
                                .build())
                .registerPermission(
                        Permission.Range.ALL,
                        new PermissionBuilder()
                                .setPermEnum(Perm.ALL_COOLDOWN_PERMISSIONS)
                                .setPermission("Salt.Cooldown")
                                .setDescription("The parent permission of all Cooldown permissions")
                                .build())
                .registerPermission(
                        Permission.Range.ALL,
                        new PermissionBuilder()
                                .setPermEnum(Perm.ALL_COOLDOWN_COMMAND_PERMISSIONS)
                                .setPermission("Salt.Cooldown.Command")
                                .setDescription("The parent permission of all Cooldown permissions for commands")
                                .build())
                .registerPermission(
                        Permission.Range.ALL,
                        new PermissionBuilder()
                                .setPermEnum(Perm.BYPASS_ALL_COMMAND_COOLDOWN)
                                .setPermission("Salt.Cooldown.Command.Bypass")
                                .setDescription("Allows for a Command Cooldown to be bypassed")
                                .build())
                .registerPermission(
                        Permission.Range.ALL,
                        new PermissionBuilder()
                                .setPermEnum(Perm.COMMAND_BYPASS_HELP_COOLDOWN)
                                .setPermission("Salt.Cooldown.Command.Bypass.Help")
                                .setDescription("Allows bypassing of the cooldown for the HelpCommand")
                                .build());

        Main.salt.init();

        //TODO add unit tests
        //TODO add JavaDoc comments where possible
        //TODO allow each guild to establish its own TimeZone. Then ensure all times and dates in the guild are adjusted to suit their times. Also attach the timezone name (such as UTC) after all times.
        //TODO use https://github.com/google/gson to parse Json
    }
}
