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

import com.vdurmont.emoji.EmojiManager;
import me.Salt.Event.jevent.game.cardsagainstdiscord.CaDManagerInvokeStateEvent;
import me.Salt.Event.jevent.game.cardsagainstdiscord.CaDManagerModifyHandlerEvent;
import me.Salt.Event.jevent.game.cardsagainstdiscord.CaDManagerRegisterHandlerEvent;
import me.Salt.Event.util.EventInitiator;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.*;
import me.Salt.Util.Utility.Games.GameManager;
import me.salt.annotations.Metrics;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This class is designed to handle and manage the control flow of every registered instance of {@link CaDGameHandler}.
 */
@Metrics(id = "An ID")
public class CaDGameManager extends ListenerAdapter {
    /**
     * A Set of Map entries that associate {@link PlayState}s with {@link HandlerContainer} instances.
     */
    private static Set<Map.Entry<PlayState, HandlerContainer>> handlers = new HashSet<>();
    /**
     * A HashMap containing Message ID's, and their respective expected response, contained in a
     * {@link ResponseContainer}.
     */
    private static HashMap<String, ResponseContainer> embeds = new HashMap<>();
    /**
     * A HashMap containing game owner ID's, and their respective {@link CaDGameHandler} game instance
     */
    private static HashMap<String, CaDGameHandler> idLookup = new HashMap<>(); /*User ids and their respective game instance */
    private static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);
    
    public static HashMap<String, ResponseContainer> getEmbeds() { return embeds; }
    
    /**
     * When issuing a game-related message, it is likely it will require a response from the user. To do this, the ID
     * of the message is recorded and, upon the modification of a message reaction, the ID of the message is checked.
     * This method allows for a message to be registered as requiring action, by specifying the ID of the message,
     * the {@link me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.ResponseContainer.ResponseExpector}, and the
     * {@link CaDGameHandler} instance it is associated with.
     *
     * @param messageId        String - The ID of the message
     * @param responseExpected {@link me.Salt.Util.Utility.Games.CardsAgainstDiscord.util.ResponseContainer.ResponseExpector} - The response
     *                         we expect to gain from the message
     * @param caDGameHandler   {@link CaDGameHandler} - The Cards Against Discord game instance that this message is
     *                         linked to
     */
    public static void addToEmbeds(String messageId, ResponseContainer.ResponseExpector responseExpected,
                                   CaDGameHandler caDGameHandler) {
        if (!embeds.containsKey(messageId))
            //EventInitiator.fire(new CaDManagerAddEmbedEvent(caDGameHandler, embeds.get(messageId).getResponseExpected(), responseExpected, messageId));
            embeds.put(messageId, new ResponseContainer(responseExpected, caDGameHandler));
    }
    
    /**
     * This method is used to register a {@link CaDGameHandler} instance to this class.
     *
     * @param handler {@link CaDGameHandler} - The game instance to be registered
     */
    public static void registerHandler(CaDGameHandler handler) {
        if (GameManager.hasGameOfType(handler.getOwner().getUser(), CaDGameHandler.class))
            handlers.add(new AbstractMap.SimpleEntry<>(PlayState.IDLE, new HandlerContainer(handler, true)));
        idLookup.put(handler.getOwner().getUser().getId(), handler);
        System.out.println("Registered new handler!");
        EventInitiator.fire(new CaDManagerRegisterHandlerEvent(handler));
        invoke();
    }
    
    /**
     * This method allows for a {@link CaDGameHandler} instance to have its {@link PlayState} updated, by specifying
     * the ID of the {@link CaDGameHandler#owner}.
     *
     * @param ownerId   String - The ID of the game's owner
     * @param playState {@link PlayState} - The new PlayState to update the game with
     */
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
    
    /**
     * This method takes in the ID of a game owner, and returns the corresponding {@link HandlerContainer} associated
     * with the game.
     *
     * @param ownerId String - The ID of the game's owner
     * @return {@link HandlerContainer} - The HandlerContainer associated with the specified individual's game instance
     */
    public static HandlerContainer getHandlerContainer(String ownerId) {
        if (idLookup.containsKey(ownerId)) {
            for (Map.Entry<PlayState, HandlerContainer> containerEntry : handlers) {
                if (Objects.equals(containerEntry.getValue().getHandler().getOwner().getUser().getId(), ownerId)) {
                    return containerEntry.getValue();
                }
            }
        }
        return null;
    }
    
    /**
     * This method takes in the ID of a game owner, and returns a boolean representing if their game has an
     * associated {@link HandlerContainer}.
     *
     * @param ownerId String - The ID of the game's owner
     * @return Boolean - If the user has an associated instance of HandlerContainer
     */
    public static boolean hasHandlerContainer(String ownerId) {
        if (idLookup.containsKey(ownerId)) {
            for (Map.Entry<PlayState, HandlerContainer> containerEntry : handlers) {
                if (Objects.equals(containerEntry.getValue().getHandler().getOwner().getUser().getId(), ownerId)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * This method is called when registering and updating game handlers. It calls upon the
     * {@link HandlerContainer#shouldInvoke()} method to determine if the current handler should be interacted with
     * and - if true - switches through its contained {@link PlayState}. The corresponding methods are then called
     * upon, to handle the game's state changes.
     */
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
                    case CZAR_CARD_SELECTION:
                        invokeCzarCardSelection(containerEntry.getValue().getHandler());
                        break;
                    case USER_CARD_SUBMITTING:
                        invokeUserCardSubmitting(containerEntry.getValue().getHandler());
                        break;
                }
            }
        });
    }
    
    public static void unregisterHandler(CaDGameHandler handler) { /* TODO: 29/05/2017 */ }
    
    /**
     * This method invokes and handles the startup phase of a game, including:
     * <ul>
     * <li>Assigning White cards to each player</li>
     * <li>Informing each player of their white cards</li>
     * <li>Selecting the (next) Card Czar</li>
     * <li>Informing the Card Czar that they have been selected</li>
     * <li>Managing time delays between card distribution and card selection stages</li>
     * </ul>
     *
     * @param toHandle {@link CaDGameHandler} - The current game instance that is being invoked
     */
    private static void invokeStart(CaDGameHandler toHandle) {
        System.out.println(toHandle.getAllPlayers());
        EmbedBuilder eb;
        StringBuilder sb;
        for (Player player : toHandle.getAllPlayers()) {
            for (int i = 0; i < toHandle.getCardCount(); i++) {
                player.addCard(CaDUtil.getRandomWhiteCard());
            }
            eb = new EmbedBuilder().setTitle("Cards Against Discord", null);
            sb = new StringBuilder();
            for (WhiteCard card : player.getCards()) {
                sb.append(">`").append(card.getText()).append("`").append("\n");
            }
            eb.addField("Your White cards (" + toHandle.getCardCount() + ")", sb.toString(), false);
            eb.appendDescription("Be sure not to show anyone else these cards!");
            eb.setFooter("The game will start in 10 seconds", null);
            player.getUser().openPrivateChannel();
            player.getUser()
                  .getPrivateChannel()
                  .sendMessage(eb.build())
                  .queue(message -> addToEmbeds(message.getId(), ResponseContainer.ResponseExpector.USER_CARD_SELECT,
                          toHandle));
            // TODO: 27/05/2017 set card czar}
        }
        //Choosing the Card Czar
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
                        "You have been marked as this round's Card Czar! In 25 seconds, you will be provided " + "with a list of cards to select from. ")
                                                       .queue());
        modifyHandler(toHandle.getOwner().getUser().getId(), PlayState.USER_CARD_SUBMITTING);
    }
    
    /**
     * This method handles the steps taken for an idle Cards Against Discord game.
     *
     * @param toHandle {@link CaDGameHandler} - The current game instance that is being invoked
     */
    private static void invokeIdle(CaDGameHandler toHandle) {
        System.out.println("Invoked IDLE!");
    }
    
    /**
     * This method handles the steps taken for a Cards Against Discord game in the 'card submission' phase.
     *
     * @param toHandle {@link CaDGameHandler} - The current game instance that is being invoked
     */
    private static void invokeUserCardSubmitting(CaDGameHandler toHandle) {
        //Issuing the response embed
        BlackCard card = CaDUtil.getRandomBlackCard();
        getHandlerContainer(toHandle.getOwner().getUser().getId()).addRound(new Round(card, toHandle.getCardCzar()));
        executorService.schedule(() -> {
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
                            executorService.schedule(() -> {
                                getEmbeds().get(message.getId()).setHandled(true);
                                privateChannel.sendMessage(
                                        "Selection time is over! The Card Czar is now selecting the winning card.")
                                              .queue();
                            }, 15, TimeUnit.SECONDS); //Disabling further adding cards past time limit
                        });
                    }));
            executorService.schedule(
                    () -> modifyHandler(toHandle.getOwner().getUser().getId(), PlayState.CZAR_CARD_SELECTION), 15,
                    TimeUnit.SECONDS);
        }, 10, TimeUnit.SECONDS);
    }
    
    /**
     * This method handles the steps taken for a Cards Against Discord game in the 'Card Czar card-selection' phase.
     *
     * @param toHandle {@link CaDGameHandler} - The current game instance that is being invoked
     */
    private static void invokeCzarCardSelection(CaDGameHandler toHandle) {
        System.out.println("Handling Czar card selection");
        //Sending submitted cards to Card Czar
        System.out.println(getHandlerContainer(toHandle.getOwner().getUser().getId()).getRounds().size());
        List<Round> rounds = getHandlerContainer(toHandle.getOwner().getUser().getId()).getRounds();
        if (rounds == null) System.out.println("Rounds = null...??");
        EmbedBuilder eb1 = new EmbedBuilder();
        StringBuilder sb1 = new StringBuilder();
        eb1.setTitle("Choose the winning card!", null);
        int i = 0;
        for (CardSubmission cardSubmission : rounds.get(rounds.size()).getCardSubmissions()) {
            i++;
            sb1.append("(").append(i).append(") ").append(cardSubmission.getWhiteCard().getText()).append("\n");
        }
        eb1.addField("Cards", sb1.toString(), false);
        System.out.println("Current Card Czar is " + toHandle.getCardCzar().getUser().getName());
        // TODO: 31/05/2017 Make message actually send, then we are complete
        toHandle.getCardCzar()
                .getUser()
                .openPrivateChannel()
                .queue(privateChannel -> privateChannel.sendMessage(eb1.build()).queue(message -> {
                    for (int ii = 0; ii < rounds.get(rounds.size()).getCardSubmissions().size(); ii++) {
                        switch (ii) {
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
                                message.addReaction(EmojiManager.getForAlias("keycap_ten").getUnicode()).queue();
                                break;
                        }
                    }
                    addToEmbeds(message.getId(), ResponseContainer.ResponseExpector.CZAR_CARD_SELECT, toHandle);
                }));
        //modifyHandler(toHandle.getOwner().getUser().getId(), PlayState.USER_CARD_SUBMITTING);
    }
    
    /**
     * This method handles the steps taken for a Cards Against Discord game distributing leaderboard statistics.
     *
     * @param toHandle {@link CaDGameHandler} - The current game instance that is being invoked
     */
    private static void invokeLeaderboards(CaDGameHandler toHandle) {
    }
    
    /**
     * This method handles the steps taken for a Cards Against Discord game in the finishing phase.
     *
     * @param toHandle {@link CaDGameHandler} - The current game instance that is being invoked
     */
    private static void invokeFinish(CaDGameHandler toHandle) {
    }
    
    public Set<Map.Entry<PlayState, HandlerContainer>> getHandlers() {
        return handlers;
    }
    
    /**
     * The PlayState represents the current state of activity that a Cards Against Discord game session is at. Due to
     * multiple sessions being capable of simultaneously running, it is possible for multiple playstates to be
     * assigned to a variety of game sessions at any one time.
     */
    public enum PlayState {
        /**
         * The game is not started, but merely created
         */
        IDLE,
        /**
         * The game is starting. Users are being assigned cards and a Card Czar is being selected.
         */
        STARTING,
        /**
         * Users are submitting white cards in response to a black card.
         */
        USER_CARD_SUBMITTING,
        /**
         * The Card Czar is selecting a winning card/card-combo.
         */
        CZAR_CARD_SELECTION,
        /**
         * The game is now displaying scoring information to individuals.
         */
        LEADERBOARDS,
        /**
         * The game is now finishing, and players are being removed.
         */
        FINISHING,
    }
}
