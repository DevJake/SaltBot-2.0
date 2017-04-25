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


import me.Salt.Main;
import me.Salt.Util.Language.LangString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class LogUtils {

    private List<LogEntry> logEntries = new ArrayList<>();

    public static void log(String message, Severity severity, String className, Exception e) {
        System.out.println("[" + Instant.now().toString() + " ] [" + Main.salt.getLanguageHandler().getLanguage(Main.salt.getDefaultLangCode()).getString(LangString.LOGGING_SEVERE).toUpperCase() + "] " + message);
    }

    public static void log(String message, String className, Exception e) {
        System.out.println("[" + Instant.now().toString() + " ] [" + Main.salt.getLanguageHandler().getLanguage(Main.salt.getDefaultLangCode()).getString(LangString.LOGGING_SEVERE).toUpperCase() + "] " + message);
    }

    public static void log(String message, String className) {
        System.out.println("[" + Instant.now().toString() + " ] [" + Main.salt.getLanguageHandler().getLanguage(Main.salt.getDefaultLangCode()).getString(LangString.LOGGING_SEVERE).toUpperCase() + "] " + message);
    }

    public static void log(String message, Severity severity, String className) {
        System.out.println("[" + Instant.now().toString() + " ] [" + Main.salt.getLanguageHandler().getLanguage(Main.salt.getDefaultLangCode()).getString(LangString.LOGGING_SEVERE).toUpperCase() + "] " + message);
    }

    public static void log(String message, Severity severity) {
        System.out.println("[" + Instant.now().toString() + " ] [" + Main.salt.getLanguageHandler().getLanguage(Main.salt.getDefaultLangCode()).getString(LangString.LOGGING_SEVERE).toUpperCase() + "] " + message);
    }


    public List<LogEntry> getLogEntries() {
        return logEntries;
    }

    public enum Severity {
        INFO,
        CONFIG,
        WARNING,
        SEVERE,
        FATAL,
        DEBUG,
    }
}
