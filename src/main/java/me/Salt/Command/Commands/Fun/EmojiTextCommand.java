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

import me.Salt.Command.Command;
import me.Salt.Command.CommandContainer;
import me.Salt.Command.Container.CommandParser;
import me.Salt.Command.ICommand;
import me.Salt.Exception.Command.DisabledCommandException;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Exception.Permission.LackingPermissionException;
import me.Salt.Main;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;

/**
 * This command takes the inputted arguments, and sends a message to the authoring textchannel with the same input,
 * but converted to their Emoji equivalent.
 */
public class EmojiTextCommand extends Command implements ICommand {
    // TODO: 29/05/2017 Change to work with spaces
    private HashMap<String, String> emojis = new HashMap<>();
    
    public EmojiTextCommand(CommandContainer commandContainer) {
        super(commandContainer);
    }
    
    @Override
    public boolean preExecution(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent event)
            throws LackingPermissionException, MissingDataException, DisabledCommandException {
        emojis.put("a", "\uD83C\uDDE6");
        emojis.put("b", "\uD83C\uDDE7");
        emojis.put("c", "\uD83C\uDDE8");
        emojis.put("d", "\uD83C\uDDE9");
        emojis.put("e", "\uD83C\uDDEA");
        emojis.put("f", "\uD83C\uDDEB");
        emojis.put("g", "\uD83C\uDDEC");
        emojis.put("h", "\uD83C\uDDED");
        emojis.put("i", "\uD83C\uDDEE");
        emojis.put("j", "\uD83C\uDDEF");
        emojis.put("k", "\uD83C\uDDF0");
        emojis.put("l", "\uD83C\uDDF1");
        emojis.put("m", "\uD83C\uDDF2");
        emojis.put("n", "\uD83C\uDDF3");
        emojis.put("o", "\uD83C\uDDF4");
        emojis.put("p", "\uD83C\uDDF5");
        emojis.put("q", "\uD83C\uDDF6");
        emojis.put("r", "\uD83C\uDDF7");
        emojis.put("s", "\uD83C\uDDF8");
        emojis.put("t", "\uD83C\uDDF9");
        emojis.put("u", "\uD83C\uDDFA");
        emojis.put("v", "\uD83C\uDDFB");
        emojis.put("w", "\uD83C\uDDFC");
        emojis.put("x", "\uD83C\uDDFD");
        emojis.put("y", "\uD83C\uDDFE");
        emojis.put("z", "\uD83C\uDDFF");
        return true;
        // TODO: 29/05/2017 Convert to using Emoji-Java library
    }
    
    @Override
    public void execute(CommandParser.ParsedCommandContainer cmd, GuildMessageReceivedEvent e) {
        if (cmd.getArgsLower().size() > 0) {
            StringBuilder sb = new StringBuilder(); //TODO add size limit to second argument, also allow multiple arguments
            for (char c : cmd.getRawText().replaceFirst(Main.salt.getCmdPrefix() + cmd.getCmd(), "").toCharArray())
                if (emojis.containsKey(String.valueOf(c))) sb.append(emojis.get(
                        String.valueOf(c))); //TODO check if list actually /contains/ value before trying to add
                else if (String.valueOf(c) == " ") sb.append("⬛");
                else {
                    e.getChannel().sendMessage("You can only use alphabetical values!").queue();
                    return;
                }
            e.getChannel().sendMessage(sb.toString()).queue();
        } else e.getChannel().sendMessage("You need to enter an argument!").queue(); //TODO change to be more fitting
    }
    
    @Override
    public void postExecution(CommandParser.ParsedCommandContainer cmd) {
    }
}
