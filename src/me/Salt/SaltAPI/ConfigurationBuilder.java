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

package me.Salt.SaltAPI;

import me.Salt.Exception.MissingDataException;
import net.dv8tion.jda.core.entities.User;

import java.util.Map;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 10/04/2017.
 */
public class ConfigurationBuilder {
    private String cmdPrefix;
    private Map<User, IConfiguration.Rank> staff;

    public ConfigurationBuilder(String cmdPrefix) {
        this.cmdPrefix = cmdPrefix;
    }

    public ConfigurationBuilder addStaff(User user, IConfiguration.Rank rank) {
        staff.put(user, rank);
        return this;
    }

    public IConfiguration build() throws MissingDataException {
        SafetyChecker c = (cmdPrefix) -> cmdPrefix != null && cmdPrefix.length() >= 1;
        if (c.isSafe(cmdPrefix)) return new ConfigurationImpl(cmdPrefix, staff);
        else throw new MissingDataException("Missing data for ConfigurationBuilder!");
        //TODO add Logger.write(...) call
    }

    //TODO add checkers to the builder

    private interface SafetyChecker {
        boolean isSafe(String cmdPrefix);
    }
}
