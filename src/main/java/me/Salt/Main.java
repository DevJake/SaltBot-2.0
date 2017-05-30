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
package me.Salt;

import me.Salt.Command.CommandContainer;
import me.Salt.Command.CommandDescriptionBuilder;
import me.Salt.Command.Commands.Administrator.EvalCommand;
import me.Salt.Command.Commands.Administrator.PermissionCommand;
import me.Salt.Command.Commands.Debugging.SayCommand;
import me.Salt.Command.Commands.Fun.CardsAgainstDiscord.*;
import me.Salt.Command.Commands.Fun.CatCommand;
import me.Salt.Command.Commands.Fun.EmojiTextCommand;
import me.Salt.Command.Commands.Informative.*;
import me.Salt.Command.Commands.Utility.IssueCommand;
import me.Salt.Command.Commands.Utility.R6GameStatsCommand;
import me.Salt.Command.Commands.Utility.ReminderCommand;
import me.Salt.Event.EventListener;
import me.Salt.Event.jevent.SaltStartupEvent;
import me.Salt.Event.util.EventInitiator;
import me.Salt.Exception.Generic.DuplicateDataException;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Permissions.Perm;
import me.Salt.Permissions.Permission;
import me.Salt.Permissions.PermissionBuilder;
import me.Salt.SaltAPI.ConfigurationBuilder;
import me.Salt.SaltAPI.IConfiguration;
import me.Salt.Util.Cooldown;
import me.Salt.Util.Language.LangCode;
import me.Salt.Util.Language.LangString;
import me.Salt.Util.Language.LanguageBuilder;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.CaDEventListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * SaltBot 2.0 -- The original, rebuilt!
 */
public class Main {
    private static final long startupTime = System.currentTimeMillis(); //Final, to ensure it cannot be changed
    public static JDA jda;
    public static IConfiguration salt;
    
    public static void main(String[] args)
            throws LoginException, InterruptedException, RateLimitedException, MissingDataException,
            DuplicateDataException, IOException {
        EventInitiator.fire(new SaltStartupEvent(startupTime));
        // TODO: 26/05/2017 Redo file reading system. Move to own class
        System.out.println(ClassLoader.getSystemClassLoader().getResource("SaltBot.salt").getFile());
        Scanner scanner = new Scanner(new File(Main.class.getClassLoader().getResource("SaltBot.salt").getFile()));
        while (scanner.hasNextLine()) {
            String n = scanner.nextLine();
            System.out.println(n + "\n");
            if (n.startsWith("Bot-Token:")) {
                System.out.println(n.substring(n.indexOf("Bot-Token:") + String.valueOf("Bot-Token:").length() + 1));
                jda = new JDABuilder(AccountType.BOT).setToken(
                        n.substring(n.indexOf("Bot-Token:") + String.valueOf("Bot-Token:").length() + 1))
                                                     .addEventListener(new EventListener())
                                                     .addEventListener(new CaDEventListener())
                                                     .buildBlocking(); //TODO Read bot token from config, and generate new token to
                // prevent others from using the bot
                // with this token.
                break;
            }
        }
        scanner.close();
        //TODO improve above method. Currently a temporary fix to an exploit.
        //TODO also, change token, as a failsafe.
        //TODO test LogUtil's cache-sorting algorithm.
        salt = new ConfigurationBuilder(".").setDebugMode(false)
                                            .setStartupTime(startupTime)
                                            .setName("SaltBot-2.0")
                                            .setEmbedColour(Color.BLUE)
                                            .setDefaultLangCode(LangCode.en_GB)
                                            .addTeamMember(jda.getUserById("112633500447838208"),
                                                    Arrays.asList(IConfiguration.Authority.DEVELOPER,
                                                            IConfiguration.Authority.OWNER,
                                                            IConfiguration.Authority.TESTER))
                                            .registerCommand("help", new HelpCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAlias("h")
                                                                                   .addAuthor(jda.getUserById(
                                                                                           "112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Provides help on any aspect of any command")
                                                                                   .setName("Help")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("ping", new PingCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAlias("p")
                                                                                   .addAuthor(jda.getUserById(
                                                                                           "112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Request a ping from the bot")
                                                                                   .setName("Ping")
                                                                                   .setCooldown(new Cooldown(15,
                                                                                           TimeUnit.SECONDS))
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("rates", new RatesCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAlias(
                                                            "r") //TODO get aliases working
                                                                                   .addAuthor(jda.getUserById(
                                                                                           "112633500447838208"))
                                                                                   .setComplete(true)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Request a list of rate limits applicable to Discord bots")
                                                                                   .setName("Rate-limits")
                                                                                   .setCooldown(new Cooldown(30,
                                                                                           TimeUnit.SECONDS))
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("issue", new IssueCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAlias("reportissue")
                                                                                   .addAlias("addissue")
                                                                                   .addAlias("issues")
                                                                                   .addAuthor(jda.getUserById(
                                                                                           "112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Provides help on any aspect of any command")
                                                                                   .setName("Issue Tracker")
                                                                                   .setCooldown(new Cooldown(1,
                                                                                           TimeUnit.MINUTES))
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("stats", new StatisticsCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAlias("statistics")
                                                                                   .addAuthor(jda.getUserById(
                                                                                           "112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Provides details of current bot statistics")
                                                                                   .setName("Statistics Viewer")
                                                                                   .setCooldown(new Cooldown(20,
                                                                                           TimeUnit.SECONDS))
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("say", new SayCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAlias("echo")
                                                                                   .addAuthor(jda.getUserById(
                                                                                           "112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Echoes any arguments given")
                                                                                   .setName("Say")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("remind", new ReminderCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAlias("remindme")
                                                                                   .addAuthor(jda.getUserById(
                                                                                           "112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setCooldown(new Cooldown(5,
                                                                                           TimeUnit.MINUTES))
                                                                                   .setDescription(
                                                                                           "Reminds the individual about something at a specified date")
                                                                                   .setName("Scheduled Reminder")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("perms", new PermissionCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAlias("permissions")
                                                                                   .addAlias("permission")
                                                                                   .addAuthor(jda.getUserById(
                                                                                           "112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Allows an individual to manage the permissions system for themselves, their textchannel, guild, or globally")
                                                                                   .setName("Permissions Manager")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("guildinfo", new GuildInfoCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAlias("gi")
                                                                                   .addAlias(
                                                                                           "guild") //TODO make sure aliases do not intersect with other aliases
                                                                                   .addAlias("ginfo")
                                                                                   .addAuthor(jda.getUserById(
                                                                                           "112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Allows an individual to view details about the current guild")
                                                                                   .setName("Guild Information")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("channelinfo", new TextChannelInfoCommand(
                                                    new CommandContainer(new CommandDescriptionBuilder().addAlias("ci")
                                                                                                        .addAlias(
                                                                                                                "channel")
                                                                                                        .addAlias(
                                                                                                                "cinfo")
                                                                                                        .addAuthor(
                                                                                                                jda.getUserById(
                                                                                                                        "112633500447838208"))
                                                                                                        .setComplete(
                                                                                                                false)
                                                                                                        .setDeprecated(
                                                                                                                false)
                                                                                                        .setDescription(
                                                                                                                "Allows an individual to view details about the current TextChannel")
                                                                                                        .setName(
                                                                                                                "Channel Information")
                                                                                                        .build(),
                                                            Arrays.asList(
                                                                    CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("uptime", new UptimeCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAuthor(
                                                            jda.getUserById("112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Calculates and displays the current uptime of the bot")
                                                                                   .setName("Uptime")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("profile", new ProfileInfoCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAuthor(
                                                            jda.getUserById("112633500447838208"))
                                                                                   .addAlias("pinfo")
                                                                                   .addAlias("profileinfo")
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Displays info about a user's profile")
                                                                                   .setName("Profile Info")
                                                                                   .addRequiredPermissions(
                                                                                           Perm.COMMAND_PROFILE_USE)
                                                                                   .addRequiredPermissions(
                                                                                           Perm.COMMAND_PROFILE_SET_USER)
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("cat", new CatCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAuthor(
                                                            jda.getUserById("112633500447838208"))
                                                                                   .addAlias("cats")
                                                                                   .addAlias("kitty")
                                                                                   .addAlias("cade")
                                                                                   .addAlias("kitten")
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription("Cats!! ;)")
                                                                                   .setName("Kitties <3")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("eval", new EvalCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAlias("evaluate")
                                                                                   .addAlias("resolve")
                                                                                   .addAuthor(jda.getUserById(
                                                                                           "112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription("Testing!")
                                                                                   .setName("Testing Command")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("r6", new R6GameStatsCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAuthor(
                                                            jda.getUserById("112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Rainbow 6 stat grabbing")
                                                                                   .setName("Rainbow6 Stats Command")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("etext", new EmojiTextCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAuthor(
                                                            jda.getUserById("112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Emoji Text... because why not!?")
                                                                                   .setName("Emoji Text Command")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("cadCreate", new CaDCreateGameCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAuthor(
                                                            jda.getUserById("112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Create a new Cards Against Discord game")
                                                                                   .setName(
                                                                                           "CardsAgainstDiscord create command")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("cadAdd", new CaDAddPlayerCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAuthor(
                                                            jda.getUserById("112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Add a player to an existing CardsAgainstDiscord game")
                                                                                   .setName(
                                                                                           "CardsAgainstDiscord add player command")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("cadStart", new CaDStartGameCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAuthor(
                                                            jda.getUserById("112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Start an existing CardsAgainstDiscord game")
                                                                                   .setName(
                                                                                           "CardsAgainstDiscord start game command")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("cadView", new CaDViewGamesCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAuthor(
                                                            jda.getUserById("112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "View a list of all existing CardsAgainstDiscord sessions")
                                                                                   .setName(
                                                                                           "CardsAgainstDiscord view sessions command")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("cadRemove", new CaDRemovePlayerCommand(
                                                    new CommandContainer(new CommandDescriptionBuilder().addAuthor(
                                                            jda.getUserById("112633500447838208"))
                                                                                                        .setComplete(
                                                                                                                false)
                                                                                                        .setDeprecated(
                                                                                                                false)
                                                                                                        .setDescription(
                                                                                                                "Remove a player from your current CardsAgainstDiscord session")
                                                                                                        .setName(
                                                                                                                "CardsAgainstDiscord remove player command")
                                                                                                        .build(),
                                                            Arrays.asList(
                                                                    CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .registerCommand("cadInfo", new CaDInfoCommand(new CommandContainer(
                                                    new CommandDescriptionBuilder().addAuthor(
                                                            jda.getUserById("112633500447838208"))
                                                                                   .setComplete(false)
                                                                                   .setDeprecated(false)
                                                                                   .setDescription(
                                                                                           "Get information about your personal CardsAgainstDiscord session")
                                                                                   .setName(
                                                                                           "CardsAgainstDiscord view info command")
                                                                                   .build(),
                                                    Arrays.asList(CommandContainer.JEventType.GENERIC_MESSAGE))))
                                            .build();
        // TODO: 27/05/2017 system for giving commands optional categories, and subcategories, such as .setCategory(xyz).addSubCategory(xyz). Allow multiple subcategories (???)
        Main.salt.getPermissionHandler()
                 .registerPermission(Permission.Range.ALL,
                         new PermissionBuilder().setPermEnum(Perm.ALL_GLOBAL_PERMISSIONS)
                                                .setPermission("Salt")
                                                .setDescription("The parent permission of all possible permissions")
                                                .build())
                 .registerPermission(Permission.Range.ALL,
                         new PermissionBuilder().setPermEnum(Perm.ALL_COOLDOWN_PERMISSIONS)
                                                .setPermission("Salt.Cooldown")
                                                .setDescription("The parent permission of all Cooldown permissions")
                                                .build())
                 .registerPermission(Permission.Range.ALL,
                         new PermissionBuilder().setPermEnum(Perm.ALL_COOLDOWN_COMMAND_PERMISSIONS)
                                                .setPermission("Salt.Cooldown.Command")
                                                .setDescription(
                                                        "The parent permission of all Cooldown permissions for commands")
                                                .build())
                 .registerPermission(Permission.Range.ALL,
                         new PermissionBuilder().setPermEnum(Perm.BYPASS_ALL_COMMAND_COOLDOWN)
                                                .setPermission("Salt.Cooldown.Command.Bypass")
                                                .setDescription("Allows for a Command Cooldown to be bypassed")
                                                .build())
                 .registerPermission(Permission.Range.ALL,
                         new PermissionBuilder().setPermEnum(Perm.COMMAND_BYPASS_HELP_COOLDOWN)
                                                .setPermission("Salt.Cooldown.Command.Bypass.Help")
                                                .setDescription("Allows bypassing of the cooldown for the HelpCommand")
                                                .build());
        salt.getLanguageHandler()
            .addLanguage(new LanguageBuilder().setCode(LangCode.custom_lang)
                                              .addString(LangString.LACKING_PERMISSION, "uh-oh no permyoes")
                                              .addString(LangString.LOGGING_INFO, "info my bro!")
                                              .build());
        salt.getLanguageHandler()
            .addLanguage(new LanguageBuilder().setCode(LangCode.en_GB)
                                              .addString(LangString.LACKING_PERMISSION, "lacking permission")
                                              .addString(LangString.LOGGING_INFO, "information")
                                              .addString(LangString.LOGGING_CONFIG, "configuration")
                                              .addString(LangString.LOGGING_DEBUG, "debugging")
                                              .addString(LangString.LOGGING_FATAL, "fatal")
                                              .addString(LangString.LOGGING_SEVERE, "severe")
                                              .addString(LangString.LOGGING_WARNING, "warning")
                                              .build());
        Main.salt.init();
        //TODO add unit tests
        //TODO add JavaDoc comments where possible
        //TODO allow each guild to establish its own TimeZone. Then ensure all times and dates in the guild are adjusted to suit their times. Also attach the timezone name (such as UTC) after all times.
        //TODO use https://github.com/google/gson to parse Json
    }
}
