package me.Salt.SaltAPI.Guild.Impl;

import me.Salt.SaltAPI.Guild.JGuild;
import net.dv8tion.jda.core.entities.Guild;

/**
 * Created by Salt001 on 10/04/2017.
 */
public class JGuildImpl implements JGuild {
    private Guild guild;

    @Override
    public Guild getGuild() {
        return guild;
    }
}
