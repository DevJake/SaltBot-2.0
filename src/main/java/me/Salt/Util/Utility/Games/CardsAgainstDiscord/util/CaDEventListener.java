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

import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.Player;
import net.dv8tion.jda.core.events.message.priv.react.GenericPrivateMessageReactionEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * This class receives Discord events, and handles them in accordance to any active Cards Against Discord games.
 * <p>
 * This class' purpose is to react to embed reaction changes, and to check if the message is related to a Cards
 * Against Discord game instance.
 */
public class CaDEventListener extends ListenerAdapter {
    @Override
    public void onGenericPrivateMessageReaction(GenericPrivateMessageReactionEvent event) {
        if (event.getUser().isBot()) return;
        final String emoteName = event.getReaction().getEmote().getName();
        if (CaDGameManager.getEmbeds().containsKey(event.getMessageId())) {
            if (!CaDGameManager.getEmbeds().get(event.getMessageId()).isHandled()) {
                switch (CaDGameManager.getEmbeds().get(event.getMessageId()).getResponseExpected()) {
                    case INVITE_RESPOND:
                        handleInviteRespond(event.getReactionEmote().getName(), event);
                        break;
                    case CZAR_CARD_SELECT:
                        handleCardSelect(event.getReactionEmote().getName(), event);
                        break;
                    case USER_CARD_SELECT:
                        break;
                }
            }
        }
    }
    
    /**
     * This method is designed to handle and respond to game invites.
     *
     * @param emoteName String - The emote that was modified
     * @param event     {@link GenericPrivateMessageReactionEvent} - The event instance that triggered this method's call
     */
    private void handleInviteRespond(String emoteName, GenericPrivateMessageReactionEvent event) {
        if (emoteName.equals("✅")) {
            if (!CaDGameManager.getEmbeds()
                               .get(event.getMessageId())
                               .getCaDGameHandler()
                               .addPlayer(new Player(event.getUser()))) {
                CaDGameManager.getEmbeds().get(event.getMessageId()).setHandled(true);
                event.getChannel().sendMessage("Added you to the game!").queue();
            } else {
                // TODO: 28/05/2017 log as user already exists
                System.out.println("User already added!");
            }
        }
    }
    
    /**
     * This method is designed to handle and respond the card selection phase of a game.
     *
     * @param emoteName String - The emote that was modified
     * @param event     {@link GenericPrivateMessageReactionEvent} - The event instance that triggered this method's call
     */
    private void handleCardSelect(String emoteName, GenericPrivateMessageReactionEvent event) {
    }
}
