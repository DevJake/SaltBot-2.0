package me.Salt.Util.TubeMapper;

/**
 * ->> SaltBot-2.0 <<-
 * Created by Salt on 13/04/2017.
 */
public class Station {
    private String name;
    private boolean railConnection;

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", railConnection=" + railConnection +
                '}';
    }

    public Station(String name, boolean railConnection) {

        this.name = name;
        this.railConnection = railConnection;
    }

    public String getName() {
        return name;
    }

    public boolean isRailConnection() {
        return railConnection;
    }
}
