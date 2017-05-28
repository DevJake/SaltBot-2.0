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

public class ResponseContainer {
    private boolean handled = false;
    private ResponseExpector responseExpected;
    private CaDGameHandler caDGameHandler;
    
    public ResponseContainer(ResponseExpector responseExpected, CaDGameHandler caDGameHandler) {
        this.responseExpected = responseExpected;
        this.caDGameHandler = caDGameHandler;
    }
    
    public CaDGameHandler getCaDGameHandler() {
        return caDGameHandler;
    }
    
    public boolean isHandled() {
        return handled;
    }
    
    public void setHandled(boolean handled) {
        this.handled = handled;
    }
    
    public ResponseExpector getResponseExpected() {
        return responseExpected;
    }
    
    public enum ResponseExpector {
        CZAR_CARD_SELECT,
        USER_CARD_SELECT,
        INVITE_RESPOND,
    }
}
