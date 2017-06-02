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
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.CardSubmission;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.Player;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.Round;
import net.dv8tion.jda.core.events.message.priv.react.GenericPrivateMessageReactionEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;
import java.util.stream.Collectors;

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
                    case USER_CARD_SELECT:
                        handleUserCardSelect(event.getReactionEmote().getName(), event);
                        break;
                    case CZAR_CARD_SELECT:
                        handleCzarCardSelect(event.getReactionEmote().getName(), event);
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
     * This method is designed to handle and respond to the card selection phase of a game.
     *
     * @param emoteName String - The emote that was modified
     * @param event     {@link GenericPrivateMessageReactionEvent} - The event instance that triggered this method's call
     */
    private void handleUserCardSelect(String emoteName, GenericPrivateMessageReactionEvent event) {
        System.out.println("Now handling user card selection/reaction change");
        ResponseContainer responseContainer = CaDGameManager.getEmbeds().get(event.getMessageId());
        int chosen = -1;
        if (emoteName.equals(EmojiManager.getForAlias("one").getUnicode())) {
            chosen = 1;
        } else if (emoteName.equals(EmojiManager.getForAlias("two").getUnicode())) {
            chosen = 2;
        } else if (emoteName.equals(EmojiManager.getForAlias("three").getUnicode())) {
            chosen = 3;
        } else if (emoteName.equals(EmojiManager.getForAlias("four").getUnicode())) {
            chosen = 4;
        } else if (emoteName.equals(EmojiManager.getForAlias("five").getUnicode())) {
            chosen = 5;
        } else if (emoteName.equals(EmojiManager.getForAlias("six").getUnicode())) {
            chosen = 6;
        } else if (emoteName.equals(EmojiManager.getForAlias("seven").getUnicode())) {
            chosen = 7;
        } else if (emoteName.equals(EmojiManager.getForAlias("eight").getUnicode())) {
            chosen = 8;
        } else if (emoteName.equals(EmojiManager.getForAlias("nine").getUnicode())) {
            chosen = 9;
        } else if (emoteName.equals(EmojiManager.getForAlias("keycap_ten").getUnicode())) {
            chosen = 10;
        }
        System.out.println("User chose card number " + chosen);
        if (chosen > responseContainer.getCaDGameHandler().getCardCount()) {
            System.out.println("Return 1");
            return; //User added their own reaction?
        }
        List<Player> players = responseContainer.getCaDGameHandler()
                                                .getAllPlayersNonCzar()
                                                .stream()
                                                .filter(player -> player.getUser()
                                                                        .getId()
                                                                        .equals(event.getUser().getId()))
                                                .collect(Collectors.toList());
        if (players.size() != 1) {
            System.out.println("Return 2");
            return; //Seemingly unreachable issue, but acts as a failsafe
        }
        CaDGameManager.getHandlerContainer(responseContainer.getCaDGameHandler().getOwner().getUser().getId())
                      .getRound(responseContainer.getCaDGameHandler().getCardCzar())
                      .addCardSubmission(new CardSubmission(players.get(0).getCards().get(chosen - 1), players.get(0)));
        System.out.println(
                "Added new card submission!" + players.get(0).getCards().get(chosen - 1) + ":" + players.get(0));
        responseContainer.setHandled(true);
        event.getChannel()
             .sendMessage("Submitted your card: " + players.get(0).getCards().get(chosen - 1).getText())
             .queue();
    }
    
    private void handleCzarCardSelect(String emoteName, GenericPrivateMessageReactionEvent event) {
        System.out.println("Now handling czar card selection/reaction change");
        ResponseContainer responseContainer = CaDGameManager.getEmbeds().get(event.getMessageId());
        int chosen = -1;
        if (emoteName.equals(EmojiManager.getForAlias("one").getUnicode())) {
            chosen = 1;
        } else if (emoteName.equals(EmojiManager.getForAlias("two").getUnicode())) {
            chosen = 2;
        } else if (emoteName.equals(EmojiManager.getForAlias("three").getUnicode())) {
            chosen = 3;
        } else if (emoteName.equals(EmojiManager.getForAlias("four").getUnicode())) {
            chosen = 4;
        } else if (emoteName.equals(EmojiManager.getForAlias("five").getUnicode())) {
            chosen = 5;
        } else if (emoteName.equals(EmojiManager.getForAlias("six").getUnicode())) {
            chosen = 6;
        } else if (emoteName.equals(EmojiManager.getForAlias("seven").getUnicode())) {
            chosen = 7;
        } else if (emoteName.equals(EmojiManager.getForAlias("eight").getUnicode())) {
            chosen = 8;
        } else if (emoteName.equals(EmojiManager.getForAlias("nine").getUnicode())) {
            chosen = 9;
        } else if (emoteName.equals(EmojiManager.getForAlias("keycap_ten").getUnicode())) {
            chosen = 10;
        }
        System.out.println("Czar chose card number " + chosen);
        if (chosen > responseContainer.getCaDGameHandler().getCardCount()) {
            System.out.println("Return 1");
            return; //User added their own reaction?
        }
        Round finalRound = CaDGameManager.getHandlerContainer(
                responseContainer.getCaDGameHandler().getOwner().getUser().getId())
                                         .getRound(responseContainer.getCaDGameHandler().getCardCzar());
        System.out.println(
                finalRound.getCardCzar().getUser().getName() + " has chosen \"" + finalRound.getCardSubmissions()
                                                                                            .get(chosen - 1)
                                                                                            .getWhiteCard()
                                                                                            .getText() + "\" as this rounds winning card!");
        Player winningPlayer = finalRound.getCardSubmissions().get(chosen - 1).getPlayer();
        winningPlayer.incrementRoundsWon();
        ResponseContainer container = CaDGameManager.getEmbeds().get(event.getMessageId());
        container.setHandled(true); //No further selection of cards
        container.getCaDGameHandler()
                 .getAllPlayersNonCzar()
                 .forEach(player -> player.getUser().openPrivateChannel().queue(privateChannel -> {
                     if (player.getUser().getId().equals(winningPlayer.getUser().getId())) privateChannel.sendMessage(
                             "You have won the round! Your score has been incremented. Current score: " + player.getRoundsWon())
                                                                                                         .queue();
                     else {
                         privateChannel.sendMessage("You haven't won the round :( Better luck next round!").queue();
                         player.incrementRoundsLost(); //Increase lost rounds
                     }
                 }));
        container.getCaDGameHandler()
                 .getCardCzar()
                 .getUser()
                 .openPrivateChannel()
                 .queue(privateChannel -> privateChannel.sendMessage("Good choice!").queue());
    }
}
