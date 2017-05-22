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

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.PlayerStats;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Player.R6Player;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Util.Platform;
import net.dv8tion.jda.core.entities.User;

public class R6PlayerImpl implements R6Player {
    private String username;
    private Platform platform;
    private String ubisoftId;
    private long indexedAt;
    private long updateAt;
    private PlayerStats stats;
    private User tiedUser; //TODO look into tying an account to a user

    @Override
    public String toString() {
        return "R6PlayerImpl{" +
                "username='" + username + '\'' +
                ", platform=" + platform +
                ", ubisoftId='" + ubisoftId + '\'' +
                ", indexedAt=" + indexedAt +
                ", updateAt=" + updateAt +
                ", stats=" + stats.toString() +
                ", tiedUser=" + tiedUser +
                '}';
    }

    public R6PlayerImpl(String username, Platform platform, String ubisoftId, long indexedAt, long updateAt, PlayerStats stats) {

        this.username = username;
        this.platform = platform;
        this.ubisoftId = ubisoftId;
        this.indexedAt = indexedAt;
        this.updateAt = updateAt;
        this.stats = stats;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Platform getPlatform() {
        return platform;
    }

    @Override
    public PlayerStats getStats() {
        return stats;
    }

    @Override
    public String getUbisoftId() {
        return ubisoftId;
    }

    @Override
    public long getIndexedAt() {
        return indexedAt;
    }

    @Override
    public long getUpdateAt() {
        return updateAt;
    }
}
