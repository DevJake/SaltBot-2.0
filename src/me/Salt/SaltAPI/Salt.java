package me.Salt.SaltAPI;

import me.Salt.SaltAPI.Guild.JGuild;
import me.Salt.SaltAPI.User.JUser;
import net.dv8tion.jda.core.entities.User;

import java.util.List;
import java.util.Map;

/**
 * ->> SaltBot-2.0 <<-
 * Created by Salt on 10/04/2017.
 */
public interface Salt {
    public String getCmdPrefix();

    public List<JUser> getJUsers();

    public JUser getJUserByID(String id); //TODO Develop unique method of converting users to an ID. Could use inbuilt IDs, but would be difficult to discriminate between default ID and API-wide ID.

    public List<JUser> getJUsersByName(String name);

    public List<JGuild> getJGuilds();

    public JGuild getJGuildByID(String id);

    public List<JGuild> getJGuildsByName(String name);

    public Map<User, Rank> getTeam(); //TODO

    public enum Rank {
        DEVELOPER,
        CONTRIBUTOR,
        TESTER
    }
}
