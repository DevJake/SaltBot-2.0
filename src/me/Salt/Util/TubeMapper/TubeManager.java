package me.Salt.Util.TubeMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ->> SaltBot-2.0 <<-
 * Created by Salt on 13/04/2017.
 */
public class TubeManager {
    private List<TubeLine> tubeLines = new ArrayList<>();
    private HashMap<String, String> stations; //String station, String tubeline
    private HashMap<String, TubeLine> tubeLineLookup; //String linename, TubeLine line;

    public List<Station> getShortestRoute(Station start, Station end) {
        List<String> routes = new ArrayList<>();

        getTubeStations().forEach(s1 -> this.getTubeStations().forEach(s2 -> routes.add(s1.getName() + ":" + s2.getName())));

        routes.stream().filter(s -> {
            String[] j = s.split(":");
            return !j[0].equals(j[1]);
        }).collect(Collectors.toList()).stream().distinct().forEach(System.out::println);
//.distinct() usage is effectively pointless, but used as a safety measure
//        if ((getStationsByName(start.getName()).size()>0 && getStationsByName(end.getName()).size()>0)) {
        //if (getStationsByName(start.getName()).get(0) getStationsByName(end.getName()).size() > 0)
        //}

        return new ArrayList<>();
    }

    public List<TubeLine> getTubeLines() {
        return tubeLines;
    }

    public TubeLine getTubeByStation(String name) {
        return tubeLineLookup.get(stations.get(name));
    }

    public List<Station> getStationsByName(String name) {
        return getTubeStations().stream().filter(station -> station.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public Station getStationByName(String name) {
        return getTubeStations().stream().filter(station -> station.getName().toLowerCase().equals(name.toLowerCase())).collect(Collectors.toList()).get(0);
    }

    public List<Station> getTubeStations() {
        List<Station> s = new ArrayList<>();
        Runnable r = () -> tubeLines.forEach(tubeLine -> s.addAll(tubeLine.getStations()));
        r.run();
        return s;
    }

//    public static TubeLine getStationByName(){
//        //TODO
//    }

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

        tubeLines.forEach(tubeLine -> tubeLine.getStations().forEach(station -> this.stations.put(station.getName(), tubeLine.getName())));

        tubeLines.forEach(tubeLine -> tubeLineLookup.put(tubeLine.getName(), tubeLine));
    }

    //TODO this is a complete mess. Rework.
}
