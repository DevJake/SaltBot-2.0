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
package me.Salt.Util.Utility.TubeMapper;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 13/04/2017.
 */
public class Station {
    private String name;
    private boolean railConnection;

    public Station(String name, boolean railConnection) {
        this.name = name;
        this.railConnection = railConnection;
    }

    @Override
    public String toString() {
        return "Station{" + "name='" + name + '\'' + ", railConnection=" + railConnection + '}';
    }

    public String getName() {
        return name;
    }

    public boolean isRailConnection() {
        return railConnection;
    }
}
