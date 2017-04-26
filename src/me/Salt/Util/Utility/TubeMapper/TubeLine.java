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

package me.Salt.Util.Utility.TubeMapper;


import java.util.List;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 13/04/2017.
 */
public class TubeLine {

    private String name;
    private Colour colour;
    private List<Station> stations;

    public TubeLine(String name, Colour colour, List<Station> stations) {
        this.name = name;
        this.colour = colour;
        this.stations = stations;

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TubeLine{" +
                "name='" + name + '\'' +
                ", colour=" + colour +
                ", stations=" + stations.toString() +
                '}';
    }

    public Colour getColour() {
        return colour;
    }

    public List<Station> getStations() {
        return stations;
    }

    public enum Colour {
        BROWN,
        RED,
        YELLOW,
        GREEN,
        ORANGE,
        PINK,
        GREY,
        PURPLE,
        BLACK,
        DARK_BLUE,
        LIGHT_BLUE,
        CYAN
    }
}
