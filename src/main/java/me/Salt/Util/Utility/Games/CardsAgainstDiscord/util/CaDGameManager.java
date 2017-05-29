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

import com.vdurmont.emoji.EmojiLoader;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import me.Salt.Event.jevent.game.cardsagainstdiscord.CaDManagerAddEmbedEvent;
import me.Salt.Event.jevent.game.cardsagainstdiscord.CaDManagerInvokeStateEvent;
import me.Salt.Event.jevent.game.cardsagainstdiscord.CaDManagerModifyHandlerEvent;
import me.Salt.Event.jevent.game.cardsagainstdiscord.CaDManagerRegisterHandlerEvent;
import me.Salt.Event.util.EventInitiator;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.BlackCard;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.CaDGameHandler;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.Player;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.WhiteCard;
import me.Salt.Util.Utility.Games.GameManager;
import me.salt.annotations.Metrics;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Metrics(id = "An ID")
public class CaDGameManager extends ListenerAdapter {
    private static Set<Map.Entry<PlayState, HandlerContainer>> handlers = new HashSet<>();
    private static HashMap<String, ResponseContainer> embeds = new HashMap<>();
    private static HashMap<String, CaDGameHandler> idLookup = new HashMap<>(); /*User ids and their respective game instance */
    
    public static HashMap<String, ResponseContainer> getEmbeds() { return embeds; }
    
    public static void addToEmbeds(String messageId, ResponseContainer.ResponseExpector responseExpected,
                                   CaDGameHandler caDGameHandler) {
        if (!embeds.containsKey(messageId))
            //EventInitiator.fire(new CaDManagerAddEmbedEvent(caDGameHandler, embeds.get(messageId).getResponseExpected(), responseExpected, messageId));
        embeds.put(messageId, new ResponseContainer(responseExpected, caDGameHandler));
    }
    
    public static void registerHandler(CaDGameHandler handler) {
        if (GameManager.hasGameOfType(handler.getOwner().getUser(), CaDGameHandler.class))
            handlers.add(new AbstractMap.SimpleEntry<>(PlayState.IDLE, new HandlerContainer(handler, true)));
        idLookup.put(handler.getOwner().getUser().getId(), handler);
        System.out.println("Registered new handler!");
        EventInitiator.fire(new CaDManagerRegisterHandlerEvent(handler));
        invoke();
    }
    
    public static void modifyHandler(String ownerId, PlayState playState) {
        if (idLookup.containsKey(ownerId)) {
            System.out.println("Found ID");
            for (Map.Entry<PlayState, HandlerContainer> containerEntry : handlers) {
                if (Objects.equals(containerEntry.getValue().getHandler().getOwner().getUser().getId(), ownerId)) {
                    System.out.println("Found equal IDs");
                    HandlerContainer h = containerEntry.getValue();
                    PlayState oldPlayState = containerEntry.getKey();
                    handlers.remove(containerEntry);
                    handlers.add(new AbstractMap.SimpleEntry<>(playState, h));
                    EventInitiator.fire(new CaDManagerModifyHandlerEvent(ownerId, h, oldPlayState, playState));
                    break;
                }
            }
            invoke();
        }
    }
    
    private static void invoke() {
        handlers.forEach(containerEntry -> {
            if (containerEntry.getValue().shouldInvoke()) {
                EventInitiator.fire(new CaDManagerInvokeStateEvent(containerEntry.getValue().getHandler(),
                        containerEntry.getKey()));
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
    
    public static void unregisterHandler(CaDGameHandler handler) { /* TODO: 29/05/2017 */ }
    
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
            eb.setFooter("The game will start in 10 seconds", null);
            player.getUser().openPrivateChannel();
            player.getUser().getPrivateChannel().sendMessage(eb.build()).queue();
            // TODO: 27/05/2017 set card czar}
        }
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
        executorService.schedule(() -> {
            if (toHandle.getCardCzar() == null) {
                toHandle.setCardCzar(toHandle.getAllPlayers().get(0)); //Set to first user
            } else {
                if (toHandle.getAllPlayers().indexOf(toHandle.getCardCzar()) >= toHandle.getAllPlayers().size())
                    toHandle.setCardCzar(toHandle.getAllPlayers().get(0)); //Reset to first list member
                else toHandle.setCardCzar(toHandle.getAllPlayers()
                                                  .get(toHandle.getAllPlayers()
                                                               .indexOf(
                                                                       toHandle.getCardCzar()))); //Set to next member of list
            }
            toHandle.getCardCzar()
                    .getUser()
                    .openPrivateChannel()
                    .queue(privateChannel -> privateChannel.sendMessage(
                            "You have been marked as this round's Card Czar! In 25 seconds, you will be provided with a " + "list of cards to select from. ")
                                                           .queue());
        }, 10, TimeUnit.SECONDS);
        executorService.schedule(() -> {
            BlackCard card = CaDUtil.getRandomBlackCard();
            toHandle.addPreviousBlackCard(card);
            toHandle.getAllPlayersNonCzar()
                    .forEach(player -> player.getUser().openPrivateChannel().queue(privateChannel -> {
                        StringBuilder sb0 = new StringBuilder();
                        EmbedBuilder eb0 = new EmbedBuilder().addField("Black Card", card.getText(), false)
                                                             .addField("Required cards",
                                                                     String.valueOf(card.getBlankFields()), false);
                        List<WhiteCard> cards = player.getCards();
                        for (int i = 0; i < cards.size(); i++) {
                            WhiteCard card0 = cards.get(i);
                            sb0.append("(").append((i + 1)).append(") `").append(card0.getText()).append("`\n");
                        }
                        eb0.addField("Your cards", sb0.toString(), false);
                        privateChannel.sendMessage(eb0.build()).queue(message -> {
                            for (int i = 0; i < player.getCards().size(); i++) {
                                switch (i) {
                                    case 0:
                                        message.addReaction(EmojiManager.getForAlias("one").getUnicode()).queue();
                                        break;
                                    case 1:
                                        message.addReaction(EmojiManager.getForAlias("two").getUnicode()).queue();
                                        break;
                                    case 2:
                                        message.addReaction(EmojiManager.getForAlias("three").getUnicode()).queue();
                                        break;
                                    case 3:
                                        message.addReaction(EmojiManager.getForAlias("four").getUnicode()).queue();
                                        break;
                                    case 4:
                                        message.addReaction(EmojiManager.getForAlias("five").getUnicode()).queue();
                                        break;
                                    case 5:
                                        message.addReaction(EmojiManager.getForAlias("six").getUnicode()).queue();
                                        break;
                                    case 6:
                                        message.addReaction(EmojiManager.getForAlias("seven").getUnicode()).queue();
                                        break;
                                    case 7:
                                        message.addReaction(EmojiManager.getForAlias("eight").getUnicode()).queue();
                                        break;
                                    case 8:
                                        message.addReaction(EmojiManager.getForAlias("nine").getUnicode()).queue();
                                        break;
                                    case 9:
                                        message.addReaction(EmojiManager.getForAlias("keycap_ten").getUnicode())
                                               .queue();
                                        break;
                                }
                            }
                            addToEmbeds(message.getId(), ResponseContainer.ResponseExpector.USER_CARD_SELECT, toHandle);
                        });
                    }));
            modifyHandler(toHandle.getOwner().getUser().getId(), PlayState.CARD_SELECTION);
        }, 35, TimeUnit.SECONDS);
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
}
