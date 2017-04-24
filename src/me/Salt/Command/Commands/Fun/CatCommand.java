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

package me.Salt.Command.Commands.Fun;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class CatCommand extends Command implements ICommand {
    public CatCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }

    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event) {
        return true;
    }

    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        URL url = null;
        Scanner s = null;

        try {
            url = new URL("http://random.cat/meow");
            s = new Scanner(url.openStream());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Gson gson = new Gson();
        if (s != null) {
            e.getChannel().sendMessage(gson.fromJson(s.next(), Cat.class).file).queue();
        }
    }

    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {

    }

    private class Cat {
        private String file = "";
    }
}
