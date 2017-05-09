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

package main.java.me.Salt.Util.Utility.TubeMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Project title: SaltBot-2.0
 * Created by Salt on 13/04/2017.
 */
public class TubeManager {
    private List<TubeLine> tubeLines = new ArrayList<>();
    private HashMap<String, Station> stationLookup; //String station, Station station
    private HashMap<Station, TubeLine> stationToTubeline; //String station, Station station
    private HashMap<String, TubeLine> tubeLineLookup; //String linename, TubeLine line

    public List<Station> getShortestRoute(Station start, Station end) {
        List<Road> priorityQueue = new ArrayList<>();

        for (Station s : tubeLines.get(0).getStations()) {
            priorityQueue.add(new Road(s, null, 0));
        }

        int index = tubeLines.get(0).getStations().indexOf(start);

        if (index == 0 && tubeLines.get(0).getStations().size() > 0) {
            //We are at the first station of the line, and there are more stations
        } else if (index > 0 && tubeLines.get(0).getStations().size() != index) {
            //We are not at the first station of the line. There is atleast 1 station behind us.
        } else if (index > 0) {
            //We are at the last station of this line
        } else return null; //Not possible to continue. Line must only have 1 station

        return null;
    }


    public void init() {
        List<Station> stations = new ArrayList<>();

        stations.add(new Station("Harrow & Wealdstone", true));
        stations.add(new Station("Kenton", false));
        stations.add(new Station("South Kenton", false));
        stations.add(new Station("North Wembley", false));
        stations.add(new Station("Wembley Central", true));
        stations.add(new Station("Stonebridge Park", false));
        stations.add(new Station("Harlesden", false));
        stations.add(new Station("Willesden Junction", true));
        stations.add(new Station("Kensal Green", false));
        stations.add(new Station("Queen's Park", false));
        stations.add(new Station("Kilburn Park", false));
        stations.add(new Station("Maida Vale", false));
        stations.add(new Station("Warwick Avenue", false));
        stations.add(new Station("Paddington", true));
        stations.add(new Station("Edgware Road", false));
        stations.add(new Station("Marylebone", true));
        stations.add(new Station("Regent's Park", false));
        stations.add(new Station("Baker Street", false));
        stations.add(new Station("Oxford Circus", false));
        stations.add(new Station("Piccadilly Circus", false));
        stations.add(new Station("Charing Cross", true));
        stations.add(new Station("Embankment", false));
        stations.add(new Station("Waterloo", true));
        stations.add(new Station("Lambeth North", false));
        stations.add(new Station("Elephant & Castle", false));

        tubeLines.add(new TubeLine("Bakerloo", TubeLine.Colour.BROWN, stations));
    }

    //TODO this is a complete mess. Rework.
}
