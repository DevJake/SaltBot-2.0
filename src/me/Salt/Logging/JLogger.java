/*
 *
 *  * Copyright (c) 2017 DevJake
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package me.Salt.Logging;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

/**
 * Allows for the logging of information to the console, text file, or both. Currently supports just console logging.
 */
public class JLogger {

    /**
     * Attempts to write a string to the console.
     *
     * @param message String - The message to be written
     */

    public static void writeToConsole(String message) {
        System.out.println("[" + Instant.now().atOffset(ZoneOffset.ofHours(-1)) + "]" + " [" + Level.INFO.name() + "] " + message);
        //TODO add a call to a private writeToFile and writeToChannel method
    }

    /**
     * Attempts to write a string to the console, as well as details about a specified object.
     *
     * @param message String - The message to be written
     * @param o       Object - The object to write details about
     */
    public static void writeToConsole(String message, Object o) {
        System.out.println("[" + Instant.now().atOffset(ZoneOffset.ofHours(-1)) + "]" + " [" + Level.INFO.name() + "] " + message + ". Thrown by class: " + o.getClass().getName());
    }

    /**
     * Attempts to write a string to the console, prepended by a warning level
     *
     * @param level   Level - The level of the log entry
     * @param message String - The message to be written
     */
    public static void writeToConsole(Level level, String message) {
        System.out.println("[" + Instant.now().atOffset(ZoneOffset.ofHours(-1)) + "]" + " [" + level.name() + "] " + message);
    }

    /**
     * Attempts to write a string to the console, prepended by a warning level. Information about the object is also included.
     *
     * @param level   Level - The level of the log entry
     * @param message String - The message to be written
     * @param o       Object - The object to write details about
     */
    public static void writeToConsole(Level level, String message, Object o) {
        System.out.println("[" + Instant.now().atOffset(ZoneOffset.ofHours(-1)) + "]" + " [" + level.name() + "] " + message + ". Thrown by class: " + o.getClass().getName());
    }

    public static void writeToConsole(List<String> prefixes, Level level, String message, Object o) {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + Instant.now().atOffset(ZoneOffset.ofHours(-1)) + "] [" + level.name() + "] ");
        prefixes.forEach(p -> sb.append("[" + p + "]"));
        sb.append(message + ". Thrown by class: " + o.getClass().getName());
        System.out.println(sb.toString());
    }

    public static void writeToConsole(String prefix, Level level, String message, Object o) {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + Instant.now().atOffset(ZoneOffset.ofHours(-1)) + "] [" + level.name() + "] ");
        sb.append("[" + prefix + "]");
        sb.append(message + ". Thrown by class: " + o.getClass().getName());
        System.out.println(sb.toString());
    }

    public static void writeToConsole(List<String> prefixes, Level level, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + Instant.now().atOffset(ZoneOffset.ofHours(-1)) + "][" + level.name() + "]");
        prefixes.forEach(p -> sb.append("[" + p + "]"));
        sb.append(" "); //To 'sexify' the output
        sb.append(message);
        System.out.println(sb.toString());
    }

    public static void writeToConsole(String prefix, Level level, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + Instant.now().atOffset(ZoneOffset.ofHours(-1)) + "][" + level.name() + "]");
        sb.append("[" + prefix + "] ");
        sb.append(message);
        System.out.println(sb.toString());
    }

//TODO Change to a simple method with a message, or a method with a LogBuilder. Simplifies the process of making log entries.

    /**
     * The level of the warning that should be used when creating log and/or console entries.
     * <p>
     * INFO - Information and details of generic operation
     * <br>
     * CONFIG - Information about changes related to configurations
     * <br>
     * WARNING - A notice of improper behaviour in the application
     * <br>
     * SEVERE - A notice of extremely improper - potentially crash-causing - behaviour
     * <br>
     * FATAL - A notice of crash-causing details; often composing the final set of entries to a log file
     * <br>
     * DEBUG - Information displayed whilst debugging mode is enabled
     */
    public enum Level {
        INFO, //Information and details of generic operation
        CONFIG, //Information about changes related to configurations
        WARNING, //A notice of improper behaviour in the application
        SEVERE, //A notice of extremely improper - potentially crash-causing - behaviour
        FATAL, //A notice of crash-causing details; often composing the final set of entries to a log file
        DEBUG //Information displayed when debugging mode is enabled
    }


}

//TODO allow toggling of console/file logging on entries (though not allowing such an ability on SEVERE and FATAL details)