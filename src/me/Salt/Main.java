package me.Salt;

import me.Salt.Event.EventDistributor;
import me.Salt.Exception.MissingDataException;
import me.Salt.SaltAPI.Salt;
import me.Salt.SaltAPI.SaltBuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

/**
 * SaltBot 2.0 -- The original, rebuilt!
 */
public class Main {
    public static JDA jda;
    public static Salt salt;

    public static void main(String[] args) throws LoginException, InterruptedException, RateLimitedException, MissingDataException {

        jda = new JDABuilder(AccountType.CLIENT).setToken("MTEyNjMzNTAwNDQ3ODM4MjA4.CSAEbA.JwKwlcs0Mif0Xc9zoKJBm9QRx5s").addListener(new EventDistributor()).buildBlocking();
        salt = new SaltBuilder(".").build();
    }

    //TODO add unit tests
    //TODO add JavaDoc comments where possible
}
