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

package me.Salt.SaltAPI.Util;

import me.Salt.Exception.MissingDataException;
import net.dv8tion.jda.core.entities.User;

import java.time.ZonedDateTime;


/**
 * Project title: SaltBot-2.0
 * Created by Salt on 10/04/2017.
 */
public class WarningBuilder {
    private User setter; //Who created the warning
    private User affector; //Who the warning is of
    private String reason;
    private ZonedDateTime setDate;
    private ZonedDateTime expirationDate;    //TODO research best time system to be used

    public WarningBuilder setSetter(User setter) {
        this.setter = setter;
        return this;
    }

    public WarningBuilder setAffector(User affector) {
        this.affector = affector;
        return this;
    }

    public WarningBuilder setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public WarningBuilder setSetDate(ZonedDateTime setDate) {
        this.setDate = setDate;
        return this;
    }

    public WarningBuilder setExpirationDate(ZonedDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public Warning build() throws MissingDataException {
        return new Warning(setter, affector, reason, setDate, expirationDate);
        //TODO add Logger.write(...) call
        //TODO add safety check system. See ConfigurationBuilder()
    }

    public class Warning {
        private User setter; //Who created the warning
        private User affector; //Who the warning is of
        private String reason;
        private ZonedDateTime setDate;
        private ZonedDateTime expirationDate;
        private String evidenceID; //TODO add a system of creating and referencing to 'evidence'. Possibly in a text file, or stored online on a text-pasting website.

        public Warning(User setter, User affector, String reason, ZonedDateTime setDate, ZonedDateTime expirationDate) {
            this.setter = setter;
            this.affector = affector;
            this.reason = reason;
            this.setDate = setDate;
            this.expirationDate = expirationDate;
        }

        public User getSetter() {
            return setter;
        }

        public User getAffector() {
            return affector;
        }

        public String getReason() {
            return reason;
        }

        public ZonedDateTime getSetDate() {
            return setDate;
        }

        public ZonedDateTime getExpirationDate() {
            return expirationDate;
        }
    }
}
