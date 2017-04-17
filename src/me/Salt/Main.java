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
import me.Salt.Command.Commands.HelpCommand;
import me.Salt.Command.Commands.PingCommand;
import me.Salt.Event.EventDistributor;
import me.Salt.Exception.DuplicateDataException;
import me.Salt.Exception.MissingDataException;
import me.Salt.SaltAPI.ConfigurationBuilder;
import me.Salt.SaltAPI.IConfiguration;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

/**
 * SaltBot 2.0 -- The original, rebuilt!
 */
public class Main {
    private static final long n = System.currentTimeMillis(); //Final, to ensure it cannot be changed
    public static JDA jda;
    public static IConfiguration salt;

    public static void main(String[] args) throws LoginException, InterruptedException, RateLimitedException, MissingDataException, DuplicateDataException {

        jda = new JDABuilder(AccountType.BOT).setToken("MjQ2MzA5NDI1OTAyNjQ5MzQ1.C9aoxA.BAQNRUAKr2i3RPYhmE3KTq4z-W0").addListener(new EventDistributor()).buildBlocking(); //TODO Read bot token from config, and generate new token to prevent others from using the bot with this token.
        salt = new ConfigurationBuilder(".")
                .setDebugMode(false)
                .setStartupTime(n)
                .setName("SaltBot")
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
                                        .build(),
                                Arrays.asList(CommandContainer.JEvent.GENERIC_MESSAGE)
                        ))).build();
    }

    //TODO add unit tests
    //TODO add JavaDoc comments where possible
}
