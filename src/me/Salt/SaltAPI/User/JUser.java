package me.Salt.SaltAPI.User;

import me.Salt.SaltAPI.Util.WarningBuilder;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

/**
 * ->> SaltBot-2.0 <<-
 * Created by Salt on 10/04/2017.
 */
public interface JUser {
    public User getUser();

    public List<WarningBuilder.Warning> getWarnings();
}
