package me.Salt.SaltAPI;

/**
 * Created by Salt001 on 10/04/2017.
 */
public class SaltImpl implements Salt {
    private String cmdPrefix;

    public SaltImpl(String cmdPrefix) {
        this.cmdPrefix = cmdPrefix;
    }

    public String getCmdPrefix() {
        return cmdPrefix;
    }
}
