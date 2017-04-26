/*
 *
 *  * Copyright (c) 2017 DevJake
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.Impl;

import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.PlayerStats;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Entities.R6Player;
import me.Salt.Util.Utility.StatGrabber.Rainbow6.Util.Platform;
import net.dv8tion.jda.core.entities.User;

import java.time.LocalDateTime;

public class R6PlayerImpl implements R6Player {
    private String username;
    private Platform platform;
    private String ubisoft_id;
    private LocalDateTime indexed_at;
    private LocalDateTime update_at;
    private PlayerStats stats;
    private User tiedUser; //TODO look into tying an account to a user
}
