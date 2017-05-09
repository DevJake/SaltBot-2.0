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

package main.java.me.Salt.Logging;

import java.time.ZonedDateTime;

public class LogEntry {
    private ZonedDateTime time;
    private JLogger.Level level;
    private String message;
    private String className; //Class that created this log entry
    private Exception cause;

    @Override
    public String toString() {
        return "LogEntry{" +
                "time=" + time +
                ", level=" + level +
                ", message='" + message + '\'' +
                ", className='" + className + '\'' +
                ", cause=" + cause +
                '}';
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public JLogger.Level getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public String getClassName() {
        return className;
    }

    public Exception getCause() {
        return cause;
    }

    public LogEntry(ZonedDateTime time, JLogger.Level level, String message, String className, Exception cause) {

        this.time = time;
        this.level = level;
        this.message = message;
        this.className = className;
        this.cause = cause;
    }
}
