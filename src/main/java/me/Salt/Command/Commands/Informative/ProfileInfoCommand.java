/*
 * Copyright (c) 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.Salt.Command.Commands.Informative;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Exception.Permission.LackingPermissionException;
import me.Salt.Main;
import me.Salt.Permissions.Perm;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 * Provides information to the user about a user's profile.
 */
public class ProfileInfoCommand extends Command implements ICommand {
    public ProfileInfoCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }
    
    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event)
            throws LackingPermissionException, MissingDataException {
        if (!Main.salt.getJUserById(event.getAuthor().getId()).hasPermission(Perm.COMMAND_PROFILE_USE))
            throw new LackingPermissionException(
                    "You lack the " + Perm.COMMAND_PROFILE_USE.name() + " permission!"); //TODO replace with getPermission call
        if (cmd.getArgsLower().size() >= 1) {
            if (!Main.salt.getJUserById(event.getAuthor().getId()).hasPermission(Perm.COMMAND_PROFILE_SET_USER))
                throw new LackingPermissionException(
                        "You lack the " + Perm.COMMAND_PROFILE_SET_USER.name() + " permission!"); //TODO replace with getPermission call
            return event.getGuild()
                        .getMembers()
                        .stream()
                        .filter(member -> member.getEffectiveName().toLowerCase().contains(cmd.getArgsLower().get(0)))
                        .collect(Collectors.toList()) != null && event.getGuild()
                                                                      .getMembers()
                                                                      .stream()
                                                                      .filter(member -> member.getEffectiveName()
                                                                                              .toLowerCase()
                                                                                              .contains(
                                                                                                      cmd.getArgsLower()
                                                                                                         .get(0)))
                                                                      .collect(Collectors.toList())
                                                                      .size() == 1;
        } else return true;
    }
    
    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        long n = System.currentTimeMillis();
        Member m;
        StringBuilder sb = new StringBuilder();
        if (cmd.getArgsLower().size() > 0) m = e.getGuild()
                                                .getMembers()
                                                .stream()
                                                .filter(member -> member.getEffectiveName()
                                                                        .toLowerCase()
                                                                        .contains(cmd.getArgsLower().get(0)))
                                                .collect(Collectors.toList())
                                                .get(0);
        else m = e.getMember();
        m.getRoles().forEach(role -> sb.append(", " + role.getName()));
        sb.replace(0, 1, "");
        //TODO
        e.getChannel()
         .sendMessage(new EmbedBuilder().setTitle(
                 "Profile details for " + m.getEffectiveName() + "#" + m.getUser().getDiscriminator(), null)
                                        .addField("Name", m.getEffectiveName(), true)
                                        .addField("Nickname", m.getNickname() != null ? m.getNickname() : "None set",
                                                true)
                                        .addField("Current Game",
                                                m.getGame() != null ? m.getGame().toString() : "None set", true)
                                        .addField("Online Status", m.getOnlineStatus().name().toLowerCase(), true)
                                        .addField("Join Date", m.getJoinDate().toString(), true)
                                        .addField("Roles", sb.toString(), true)
                                        .addField("Colour", "View the embed's colour!", true)
                                        .setThumbnail(m.getUser().getAvatarUrl())
                                        .setColor(m.getColor())
                                        .appendDescription(
                                                "Generated in " + String.valueOf(System.currentTimeMillis() - n) + "ms")
                                        .setFooter("Requested by " + e.getAuthor().getName() + " at " + e.getMessage()
                                                                                                         .getCreationTime()
                                                                                                         .plusHours(1)
                                                                                                         .format(DateTimeFormatter.ISO_LOCAL_TIME),
                                                e.getAuthor().getAvatarUrl())
                                        .build())
         .queue();
    }
    
    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {
    }
}
