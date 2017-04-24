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

import me.Salt.Command.ICommand;
import me.Salt.Handler.Main;
import net.dv8tion.jda.core.entities.User;

import java.util.HashMap;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 18/04/2017.
 */
public class CooldownManager {
    private static HashMap<ICommand, HashMap<User, Long>> cooldowns = new HashMap<>();
    //Search first by command, then retrieve a HashMap of users. Search the list for the user, and retrieve the time at which the command was last executed.

    /**
     * Returns true or false if the user is still in cooldown period
     *
     * @param cmd     String - The command to be used when attempting to obtain a CommandContainer
     * @param command ICommand - The Command itself - which implements the ICommand interface - that is to be used for cooldown checking
     * @param user    User - The User who is being checked for a cooldown
     * @param time    Long - The time at which the command was last executed.
     *                This value is used to provide information about when the user can next use the command.
     *
     * @return Boolean - A boolean value representing if the specified User is within the cooldown period or not. If they are, they cannot use the command. If they are outside of the cooldown period, they can use the command again.
     */
    public static boolean isNotInCooldown(String cmd, ICommand command, User user, long time) {
        //Check user is within cooldown. Compare to list, add to list if necessary.
        if (cooldowns.containsKey(command)) {
            if (cooldowns.get(command).containsKey(user)) {
                if (Main.salt.getCommands().get(cmd).getCmdContainer().getCommandDescription().hasCooldown()) {//Does the command actually -have- a cooldown?
                    if (System.currentTimeMillis() - cooldowns.get(command).get(user) >= Main.salt.getCommands().get(cmd).getCmdContainer().getCommandDescription().getCooldown().getValue()) {
                        cooldowns.get(command).replace(user, time);
                        return true;
                    } else return false;
                } else return true; //Command has no cooldown... allow continued operation
            } else {
                cooldowns.get(command).put(user, time); //Add ourselves to the list of users for the command
                return Main.salt.getCommands().get(cmd).getCmdContainer().getCommandDescription().hasCooldown(); //isNotInCooldown(cmd, command, user, time);
                // Return the result of this method
            }

        } else { //If 'cooldowns' doesn't already have this command mapped, add it, and add the user as the first user
            if (Main.salt.getCommands().get(cmd).getCmdContainer().getCommandDescription().hasCooldown()) {//Does the command actually -have- a cooldown?
                cooldowns.put(command, new HashMap<>());
                cooldowns.get(command).put(user, time);
            } else return true;
        }
        return true;
    }

    public static long getRemainingTime(String cmd, ICommand command, User user) { //Return how long the individual has left
//TODO convert remaining time to seconds.
        long called = cooldowns.get(command).get(user);
        long cooldown = Main.salt.getCommands().get(cmd).getCmdContainer().getCommandDescription().getCooldown().getValue();
        return cooldown - (System.currentTimeMillis() - called);
        //TODO We can assume that cooldowns contains the value(s) already, as we know isNotInCooldown(...) will already have been called. In future, add the necessary checks. Throw an exception if data is missing.
    }
}
