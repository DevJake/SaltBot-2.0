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

import java.util.List;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 14/04/2017.
 */
public class Route {
    private Station start;
    private Station end;
    private List<Station> stops;

    public Route(Station start, Station end, List<Station> stops) {

        this.start = start;
        this.end = end;
        this.stops = stops;
    }

    public Station getStart() {
        return start;
    }

    public Station getEnd() {
        return end;
    }

    public List<Station> getStops() {
        return stops;
    }
}