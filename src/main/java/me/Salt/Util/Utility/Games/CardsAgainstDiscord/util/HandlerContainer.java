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
package me.Salt.Util.Utility.Games.CardsAgainstDiscord.util;

import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.CaDGameHandler;

public class HandlerContainer {
    private CaDGameHandler handler;
    private boolean shouldInvoke;
    
    public HandlerContainer(CaDGameHandler handler, boolean shouldInvoke) {
        this.handler = handler;
        this.shouldInvoke = shouldInvoke;
    }
    
    public CaDGameHandler getHandler() {
        return handler;
    }
    
    public boolean shouldInvoke() {
        return shouldInvoke;
    }
    
    public void setShouldInvoke(boolean shouldInvoke) {
        this.shouldInvoke = shouldInvoke;
    }
}




