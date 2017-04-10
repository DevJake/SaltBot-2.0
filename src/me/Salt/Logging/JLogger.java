package me.Salt.Logging;

import java.time.Instant;
import java.time.ZoneOffset;

/**
 * Allows for the logging of information to the console, text file, or both.
 */
public class JLogger {

    /**
     * Attempts to write data to the console
     *
     * @return Boolean - If the operation was successful
     */

    public static void writeToConsole(String message) {
        System.out.println("[" + Instant.now().atOffset(ZoneOffset.ofHours(-1)) + "]" + " [" + Level.INFO.name() + "] " + message);
        //TODO add a call to a private writeToFile and writeToChannel method
    }

    public static void writeToConsole(String message, Object o) {
        System.out.println("[" + Instant.now().atOffset(ZoneOffset.ofHours(-1)) + "]" + " [" + Level.INFO.name() + "] " + message + ". Thrown by class: " + o.getClass().getName());
    }

    public static void writeToConsole(Level level, String message) {
        System.out.println("[" + Instant.now().atOffset(ZoneOffset.ofHours(-1)) + "]" + " [" + level.name() + "] " + message);
    }

    public static void writeToConsole(Level level, String message, Object o) {
        System.out.println("[" + Instant.now().atOffset(ZoneOffset.ofHours(-1)) + "]" + " [" + level.name() + "] " + message + ". Thrown by class: " + o.getClass().getName());
    }


    public enum Level {
        INFO, //Information and details of generic operation
        CONFIG, //Information about changes related to configurations
        WARNING, //A notice of improper behaviour in the application
        SEVERE, //A notice of extremely improper - potentially crash-causing - behaviour
        FATAL //A notice of crash-causing details; often composing the final set of entries to a log file
    }


}

//TODO allow toggling of console/file logging on entries (though not allowing such an ability on SEVERE and FATAL details)