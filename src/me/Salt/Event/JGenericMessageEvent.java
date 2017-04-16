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

package me.Salt.Event;

import me.Salt.Logging.JLogger;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Salt001 on 09/04/2017.
 */
public class JGenericMessageEvent implements IEvent {
    static Reaction r1 = (r, m) -> r.forEach((p) -> m.addReaction(p).queue());

    public static void process(GenericMessageEvent event) {

        //new CommandParser().parse(event.getMessage().getRawContent(), Main.salt.getCmdPrefix());

        if (event.getMessage().getRawContent().toLowerCase().contains("xd") || event.getMessage().getRawContent().toLowerCase().contains("eggs d")) {
            r1.add(Arrays.asList("\uD83E\uDD5A", "\uD83C\uDF46"), event.getMessage());
            JLogger.writeToConsole(JLogger.Level.INFO, "Reacted to: \"" + event.getMessage().getRawContent() + "\" with unicode '\\uD83E\\uDD5A' '\\uD83C\\uDF46' (\uD83E\uDD5A \uD83C\uDF46)");
        }
    }

    private interface Reaction {
        void add(List<String> reactions, Message m);
    }
}

