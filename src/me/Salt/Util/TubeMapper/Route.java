package me.Salt.Util.TubeMapper;

import java.util.List;

/**
 * ->> SaltBot-2.0 <<-
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
