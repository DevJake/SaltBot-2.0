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
package me.Salt.Util;

import java.util.concurrent.TimeUnit;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 18/04/2017.
 */
public class Cooldown {
    private long value;
    private TimeUnit timeUnit;
    
    public Cooldown(long value, TimeUnit timeUnit) {
        this.value = timeUnit.toMillis(value); //Convert specified value to a long value, in milliseconds.
        this.timeUnit = timeUnit;
    }
    
    public long getValue() {
        return value;
    }
    
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
