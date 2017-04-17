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

package me.Salt.Command.Commands;

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

import java.awt.*;

/**
 * Project title: SaltBot-2.0
 * Authored by Salt on 10/04/2017.
 */
public class PingCommand extends Command implements ICommand {

    public PingCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, Event event) {
        return true;
    }

    @Override
    public void executeGuildMessageEvent(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        long n = System.currentTimeMillis();
        e.getChannel().sendMessage(new EmbedBuilder().appendDescription("Calculating...").setColor(Color.BLUE).build()).queue(m -> m.editMessage(new EmbedBuilder().setDescription("\uD83C\uDFD3 Pong! (" + (System.currentTimeMillis()-n) + "ms)").setColor(Color.BLUE).build()).queue());
    }

    @Override
    public void executePrivateMessageEvent(CommandParser.ParsedCommandContainer cmd, PrivateMessageReceivedEvent e) {

    }


    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }
}
