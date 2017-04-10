package me.Salt.SaltAPI.User.Impl;

import me.Salt.SaltAPI.User.JUser;
import me.Salt.SaltAPI.Util.WarningBuilder;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

/**
 * Created by Salt001 on 10/04/2017.
 */
public class JUserImpl implements JUser {
    private User user;
    private List<WarningBuilder.Warning> warnings;

    public JUserImpl(User user, List<WarningBuilder.Warning> warnings) {
        this.user = user;
        this.warnings = warnings;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public List<WarningBuilder.Warning> getWarnings() {
        return warnings;
    }
}
