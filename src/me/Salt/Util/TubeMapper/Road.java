package me.Salt.Util.TubeMapper;

/**
 * ->> SaltBot-2.0 <<-
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
