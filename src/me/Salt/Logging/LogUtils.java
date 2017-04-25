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

package me.Salt.Logging;


import me.Salt.Handler.Main;
import me.Salt.Util.Language.LangString;
import me.Salt.Util.Language.LanguageHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LogUtils {

    private List<LogEntry> logEntries = new ArrayList<>();

    public static void info(String message) {

    }

    public static void config(String message) {

    }

    public static void warning(String message) {

    }

    public static void severe(String message) {
        System.out.println("[" + Instant.now().toString() + " ] [" + Main.salt.getLanguageHandler().getLanguage(Main.salt.getDefaultLangCode()).getString(LangString.LOGGING_SEVERE).toUpperCase() + "] " + message);
    }

    public static void fatal(String message) {

    }

    public static void debug(String message) {

    }

    public void addLogEntry(LogEntry entry) {
        logEntries = logEntries.stream().sorted(Comparator.comparing(LogEntry::getTime)).collect(Collectors.toList());
    }

    public List<LogEntry> getLogEntries() {
        return logEntries;
    }
}
