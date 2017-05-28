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
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.Player;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.WhiteCard;
import me.Salt.Util.Utility.Games.GameManager;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.*;

public class CaDGameManager extends ListenerAdapter {
    private static Set<Map.Entry<PlayState, HandlerContainer>> handlers = new HashSet<>();
    private static HashMap<String, ResponseContainer> embeds = new HashMap<>();
    private static HashMap<String, CaDGameHandler> idLookup = new HashMap<>(); //User ids and their respective game instance
    /*
    'embeds' HashMap is a list of IDs for embeds that are expecting/awaiting responses. Any listed here are 'active'.
     The corresponding ResponseExpector is used to determine what sort of response we're looking for, as certain embeds should only allow for control by one user (such as a Card Czar) whilst others may demand control from multiple users.

    When receiving an incoming reaction, we compare the message's ID to this list and see if we
    are meant to respond to it; i.e., is the message that of a CaD game?
    */
    
    public static HashMap<String, ResponseContainer> getEmbeds() {
        return embeds;
    }
    
    public static void addToEmbeds(String messageId, ResponseContainer.ResponseExpector responseExpected,
                                   CaDGameHandler caDGameHandler) {
        if (!embeds.containsKey(messageId))
            embeds.put(messageId, new ResponseContainer(responseExpected, caDGameHandler));
    }
    
    public static void registerHandler(CaDGameHandler handler) {
        if (GameManager.hasGameOfType(handler.getOwner().getUser(), CaDGameHandler.class))
            handlers.add(new AbstractMap.SimpleEntry<>(PlayState.IDLE, new HandlerContainer(handler, true)));
        idLookup.put(handler.getOwner().getUser().getId(), handler);
        System.out.println("Registered new handler!");
        invoke();
    }
    
    public static void modifyHandler(String ownerId, PlayState playState) {
        if (idLookup.containsKey(ownerId)) {
            System.out.println("Found ID");
            for (Map.Entry<PlayState, HandlerContainer> containerEntry : handlers) {
                if (Objects.equals(containerEntry.getValue().getHandler().getOwner().getUser().getId(), ownerId)) {
                    System.out.println("Found equal IDs");
                    HandlerContainer h = containerEntry.getValue();
                    handlers.remove(containerEntry);
                    handlers.add(new AbstractMap.SimpleEntry<>(playState, h));
                    break;
                }
            }
            invoke();
        }
    }
    
    private static void invoke() {
        handlers.forEach(containerEntry -> {
            if (containerEntry.getValue().shouldInvoke()) {
                switch (containerEntry.getKey()) {
                    case IDLE:
                        invokeIdle(containerEntry.getValue().getHandler());
                        break;
                    case STARTING:
                        invokeStart(containerEntry.getValue().getHandler());
                        break;
                    case FINISHING:
                        invokeFinish(containerEntry.getValue().getHandler());
                        break;
                    case LEADERBOARDS:
                        invokeLeaderboards(containerEntry.getValue().getHandler());
                        break;
                    case CARD_SELECTION:
                        invokeCardSelection(containerEntry.getValue().getHandler());
                        break;
                    case CARD_SUBMITTING:
                        invokeCardSubmitting(containerEntry.getValue().getHandler());
                        break;
                }
            }
        });
    }
    
    public static void unregisterHandler(CaDGameHandler handler) { }
    
    private static void invokeStart(CaDGameHandler toHandle) {
        System.out.println(toHandle.getAllPlayers());
        EmbedBuilder eb;
        StringBuilder sb;
        for (Player player : toHandle.getAllPlayers()) {
            for (int i = 0; i < toHandle.getCardCount(); i++) {
                player.addCard(CaDUtil.getRandomWhiteCard());
            }
            eb = new EmbedBuilder().setTitle("Cards Against Humanity", null);
            sb = new StringBuilder();
            for (WhiteCard card : player.getCards()) {
                sb.append(">`").append(card.getText()).append("`").append("\n");
            }
            eb.addField("Your White cards (" + toHandle.getCardCount() + ")", sb.toString(), false);
            eb.appendDescription("Be sure not to show anyone else these cards!");
            player.getUser().getPrivateChannel().sendMessage(eb.build()).queue();
            // TODO: 27/05/2017 set card czar}
        }
    }
    
    private static void invokeIdle(CaDGameHandler toHandle) {
        System.out.println("Invoked IDLE!");
    }
    
    private static void invokeCardSubmitting(CaDGameHandler toHandle) {
    }
    
    private static void invokeCardSelection(CaDGameHandler toHandle) {
    }
    
    private static void invokeLeaderboards(CaDGameHandler toHandle) {
    }
    
    private static void invokeFinish(CaDGameHandler toHandle) {
    }
    
    public Set<Map.Entry<PlayState, HandlerContainer>> getHandlers() {
        return handlers;
    }
    
    public enum PlayState {
        IDLE,
        //Not started, but merely created
        STARTING,
        //Game starting. Users being assigned cards
        CARD_SUBMITTING,
        //Users are submitting white cards in response to a black card
        CARD_SELECTION,
        //Card Czar is selecting a winning card/card-combo
        LEADERBOARDS,
        //Displaying scoring information to individuals
        FINISHING, //Finishing the game, removing users
    }
    
    

    /*
    Receive incoming events. When event is a reaction, check if the message ID is registered as linked to a current
    game. Call upon corresponding methods.
     */
}
