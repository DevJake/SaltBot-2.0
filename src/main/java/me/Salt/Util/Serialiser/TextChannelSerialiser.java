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

package me.Salt.Util.Serialiser;

import com.google.gson.*;
import net.dv8tion.jda.core.entities.TextChannel;

import java.lang.reflect.Type;

public class TextChannelSerialiser implements JsonSerializer<TextChannel> {
    @Override
    public JsonElement serialize(TextChannel src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getId());
    }
}
