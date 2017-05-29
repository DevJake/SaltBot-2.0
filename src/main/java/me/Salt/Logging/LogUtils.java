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
    
    private static boolean notSafe() {
        return Main.salt == null ? false : Main.salt.getLanguageHandler() == null ? false : true;
    }
    
    public static void log(String message, Severity severity, String className, Exception e) {
        if (notSafe()) return;
        System.out.println("[" + Instant.now().toString() + " ][" + getLang(severity) + "] " + message);
    }
    
    public static void log(String message, String className, Exception e) {
        if (notSafe()) return;
        System.out.println("[" + Instant.now().toString() + " ][" + getLang(Severity.INFO) + "] " + message);
    }
    
    public static void log(String message, String className) {
        if (notSafe()) return;
        System.out.println("[" + Instant.now().toString() + " ][" + getLang(Severity.INFO) + "] " + message);
    }
    
    public static void log(String message, Severity severity, String className) {
        if (notSafe()) return;
        System.out.println("[" + Instant.now().toString() + " ][" + getLang(severity) + "] " + message);
    }
    
    public static void log(String message, Severity severity) {
        if (notSafe()) return;
        System.out.println("[" + Instant.now().toString() + " ][" + getLang(severity) + "] " + message);
    }
    
    public static void log(String message) {
        if (notSafe()) return;
        System.out.println("[" + Instant.now().toString() + " ][" + getLang(Severity.INFO) + "] " + message);
    }
    //TODO consider adding a Log builder, to properly format a log entry
    
    private static String getLang(Severity severity) {
        switch (severity) {
            case INFO:
                return Main.salt.getLanguageHandler()
                                .getLanguage(Main.salt.getDefaultLangCode())
                                .getString(LangString.LOGGING_INFO) != null ? Main.salt.getLanguageHandler()
                                                                                       .getLanguage(
                                                                                               Main.salt.getDefaultLangCode())
                                                                                       .getString(
                                                                                               LangString.LOGGING_INFO)
                                                                                       .toUpperCase() : "INFO";
            case DEBUG:
                return Main.salt.getLanguageHandler()
                                .getLanguage(Main.salt.getDefaultLangCode())
                                .getString(LangString.LOGGING_DEBUG) != null ? Main.salt.getLanguageHandler()
                                                                                        .getLanguage(
                                                                                                Main.salt.getDefaultLangCode())
                                                                                        .getString(
                                                                                                LangString.LOGGING_DEBUG)
                                                                                        .toUpperCase() : "DEBUG";
            case FATAL:
                return Main.salt.getLanguageHandler()
                                .getLanguage(Main.salt.getDefaultLangCode())
                                .getString(LangString.LOGGING_FATAL) != null ? Main.salt.getLanguageHandler()
                                                                                        .getLanguage(
                                                                                                Main.salt.getDefaultLangCode())
                                                                                        .getString(
                                                                                                LangString.LOGGING_FATAL)
                                                                                        .toUpperCase() : "FATAL";
            case CONFIG:
                return Main.salt.getLanguageHandler()
                                .getLanguage(Main.salt.getDefaultLangCode())
                                .getString(LangString.LOGGING_CONFIG) != null ? Main.salt.getLanguageHandler()
                                                                                         .getLanguage(
                                                                                                 Main.salt.getDefaultLangCode())
                                                                                         .getString(
                                                                                                 LangString.LOGGING_CONFIG)
                                                                                         .toUpperCase() : "CONFIG";
            case SEVERE:
                return Main.salt.getLanguageHandler()
                                .getLanguage(Main.salt.getDefaultLangCode())
                                .getString(LangString.LOGGING_SEVERE) != null ? Main.salt.getLanguageHandler()
                                                                                         .getLanguage(
                                                                                                 Main.salt.getDefaultLangCode())
                                                                                         .getString(
                                                                                                 LangString.LOGGING_SEVERE)
                                                                                         .toUpperCase() : "SEVERE";
            case WARNING:
                return Main.salt.getLanguageHandler()
                                .getLanguage(Main.salt.getDefaultLangCode())
                                .getString(LangString.LOGGING_WARNING) != null ? Main.salt.getLanguageHandler()
                                                                                          .getLanguage(
                                                                                                  Main.salt.getDefaultLangCode())
                                                                                          .getString(
                                                                                                  LangString.LOGGING_WARNING)
                                                                                          .toUpperCase() : "WARNING";
            default:
                return "LANGUAGE FILE MISSING DATA";
        }
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
