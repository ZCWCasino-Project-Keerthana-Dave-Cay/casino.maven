package com.github.zipcodewilmington.casino.games.BlackJack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.games.gameUtils.Cards;
import com.github.zipcodewilmington.casino.games.gameUtils.Hand;
import com.github.zipcodewilmington.casino.games.gameUtils.Rank;
import com.github.zipcodewilmington.casino.games.gameUtils.Suit;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlackJackEngineTest {
    BlackJackEngine systemUnderTest;
    BlackJackPlayer dealer;
    BlackJackPlayer player;
    IOConsole consoleTest;

    @Before
    public void setUp() throws Exception {
        dealer = new BlackJackPlayer();
        player = new BlackJackPlayer();
        String acctName = "testPlayer";
        String acctPw = "password";
        CasinoAccount casinoAccount = new CasinoAccount(acctName, acctPw);
        player.setCasinoAccount(casinoAccount);
        consoleTest = new IOConsole();
        systemUnderTest = new BlackJackEngine(dealer, player, consoleTest);

    }

    @Test
    public void testPlayerInitializeHand() {
        //given
        Hand testHandPlayer = systemUnderTest.getPlayerBJHand();
        Integer expected = 2;
        //when
        systemUnderTest.initializePlayerHand();
        Integer actual = testHandPlayer.getSize();
        //then
        assertEquals(expected,actual);
    }

    @Test
    public void testDealerInitializeHand() {
        //given
        Hand testHandDealer = systemUnderTest.getDealerBJHand();
        Integer expected = 2;
        //when
        systemUnderTest.initializeDealerHand();
        Integer actual = testHandDealer.getSize();
        //then
        assertEquals(expected,actual);
    }

    @Test
    public void testGetBlackHandTotal(){
        //given
        Cards testCard = new Cards(Rank.Two, Suit.DIAMONDS);
        Hand testHand = new Hand();
        testHand.add(testCard);
        Integer expected = 2;
        //when
        Integer actual = systemUnderTest.getBlackJackHandTotal(testHand);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBlackHandTotal_with_Aces(){
        //given
        Cards testCardAceDiamonds = new Cards(Rank.Ace, Suit.DIAMONDS);
        Cards testCardAceClubs = new Cards(Rank.Ace, Suit.CLUBS);
        Cards testCardKing = new Cards(Rank.King, Suit.DIAMONDS);

        Hand testHand = new Hand();
        testHand.add(Arrays.asList(testCardAceClubs, testCardKing, testCardAceDiamonds));
        Integer expected = 22;
        //when
        Integer actual = systemUnderTest.getBlackJackHandTotal(testHand);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBlackHandTotal_with_One_Ace(){
        //given
        Cards testCardAceDiamonds = new Cards(Rank.Ace, Suit.DIAMONDS);
        Cards testCardAceClubs = new Cards(Rank.Ten, Suit.CLUBS);
        Cards testCardKing = new Cards(Rank.King, Suit.DIAMONDS);

        Hand testHand = new Hand();
        testHand.add(Arrays.asList(testCardAceClubs, testCardKing, testCardAceDiamonds));
        Integer expected = 21;
        //when
        Integer actual = systemUnderTest.getBlackJackHandTotal(testHand);
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testWhenPlayerHasBlackJack(){
        //given
        Hand testHand = new Hand();
        List<Cards> drawnCards = new ArrayList<>();
        drawnCards.add(new Cards(Rank.Ace, Suit.DIAMONDS));
        drawnCards.add(new Cards(Rank.King, Suit.DIAMONDS));
        testHand.add(drawnCards);
        System.setIn(new ByteArrayInputStream("STAND".getBytes()));
        BlackJackEngine systemWithFakeInput = new BlackJackEngine(dealer, player, new IOConsole());
        //from lines 110-111 this is for fake input when testing user input methods
        //when
        boolean actual = systemWithFakeInput.playerHitStandCycle(testHand);
        //then
        assertTrue(actual);
    }

    @Test
    public void testPlayerWantsToSeeRules() {
        // given
        String playerResponse = "YES";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(playerResponse.getBytes()));

        // when
        BlackJackEngine systemWithFakeInput = new BlackJackEngine(dealer, player, new IOConsole());
        systemWithFakeInput.displayRules();

        // then
        assertTrue(outContent.toString().contains(systemWithFakeInput.BLACKJACK_RULES));
    }

    //    @Test
//    public void testWhenDealerHasBlackJack() {
//        //given
//        Hand testDealerHand = new Hand();
//        List<Cards> drawnCards = new ArrayList<>();
//        drawnCards.add(new Cards(Rank.Ace, Suit.DIAMONDS));
//        drawnCards.add(new Cards(Rank.King, Suit.DIAMONDS));
//        testDealerHand.add(drawnCards);
//
//        System.setIn(new ByteArrayInputStream("STAND".getBytes()));
//        BlackJackEngine systemWithFakeInput = new BlackJackEngine(dealer, player, new IOConsole());
//        systemWithFakeInput.dealerBJHand = testDealerHand;
//        //when
//        boolean actual = systemWithFakeInput.dealerHitOrStandCycle();
//        //then
//        assertTrue(actual);
//    }

    @Test
    public void testBetCycle_Accepted(){
        String playerBet = "10";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(playerBet.getBytes()));

        // when
        BlackJackEngine systemWithFakeInput = new BlackJackEngine(dealer, player, new IOConsole());
        systemWithFakeInput.player.casinoAccount.setBalance(100);
        Integer actual = systemWithFakeInput.betCycle();

        // then
        Assert.assertEquals(Integer.valueOf(playerBet), actual);
        Assert.assertTrue(outContent.toString().contains("Bet accepted!"));
    }

    @Test
    public void testBetCycle_Insufficient_Funds_Then_Valid_Bet(){
        String playerBet = "10";
        String playerSecondBet = "5";
        String playerBetInputs = playerBet + System.lineSeparator() + playerSecondBet;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(playerBetInputs.getBytes()));

        // when
        BlackJackEngine systemWithFakeInput = new BlackJackEngine(dealer, player, new IOConsole());
        systemWithFakeInput.player.casinoAccount.setBalance(5);
        Integer actual = systemWithFakeInput.betCycle();

        // then
        Assert.assertEquals(Integer.valueOf(playerSecondBet), actual);
        Assert.assertTrue(outContent.toString().contains("Insufficient funds, try again!"));
    }

    @Test
    public void testGetUserBet(){
        String playerBet = "10";
        System.setIn(new ByteArrayInputStream(playerBet.getBytes()));

        // when
        BlackJackEngine systemWithFakeInput = new BlackJackEngine(dealer, player, new IOConsole());
        Integer actual = systemWithFakeInput.getUserBetAmount();

        // then
        Assert.assertEquals(Integer.valueOf(playerBet), actual);

    }

    @Test
    public void testCheckBetAccepted(){
        // given
        Integer betAmount = 10;
        Integer balance = 100;
        systemUnderTest.player.casinoAccount.setBalance(balance);
        // when
        boolean actual = systemUnderTest.checkBet(betAmount);

        // then
        Assert.assertTrue(actual);
    }

    @Test
    public void testCheckBetFailed(){
        // given
        Integer betAmount = 10;
        Integer balance = 0;
        systemUnderTest.player.casinoAccount.setBalance(balance);
        // when
        boolean actual = systemUnderTest.checkBet(betAmount);

        // then
        Assert.assertFalse(actual);
    }

    @Test
    public void testValidateTotal(){

    }
}