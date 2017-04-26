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

package me.Salt.Util.Utility.StatGrabber.Rainbow6;

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.R6Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class R6Handler {
    private List<R6Player> players = new ArrayList<>();
    private HashMap<String, R6Player> idPlayerLookup = new HashMap<>(); //Lookup by ID
    private HashMap<String, R6Player> namePlayerLookup = new HashMap<>(); //Lookup by name
    private String apiURL;

    public R6Handler(String apiURL) {
        this.apiURL = apiURL; //https://api.r6stats.com/api/v1
    }

    public String getApiURL() {
        return apiURL;
    }

    public R6Player getPlayerById() {
        //TODO
        return null;
    }

    public List<R6Player> getNamePlayerLookup() {
        //TODO
        return null;
    }

    public List<R6Player> getPlayers() {
        return players;
    }
}
