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

package me.Salt.Util.Utility.StatGrabber.Rainbow6.Util.Serialiser;

import com.google.gson.*;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.Impl.*;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Util.Gamemode;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Util.Platform;

import java.lang.reflect.Type;

public class R6PlayerDeserialiaser implements JsonDeserializer<R6PlayerImpl> {

    @Override
    public R6PlayerImpl deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject player = json.getAsJsonObject().getAsJsonObject("player");
        JsonObject stats = json.getAsJsonObject().getAsJsonObject("player").getAsJsonObject("stats");
        JsonObject ranked = stats.get("ranked").getAsJsonObject();
        JsonObject casual = stats.getAsJsonObject("casual");
        JsonObject overall = stats.getAsJsonObject("overall");
        JsonObject progression = stats.getAsJsonObject("progression");

        PlayerStatsImpl pstats = new PlayerStatsImpl(
                new PlayerGamemodeStatsImpl(
                        Gamemode.RANKED,
                        ranked.get("has_played").getAsBoolean(),
                        ranked.get("wins").getAsInt(),
                        ranked.get("losses").getAsInt(),
                        ranked.get("wlr").getAsDouble(),
                        ranked.get("kills").getAsInt(),
                        ranked.get("deaths").getAsInt(),
                        ranked.get("kd").getAsDouble(),
                        ranked.get("playtime").getAsInt()
                ),

                new PlayerGamemodeStatsImpl(
                        Gamemode.CASUAL,
                        casual.get("has_played").getAsBoolean(),
                        casual.get("wins").getAsInt(),
                        casual.get("losses").getAsInt(),
                        casual.get("wlr").getAsDouble(),
                        casual.get("kills").getAsInt(),
                        casual.get("deaths").getAsInt(),
                        casual.get("kd").getAsDouble(),
                        casual.get("playtime").getAsInt()
                ),

                new PlayerOverallStatsImpl(
                        overall.get("revives").getAsInt(),
                        overall.get("suicides").getAsInt(),
                        overall.get("reinforcements_deployed").getAsInt(),
                        overall.get("barricades_built").getAsInt(),
                        overall.get("steps_moved").getAsInt(),
                        overall.get("bullets_fired").getAsInt(),
                        overall.get("bullets_hit").getAsInt(),
                        overall.get("headshots").getAsInt(),
                        overall.get("melee_kills").getAsInt(),
                        overall.get("penetration_kills").getAsInt(),
                        overall.get("assists").getAsInt() //TODO add an ability to view info about what each property means
                        ),

                new PlayerProgressionStatsImpl(
                        progression.get("level").getAsInt(),
                        progression.get("xp").getAsInt()
                )
        );

        return new R6PlayerImpl(player.get("username").getAsString(), Platform.valueOf(player.get("platform").getAsString().toUpperCase()), player.get("ubisoft_id").getAsString(),0, 0, pstats);
    }
}
