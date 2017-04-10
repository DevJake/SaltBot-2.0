package me.Salt.SaltAPI;

import me.Salt.Exception.MissingDataException;

/**
 * Created by Salt001 on 10/04/2017.
 */
public class SaltBuilder {
    private String cmdPrefix;

    public SaltBuilder setCmdPrefix(String cmdPrefix) {
        this.cmdPrefix = cmdPrefix;
        return this;
    }

    public Salt build() throws MissingDataException {
        SafetyChecker c = (cmdPrefix) -> cmdPrefix != null && cmdPrefix.length() >= 1;
        if (c.isSafe(cmdPrefix)) return new SaltImpl(cmdPrefix);
        else throw new MissingDataException("Missing data for SaltBuilder!");
        //TODO add Logger.write(...) call
    }

    //TODO add checkers to the builder

    private interface SafetyChecker {
        boolean isSafe(String cmdPrefix);
    }
}
