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
package me.Salt.Event.jevent.game;

import me.Salt.Event.util.JEvent;
import me.Salt.Util.Utility.Games.Game;
import net.dv8tion.jda.core.entities.User;

public class GameManagerRegisterGameEvent extends JEvent {
    private User user;
    private Game newGame;
    
    public GameManagerRegisterGameEvent(User user, Game newGame) {
        this.user = user;
        this.newGame = newGame;
    }
    
    public User getUser() {
        return user;
    }
    
    public Game getNewGame() {
        return newGame;
    }
}
