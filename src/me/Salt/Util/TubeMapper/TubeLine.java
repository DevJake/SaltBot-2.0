package me.Salt.Util.TubeMapper;


import java.util.List;

/**
 * ->> SaltBot-2.0 <<-
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
