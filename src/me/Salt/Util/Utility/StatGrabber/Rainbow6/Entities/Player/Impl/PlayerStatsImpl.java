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

package me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.Impl;

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.PlayerGamemodeStats;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.PlayerOverallStats;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.PlayerProgressionStats;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.PlayerStats;

public class PlayerStatsImpl implements PlayerStats {
    private PlayerGamemodeStats ranked;
    private PlayerGamemodeStats casual;
    private PlayerOverallStats overall;
    private PlayerProgressionStats progression;

    public PlayerStatsImpl(PlayerGamemodeStats ranked, PlayerGamemodeStats casual, PlayerOverallStats overall, PlayerProgressionStats progression) {
        this.ranked = ranked;
        this.casual = casual;
        this.overall = overall;
        this.progression = progression;
    }

    public PlayerGamemodeStats getRanked() {
        return ranked;
    }

    public PlayerGamemodeStats getCasual() {
        return casual;
    }

    public PlayerOverallStats getOverall() {
        return overall;
    }

    public PlayerProgressionStats getProgression() {
        return progression;
    }
}
