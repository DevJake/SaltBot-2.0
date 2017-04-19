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
import me.Salt.Command.Commands.*;
import me.Salt.Event.EventDistributor;
import me.Salt.Exception.DuplicateDataException;
import me.Salt.Exception.MissingDataException;
import me.Salt.SaltAPI.ConfigurationBuilder;
import me.Salt.SaltAPI.IConfiguration;
import me.Salt.Util.Cooldown;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        jda = new JDABuilder(AccountType.BOT).setToken(new BufferedReader(new FileReader(new File("C:\\Users\\jake\\Desktop\\GitHub\\SaltBot-2.0\\src\\me\\Salt\\Configuration\\config.txt"))).readLine()).addEventListener(new EventDistributor()).buildBlocking(); //TODO Read bot token from config, and generate new token to prevent others from using the bot with this token.
        //TODO improve above method. Currently a temporary fix to an exploit.
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
                .registerCommand("remind", new RemindMeCommand(
                        new CommandContainer(
                                new CommandDescriptionBuilder()
                                        .addAlias("remindme")
                                        .addAuthor(jda.getUserById("112633500447838208"))
                                        .setComplete(false)
                                        .setDeprecated(false)
                                        .setDescription("Reminds the individual about something at a specified date")
                                        .setName("Scheduled Reminder")
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE))))
                .build();
    }

    //TODO add unit tests
    //TODO add JavaDoc comments where possible
    //TODO allow each guild to establish its own TimeZone. Then ensure all times and dates in the guild are adjusted to suit their times. Also attach the timezone name (such as UTC) after all times.
}
