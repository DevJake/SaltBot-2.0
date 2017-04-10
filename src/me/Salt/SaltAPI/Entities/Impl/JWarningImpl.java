package me.Salt.SaltAPI.Entities.Impl;

import me.Salt.SaltAPI.Entities.JWarning;
import me.Salt.SaltAPI.Util.WarningBuilder;

import java.util.List;

/**
 * ->> SaltBot-2.0 <<-
 * Created by Salt on 10/04/2017.
 */
public class JWarningImpl implements JWarning {
    private List<WarningBuilder.Warning> warnings;

    @Override
    public List<WarningBuilder.Warning> getWarnings() {
        return warnings;
    }
}
