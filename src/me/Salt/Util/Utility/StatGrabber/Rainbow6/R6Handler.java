package me.Salt.Util.Utility.StatGrabber.Rainbow6;

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.R6Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class R6Handler {
    private List<R6Player> players = new ArrayList<>();
    private HashMap<String, R6Player> idPlayerLookup = new HashMap<>(); //Lookup by ID
    private HashMap<String, R6Player> namePlayerLookup = new HashMap<>(); //Lookup by name

    public R6Player getPlayerById() {
        //TODO
        return null;
    }

    public List<R6Player> getNamePlayerLookup() {
        //TODO
        return null;
    }

    public List<R6Player> getPlayers() {
        return players;
    }
}
