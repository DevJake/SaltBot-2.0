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
package me.Salt.Util.Utility.Games;

import me.Salt.Event.jevent.game.GameManagerRegisterGameEvent;
import me.Salt.Event.util.EventInitiator;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.CaDGameHandler;
import net.dv8tion.jda.core.entities.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * This class acts as a handler for all different types of Games. An instance of {@link Game} is registered under a
 * user. When attempting to play a game, the bot need only identify the game owner's ID, and the <i>type</i> of game
 * being played.
 */
public class GameManager {
    /**
     * A HashMap with a nested List of {@link Game} instances.
     */
    private static HashMap<User, List<Game>> userGames = new HashMap<>(); //Register a game to an individual, who is the one who created the game
    
    /**
     * This method allows for the registration of a game to a specific {@link User}. If the user already exists
     * within the {@link GameManager#userGames} HashMap, the user is grabbed and their list of games updated to add
     * the new game instance.
     *
     * @param user {@link User} - The User to register the game instance under
     * @param game {@link Game} - The Game instance to be registered to the specified user
     */
    public static void registerGame(User user, Game game) {
        // TODO: 30/05/2017 Possibly prevent users having multiple games of different types?
        if (userGames.containsKey(user)) {
            System.out.println("Key found");
            if (!hasGameOfType(user, CaDGameHandler.class)) {
                userGames.get(user).add(game);
                System.out.println("registered new cad game to existing user");
                EventInitiator.fire(new GameManagerRegisterGameEvent(user, game));
            }
        } else {
            System.out.println("Key not found");
            userGames.put(user, Collections.singletonList(game));
        }
    }
    
    /**
     * This method unregisters a game from a specified user, if the user <i>has</i> an instance of the specified game.
     *
     * @param user {@link User} - The User that the game instance is registered to
     * @param game {@link Game} - The Game instance to be removed from the specified user
     */
    public static void unregisterGameFromUser(User user, Game game) {
        // TODO: 30/05/2017 Update game to generic type that implements Game
        // TODO: 30/05/2017 Force games to have a shutdown() method, which is called upon game unregistration. Ensures games shut down smoothly.
        if (userGames.containsKey(user)){
            if (userGames.get(user).contains(game)){
                userGames.get(user).remove(userGames.get(user).indexOf(game));
            }
        }
    }
    
    /**
     * This method takes a specified user, and removes any game instances registered to their name.
     *
     * @param user {@link User} - The User that the game instance is registered to
     */
    public static void unregisterAllGamesFromUser(User user) {
        if (userGames.containsKey(user)) userGames.remove(user);
    }
    
    /**
     * This method checks if a user has a game of a specific type, and returns a boolean reflecting the result.
     *
     * @param user  {@link User} - The User that the game instance is registered to
     * @param clazz Class - The class of a {@link Game} implementation that should be queried
     * @return Boolean - If the specified user has a game of the type specified
     */
    public static boolean hasGameOfType(User user, Class clazz) {
        // TODO: 30/05/2017 Update clazz to generic type that implements Game
        if (userGames.containsKey(user)) {
            for (Game game : userGames.get(user)) {
                if (game.getClass() == clazz) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * This method returns the instance of a specified game, registered to a specified user.
     * <p>
     * <b>This method should be called after using {@link GameManager#hasGameOfType(User, Class)} to check if the
     * user <i>has</i> a game instance of specified type. This method returns Null if no instance is found. </b>
     *
     * @param user  {@link User} - The User that the game instance is registered to
     * @param clazz Class - The class of a {@link Game} implementation that should be queried
     * @return Possibly-null: A {@link Game} instance that was located, or <b>null</b> if no game instance was located
     */
    public static Game getGameOfType(User user, Class clazz) {
        if (hasGameOfType(user, clazz)) {
            for (Game g : userGames.get(user)) {
                if (g.getClass() == clazz) return g;
            }
        }
        return null;
    }
    
    /**
     * @return {@link GameManager#userGames}
     */
    public static HashMap<User, List<Game>> getUserGames() {
        return userGames;
    }
}
