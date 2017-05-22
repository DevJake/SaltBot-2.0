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

package me.Salt.Util.Utility.StatGrabber.Rainbow6;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.Impl.R6PlayerImpl;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.R6Player;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Util.Platform;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Util.Serialiser.R6PlayerDeserialiaser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is designed to act as the base class for interactions with the Rainbow 6 REST API.
 */
public class R6Handler {
    private final String apiURL;
    private final Gson g =
            new GsonBuilder()
                    .registerTypeAdapter(R6PlayerImpl.class, new R6PlayerDeserialiaser())
                    .create();
    private List<R6Player> players = new ArrayList<>();
    private HashMap<String, R6Player> namePlayerLookup = new HashMap<>(); //Lookup by name

    public R6Handler(String apiURL) {
        this.apiURL = apiURL;
    }

    public String getApiURL() {
        return apiURL;
    }

    public List<R6Player> getNamePlayerLookup() {
        //TODO
        return null;
    }

    public List<R6Player> getPlayers() {
        return players;
    }

    public R6Player getPlayerByName(String username, Platform platform) {
        R6Player r = null;
        try {
            StringBuilder n = new StringBuilder();

            InputStreamReader i = new InputStreamReader(
                    Unirest.get(apiURL + "/players/{username}")
                            .routeParam("username", username)
                            .header("accept", "application/json")
                            .queryString("platform", platform.name().toLowerCase())
                            .asJson().getRawBody());
            int x = i.read();
            while (x != -1) {
                n.append(String.valueOf((char) x));
                x = i.read();
            }
            
            if (n.toString().contains("status")) throw new MissingDataException("Wrong data!");
            r = g.fromJson(n.toString(), R6PlayerImpl.class);
            System.out.println(r.toString());
            System.out.println(n.toString());

        } catch (UnirestException | IOException | MissingDataException e) {
            e.printStackTrace();
        }
        return r;
    }
}
