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

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 14/04/2017.
 */
public class Road {
    private Station nodeA;
    private Station via;
    private int distance;

    public Road(Station nodeA, Station via, int distance) {
        this.nodeA = nodeA;
        this.via = via;
        this.distance = distance;
    }

    public Station getNodeA() {

        return nodeA;
    }

    public void setNodeA(Station nodeA) {
        this.nodeA = nodeA;
    }

    public Station getVia() {
        return via;
    }

    public void setVia(Station via) {
        this.via = via;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
