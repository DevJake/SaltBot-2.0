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

import me.Salt.Event.EventDistributor;
import me.Salt.Exception.MissingDataException;
import me.Salt.SaltAPI.ConfigurationBuilder;
import me.Salt.SaltAPI.IConfiguration;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

/**
 * SaltBot 2.0 -- The original, rebuilt!
 */
public class Main {
    public static JDA jda;
    public static IConfiguration salt;

    public static void main(String[] args) throws LoginException, InterruptedException, RateLimitedException, MissingDataException {

        jda = new JDABuilder(AccountType.CLIENT).setToken("MTEyNjMzNTAwNDQ3ODM4MjA4.CSAEbA.JwKwlcs0Mif0Xc9zoKJBm9QRx5s").addListener(new EventDistributor()).buildBlocking();
        salt = new ConfigurationBuilder(".").build();
    }

    //TODO add unit tests
    //TODO add JavaDoc comments where possible
}
