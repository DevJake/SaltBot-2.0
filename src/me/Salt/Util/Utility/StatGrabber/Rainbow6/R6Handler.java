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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.R6Player;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultHttpClientConnectionOperator;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This class is designed to act as the base class for interactions with the Rainbow 6 REST API.
 */
public class R6Handler {
    private List<R6Player> players = new ArrayList<>();
    private HashMap<String, R6Player> idPlayerLookup = new HashMap<>(); //Lookup by ID
    private HashMap<String, R6Player> namePlayerLookup = new HashMap<>(); //Lookup by name
    private String apiURL;

    public R6Handler(String apiURL) {
        this.apiURL = apiURL; //https://api.r6stats.com/api/v1

        HttpClient client = HttpClientBuilder.create().build();
        try {
            Gson g = new Gson();

            StringBuilder n = new StringBuilder();

            InputStreamReader i = new InputStreamReader(
                    Unirest.get("https://api.r6stats.com/api/v1/players/Wallek224")
                            .header("accept", "application/json")
                            .queryString("platform", "uplay")
                            .asJson().getRawBody());
            int x = i.read();
            while (x!=-1){
                n.append(String.valueOf((char) x));
                x = i.read();
            }

            System.out.println(n.toString());

        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
