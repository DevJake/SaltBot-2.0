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

public class GameManager {
    private static HashMap<User, List<Game>> userGames = new HashMap<>(); //Register a game to an individual, who is the one who created the game
    
    public static void registerGame(User user, Game game) {
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
    
    public static void unregisterGameFromUser(User user, Game game) {
        // TODO: 26/05/2017
    }
    
    public static void unregisterAllGamesFromUser(User user) {
        if (userGames.containsKey(user)) userGames.remove(user);
    }
    
    public static boolean hasGameOfType(User user, Class clazz) {
        if (userGames.containsKey(user)) {
            for (Game game : userGames.get(user)) {
                if (game.getClass() == clazz) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static Game getGameOfType(User user, Class clazz) {
        if (hasGameOfType(user, clazz)) {
            for (Game g : userGames.get(user)) {
                if (g.getClass() == clazz) return g;
            }
        }
        return null;
    }
    
    public static HashMap<User, List<Game>> getUserGames() {
        return userGames;
    }
}
