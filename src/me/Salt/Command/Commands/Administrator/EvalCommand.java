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

package me.Salt.Command.Commands.Administrator;


import bsh.EvalError;
import bsh.Interpreter;
import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Exception.Command.DisabledCommandException;
import me.Salt.Exception.Evaluation.EvaluationReturnedNullException;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Exception.Permission.LackingPermissionException;
import me.Salt.Main;
import me.Salt.Util.Language.LangCode;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.format.DateTimeFormatter;

public class EvalCommand extends Command implements ICommand {
    public EvalCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) throws LackingPermissionException, MissingDataException, DisabledCommandException {
        if (cmd.getArgsLower().size() <= 0) throw new MissingDataException("You are missing required parameters!");
        if (!event.getAuthor().getId().equals("112633500447838208"))
            throw new LackingPermissionException("You are lacking the correct permissions to use this command!");
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        Interpreter bsh = new Interpreter();
        StringBuilder sb = new StringBuilder();

        cmd.getArgsUpper().forEach(n -> sb.append(n).append(" "));

        try {
            bsh.set("e", e);
            Object n = bsh.eval("import java.util.*; import java.io.*; import me.Salt.*; import net.dv8tion.*; " + sb.toString());
            if (n == null) throw new EvaluationReturnedNullException("No data was returned by the evaluation!");
            e.getChannel().sendMessage(
                    new EmbedBuilder()
                            .addField("Evaluation was successful! ✅", n.toString(), false)
                            .setColor(Main.salt.getEmbedColour())
                            .setFooter("Requested by " + e.getAuthor().getName() + " at " + e.getMessage().getCreationTime().plusHours(1).format(DateTimeFormatter.ISO_LOCAL_TIME), e.getAuthor().getAvatarUrl())
                            .build()).queue();
        } catch (EvalError evalError) {
            e.getChannel().sendMessage(
                    new EmbedBuilder()
                            .addField("Evaluation was unsuccessful! ❌", evalError.toString(), false)
                            .setColor(Main.salt.getEmbedColour())
                            .setFooter("Requested by " + e.getAuthor().getName() + " at " + e.getMessage().getCreationTime().plusHours(1).format(DateTimeFormatter.ISO_LOCAL_TIME), e.getAuthor().getAvatarUrl())
                            .build()).queue();
        } catch (EvaluationReturnedNullException e1) {
            e.getChannel().sendMessage(
                    new EmbedBuilder()
                            .addField("Evaluation was unsuccessful! ❌", e1.getMessage(), false)
                            .setColor(Main.salt.getEmbedColour())
                            .setFooter("Requested by " + e.getAuthor().getName() + " at " + e.getMessage().getCreationTime().plusHours(1).format(DateTimeFormatter.ISO_LOCAL_TIME), e.getAuthor().getAvatarUrl())
                            .build()).queue();
        }
        //TODO add evaluation time calculator
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
