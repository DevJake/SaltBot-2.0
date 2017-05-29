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
package me.Salt.Event.util;

import me.Salt.Event.jevent.*;
import me.Salt.Event.jevent.game.GameManagerRegisterGameEvent;
import me.Salt.Event.jevent.game.cardsagainstdiscord.*;

public abstract class JListenerAdapter implements JEventListener {
    public void onStartup(SaltStartupEvent e) { }
    
    public void onShutdown(SaltShutdownEvent e) { }
    
    public void onCommandPreExecute(CommandPreExecuteEvent e) { }
    
    public void onCommandExecute(CommandExecuteEvent e) { }
    
    public void onCommandPostExecute(CommandPostExecuteEvent e) { }
    
    public void onCommandRegister(CommandRegisterEvent e) { }
    
    public void onLanguageRegister(LanguageRegisterEvent e) { }
    
    public void onPermissionRegister(PermissionRegisterEvent e) { }
    
    public void onCaDGameAddPlayer(CaDGameAddPlayerEvent e) { }
    
    public void onCaDGameAddPreviousBlackCard(CaDGameAddPreviousBlackCardEvent e) { }
    
    public void onCaDGameRemovePlayer(CaDGameRemovePlayerEvent e) { }
    
    public void onCaDGameUpdateActive(CaDGameUpdateActiveEvent e) { }
    
    public void onCaDManagerAddEmbed(CaDManagerAddEmbedEvent e) { }
    
    public void onCaDManagerInvokeState(CaDManagerInvokeStateEvent e) { }
    
    public void onCaDManagerModifyHandler(CaDManagerModifyHandlerEvent e) { }
    
    public void onCaDManagerRegisterHandler(CaDManagerRegisterHandlerEvent e) { }
    
    public void onGenericCaD(GenericCaDEvent e) { }
    
    public void onGenericCommand(GenericCommandEvent e) { }
    
    public void onGenericCaDGame(GenericCaDGameEvent e) { }
    
    public void onGenericCaDManager(GenericCaDManagerEvent e) { }
    
    public void onGameManagerRegisterGame(GameManagerRegisterGameEvent e) { }
    
    @Override
    public void onEvent(JEvent event) {
        //        ##########################
        //        ###   SPECIFIC EVENTS  ###
        //        ##########################
        if (event instanceof SaltStartupEvent) onStartup((SaltStartupEvent) event);
        else if (event instanceof SaltShutdownEvent) onShutdown((SaltShutdownEvent) event);
        else if (event instanceof CommandPreExecuteEvent) onCommandPreExecute((CommandPreExecuteEvent) event);
        else if (event instanceof CommandExecuteEvent) onCommandExecute((CommandExecuteEvent) event);
        else if (event instanceof CommandPostExecuteEvent) onCommandPostExecute((CommandPostExecuteEvent) event);
        else if (event instanceof CommandRegisterEvent) onCommandRegister((CommandRegisterEvent) event);
        else if (event instanceof LanguageRegisterEvent) onLanguageRegister((LanguageRegisterEvent) event);
        else if (event instanceof PermissionRegisterEvent) onPermissionRegister((PermissionRegisterEvent) event);
            //    #############################
            //    ###  SPECIFIC CAD EVENTS  ###
            //    #############################
        else if (event instanceof CaDGameAddPlayerEvent) onCaDGameAddPlayer((CaDGameAddPlayerEvent) event);
        else if (event instanceof CaDGameAddPreviousBlackCardEvent)
            onCaDGameAddPreviousBlackCard((CaDGameAddPreviousBlackCardEvent) event);
        else if (event instanceof CaDGameRemovePlayerEvent) onCaDGameRemovePlayer((CaDGameRemovePlayerEvent) event);
        else if (event instanceof CaDGameUpdateActiveEvent) onCaDGameUpdateActive((CaDGameUpdateActiveEvent) event);
        else if (event instanceof CaDManagerAddEmbedEvent) onCaDManagerAddEmbed((CaDManagerAddEmbedEvent) event);
        else if (event instanceof CaDManagerInvokeStateEvent)
            onCaDManagerInvokeState((CaDManagerInvokeStateEvent) event);
        else if (event instanceof CaDManagerModifyHandlerEvent)
            onCaDManagerModifyHandler((CaDManagerModifyHandlerEvent) event);
        else if (event instanceof CaDManagerRegisterHandlerEvent)
            onCaDManagerRegisterHandler((CaDManagerRegisterHandlerEvent) event);
        else if (event instanceof GameManagerRegisterGameEvent)
            onGameManagerRegisterGame((GameManagerRegisterGameEvent) event);
        //        ##########################
        //        ###   GENERIC EVENTS   ###
        //        ##########################
        if (event instanceof GenericCaDEvent) onGenericCaD((GenericCaDEvent) event);
        else if (event instanceof GenericCommandEvent) onGenericCommand((GenericCommandEvent) event);
        //        ##########################
        //        ### GENERIC CAD EVENTS ###
        //        ##########################
        if (event instanceof GenericCaDGameEvent) onGenericCaDGame((GenericCaDGameEvent) event);
        if (event instanceof GenericCaDManagerEvent) onGenericCaDManager((GenericCaDManagerEvent) event);
    }
}
