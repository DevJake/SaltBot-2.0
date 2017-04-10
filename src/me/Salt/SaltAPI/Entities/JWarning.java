package me.Salt.SaltAPI.Entities;

import me.Salt.SaltAPI.Util.WarningBuilder;

import java.util.List;

/**
 * ->> SaltBot-2.0 <<-
 * Created by Salt on 10/04/2017.
 */
public interface JWarning {
    public List<WarningBuilder.Warning> getWarnings();
}
