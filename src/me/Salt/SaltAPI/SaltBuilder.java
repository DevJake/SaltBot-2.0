package me.Salt.SaltAPI;

import me.Salt.Exception.MissingDataException;
import net.dv8tion.jda.core.entities.User;

import java.util.Map;

/**
 * Created by Salt001 on 10/04/2017.
 */
public class SaltBuilder {
    private String cmdPrefix;
    private Map<User, Salt.Rank> staff;

    public SaltBuilder(String cmdPrefix) {
        this.cmdPrefix = cmdPrefix;
    }

    public SaltBuilder addStaff(User user, Salt.Rank rank){
        staff.put(user, rank);
        return this;
    }

    public Salt build() throws MissingDataException {
        SafetyChecker c = (cmdPrefix) -> cmdPrefix != null && cmdPrefix.length() >= 1;
        if (c.isSafe(cmdPrefix)) return new SaltImpl(cmdPrefix, staff);
        else throw new MissingDataException("Missing data for SaltBuilder!");
        //TODO add Logger.write(...) call
    }

    //TODO add checkers to the builder

    private interface SafetyChecker {
        boolean isSafe(String cmdPrefix);
    }
}
