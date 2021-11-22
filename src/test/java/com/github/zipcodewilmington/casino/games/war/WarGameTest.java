package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.gameUtils.Cards;
import com.github.zipcodewilmington.casino.games.gameUtils.Deck;
import com.github.zipcodewilmington.casino.games.gameUtils.Rank;
import com.github.zipcodewilmington.casino.games.gameUtils.Suit;
import com.github.zipcodewilmington.casino.games.numberguess.WarEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WarGameTest {

    WarEngine warEngine;

    CasinoAccount account = new CasinoAccount("test", "test");

    Deck deck = new Deck();

    PlayerInterface player = new PlayerInterface() {
        @Override
        public CasinoAccount getArcadeAccount() {
            return account;
        }

        @Override
        public Cards play() {
            return deck.drawCard();
        }
    };

    PlayerInterface dealer = new PlayerInterface() {
        @Override
        public CasinoAccount getArcadeAccount() {
            return null;
        }

        @Override
        public Cards play() {
            return deck.drawCard();
        }
    };

    @BeforeEach
    public void setup() {
        warEngine = new WarEngine(dealer, player);
    }

    @Test
    public void testGame_whoiswinner_player() {

        // Given
        String expected = "PLAYER";
        Cards playerCards = new Cards(Rank.Ace, Suit.DIAMONDS);
        Cards dealerCards = new Cards(Rank.Seven, Suit.HEARTS);

        // when
        String winner = warEngine.whoIsWinner(playerCards, dealerCards, false);

        // then
        Assertions.assertNotNull(winner);
        Assertions.assertEquals(expected, winner);
    }

    @Test
    public void testGame_whoiswinner_dealer() {

        // Given
        String expected = "DEALER";
        Cards playerCards = new Cards(Rank.Five, Suit.DIAMONDS);
        Cards dealerCards = new Cards(Rank.Seven, Suit.HEARTS);

        // when
        String winner = warEngine.whoIsWinner(playerCards, dealerCards, false);

        // then
        Assertions.assertNotNull(winner);
        Assertions.assertEquals(expected, winner);
    }

    @Test
    public void testGame_whoiswinner_tie_normal_play() {

        // Given
        String expected = "TIE";
        Cards playerCards = new Cards(Rank.Ace, Suit.SPADES);
        Cards dealerCards = new Cards(Rank.Ace, Suit.SPADES);

        // when
        String winner = warEngine.whoIsWinner(playerCards, dealerCards, false);

        // then
        Assertions.assertNotNull(winner);
        Assertions.assertEquals(expected, winner);
    }

    @Test
    public void testGame_whoiswinner_tie_war_play() {

        // Given
        String expected = "PLAYER";
        Cards playerCards = new Cards(Rank.Ace, Suit.SPADES);
        Cards dealerCards = new Cards(Rank.Ace, Suit.SPADES);

        // when
        String winner = warEngine.whoIsWinner(playerCards, dealerCards, true);

        // then
        Assertions.assertEquals(expected, winner);
    }

    @Test
    public void testGame_deductTieAmountIfEligible() {

        // Given
        int tieAmount = 10;
        int balance = 10;
        int expected = balance -tieAmount;
        account.setBalance(balance);

        // when
        warEngine.deductTieAmountIfEligible(tieAmount);

        // then
        Assertions.assertEquals(expected, account.getAccountBalance());
    }

    @Test
    public void testGame_deductTieAmountIfEligible_negative_tieamount() {

        // Given
        int tieAmount = -100;
        int balance = 100;
        account.setBalance(balance);

        // when
        warEngine.deductTieAmountIfEligible(tieAmount);

        // then
        Assertions.assertEquals(balance, account.getAccountBalance());
    }

    @Test
    public void testGame_adjustAccountBalance_player_won() {

        // Given
        int betAmount = 100;
        int tieAmount = 10;
        int balance = 1000;
        account.setBalance(balance);

        // when
        warEngine.adjustAccountBalance("PLAYER", betAmount, tieAmount);
        int expectedAmount = (balance + betAmount - tieAmount);

        // then
        Assertions.assertEquals(expectedAmount , account.getAccountBalance());
    }

    @Test
    public void testGame_adjustAccountBalance_dealer_won() {

        // Given
        int betAmount = 100;
        int tieAmount = 10;
        int balance = 1000;
        account.setBalance(balance);

        // when
        warEngine.adjustAccountBalance("DEALER", betAmount, tieAmount);
        int expectedAmount = (balance - betAmount - tieAmount);

        // then
        Assertions.assertEquals(expectedAmount , account.getAccountBalance());
    }

    @Test
    public void testGame_adjustAccountBalance_tie() {

        // Given
        int betAmount = 100;
        int tieAmount = 10;
        int balance = 1000;
        account.setBalance(balance);

        // when
        warEngine.adjustAccountBalance("TIE", betAmount, tieAmount);
        int expectedAmount = (balance + tieAmount);

        // then
        Assertions.assertEquals(expectedAmount , account.getAccountBalance());
    }

    @Test
    public void testGame_displayCard() {

        // Given
        Cards playerCards = new Cards(Rank.Ace, Suit.DIAMONDS);

        // when
        warEngine.displayCard(playerCards);

        // then
        Assertions.assertNotNull(warEngine);
    }
}
