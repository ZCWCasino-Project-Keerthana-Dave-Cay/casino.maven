package com.github.zipcodewilmington.casino.games.BlackJack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.games.gameUtils.*;
import com.github.zipcodewilmington.utils.IOConsole;
import com.oracle.tools.packager.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class BlackJackEngineTest {
    BlackJackEngine systemUnderTest;
    BlackJackPlayer dealer;
    BlackJackPlayer player;
    IOConsole consoleTest;
    Deck testCards;

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
    public void testPlayerWantsToSeeRules_Yes() {
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

    @Test
    public void testPlayerWantsToSeeRules_No() {
        // given
        String playerResponse = "NO";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(playerResponse.getBytes()));

        // when
        BlackJackEngine systemWithFakeInput = new BlackJackEngine(dealer, player, new IOConsole());
        systemWithFakeInput.displayRules();

        // then
        assertFalse(outContent.toString().contains(systemWithFakeInput.BLACKJACK_RULES));
    }

    @Test
    public void testWhenDealerHasBlackJack() {
        //given
        Hand testDealerHand = new Hand();
        List<Cards> drawnCards = new ArrayList<>();
        drawnCards.add(new Cards(Rank.Ace, Suit.DIAMONDS));
        drawnCards.add(new Cards(Rank.King, Suit.DIAMONDS));
        testDealerHand.add(drawnCards);
        Integer expectedHandSize = 2;
        systemUnderTest.dealerBJHand.add(drawnCards);
        // when
        systemUnderTest.dealerHitOrStandCycle();
        Integer actual = systemUnderTest.dealerBJHand.getSize();

        //then
        assertEquals(expectedHandSize, actual);
    }

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
        //given
        Hand testHand = new Hand();
        List<Cards> drawnCards = new ArrayList<>();
        drawnCards.add(new Cards(Rank.Ace, Suit.DIAMONDS));
        drawnCards.add(new Cards(Rank.King, Suit.DIAMONDS));
        testHand.add(drawnCards);
        //from lines 110-111 this is for fake input when testing user input methods
        //when
        boolean actual = systemUnderTest.validateTotal(testHand);
        //then
        assertTrue(actual);
    }

    @Test
    public void testIsBlackJack(){
        //given
        Hand testHand = new Hand();
        List<Cards> drawnCards = new ArrayList<>();
        drawnCards.add(new Cards(Rank.Ace, Suit.DIAMONDS));
        drawnCards.add(new Cards(Rank.King, Suit.DIAMONDS));
        testHand.add(drawnCards);
        //from lines 110-111 this is for fake input when testing user input methods
        //when
        boolean actual = systemUnderTest.isBlackJack(testHand);
        //then
        assertTrue(actual);
    }

    @Test
    public void testGetBlackJackHandTotalWithBlackJack() {
        //given
        Hand testDealerHand = new Hand();
        List<Cards> drawnCards = new ArrayList<>();
        drawnCards.add(new Cards(Rank.Ace, Suit.DIAMONDS));
        drawnCards.add(new Cards(Rank.King, Suit.DIAMONDS));
        testDealerHand.add(drawnCards);
        Integer expectedHandValue = 21;
        // when
        Integer actualBlackJack = systemUnderTest.getBlackJackHandTotal(testDealerHand);
        //then
        assertEquals(expectedHandValue, actualBlackJack);

    }

    @Test
    public void testWhenDealerDoeNotHaveBJ_But_Lower_Than_21() {
        //given
        Stack<Cards> testCards = new Stack<>();
        testCards.add(new Cards(Rank.Two, Suit.DIAMONDS));
        testCards.add(new Cards(Rank.Two, Suit.SPADES));
        testCards.add(new Cards(Rank.Two, Suit.CLUBS));
        testCards.add(new Cards(Rank.Three, Suit.DIAMONDS));
        testCards.add(new Cards(Rank.Three, Suit.SPADES));
        testCards.add(new Cards(Rank.Three, Suit.CLUBS));
        testCards.add(new Cards(Rank.Four, Suit.DIAMONDS));
        testCards.add(new Cards(Rank.Four, Suit.SPADES));
        testCards.add(new Cards(Rank.Four, Suit.CLUBS));
        Hand testDealerHand = new Hand();
        List<Cards> drawnCards = new ArrayList<>();
        drawnCards.add(new Cards(Rank.Two, Suit.DIAMONDS));
        drawnCards.add(new Cards(Rank.Two, Suit.DIAMONDS));
        testDealerHand.add(drawnCards);
        Integer expectedHandSize = 4;

        systemUnderTest.dealerBJHand.add(drawnCards);
        // when
        systemUnderTest.dealerHitOrStandCycle();
        Integer actual = systemUnderTest.dealerBJHand.getSize();
        //then
        assertTrue(actual > 2);
    }

    @Test
    public void testWhenDealerDoeNotHaveBJ_But_Busts() {
        //given
        Stack<Cards> testCards = new Stack<>();
        testCards.add(new Cards(Rank.Queen, Suit.SPADES));
        testCards.add(new Cards(Rank.Queen, Suit.CLUBS));
        testCards.add(new Cards(Rank.King, Suit.DIAMONDS));
        testCards.add(new Cards(Rank.King, Suit.SPADES));
        testCards.add(new Cards(Rank.King, Suit.CLUBS));
        Hand testDealerHand = new Hand();
        List<Cards> drawnCards = new ArrayList<>();
        drawnCards.add(new Cards(Rank.Jack, Suit.DIAMONDS));
        drawnCards.add(new Cards(Rank.Six, Suit.CLUBS));
        testDealerHand.add(drawnCards);
        Integer expectedHandSize = 3;
        systemUnderTest.dealerBJHand.add(drawnCards);
        // when
        systemUnderTest.dealerHitOrStandCycle();
        Integer actual = systemUnderTest.dealerBJHand.getSize();

        //then
        assertEquals(expectedHandSize, actual);
    }

    @Test
    public void testRestartGamePrompt_No(){
        //given
        System.setIn(new ByteArrayInputStream("NO".getBytes()));
        BlackJackEngine systemWithFakeInput = new BlackJackEngine(dealer, player, new IOConsole());
        //when
        boolean actual = systemWithFakeInput.promptRestartGame();
        //then
        assertFalse(actual);
    }

    @Test
    public void testRestartGamePrompt_Yes(){
        //given
        System.setIn(new ByteArrayInputStream("YES".getBytes()));
        BlackJackEngine systemWithFakeInput = new BlackJackEngine(dealer, player, new IOConsole());
        //when
        boolean actual = systemWithFakeInput.promptRestartGame();
        //then
        assertTrue(actual);
    }

    @Test
    public void testHitAction() {
        //given
        Hand testDealerHand = new Hand();
        List<Cards> drawnCards = new ArrayList<>();
        drawnCards.add(new Cards(Rank.Ace, Suit.DIAMONDS));
        drawnCards.add(new Cards(Rank.Three, Suit.DIAMONDS));
        testDealerHand.add(drawnCards);
        Integer expectedHandSize = 2;
        systemUnderTest.playerBJHand.add(drawnCards);
        // when
        systemUnderTest.hitAction(testDealerHand);
        Integer actual = systemUnderTest.playerBJHand.getSize();
        //then
        assertEquals(expectedHandSize, actual);
    }

    @Test
    public void testDistributeWinnings_Push() {
        //given
        CasinoAccount testPlayer = new CasinoAccount("cay", "1");
        testPlayer.addWinningsToBalance(1000);
        player.setCasinoAccount(testPlayer);

        Hand testDealerHand = new Hand();
        List<Cards> drawnCards = new ArrayList<>();
        drawnCards.add(new Cards(Rank.Ace, Suit.DIAMONDS));
        drawnCards.add(new Cards(Rank.Three, Suit.DIAMONDS));
        testDealerHand.add(drawnCards);

        //given
        Hand testPlayerHand = new Hand();
        List<Cards> drawnCardsForPlayer = new ArrayList<>();
        drawnCardsForPlayer.add(new Cards(Rank.Ace, Suit.DIAMONDS));
        drawnCardsForPlayer.add(new Cards(Rank.Three, Suit.DIAMONDS));
        testPlayerHand.add(drawnCardsForPlayer);

        BlackJackEngine newTestEngine = new BlackJackEngine(dealer, player, new IOConsole());
        newTestEngine.playerBJHand = testPlayerHand;
        newTestEngine.dealerBJHand = testDealerHand;

        //when
        newTestEngine.distributeWinnings(10);
        assertTrue(newTestEngine.getBlackJackHandTotal(testPlayerHand) ==
                newTestEngine.getBlackJackHandTotal(testDealerHand));

        //then
    }

    @Test
    public void testDistributeWinnings_DealerWin() {
        //given
        CasinoAccount testPlayer = new CasinoAccount("cay", "1");
        testPlayer.addWinningsToBalance(1000);
        player.setCasinoAccount(testPlayer);

        Hand testDealerHand = new Hand();
        List<Cards> drawnCards = new ArrayList<>();
        drawnCards.add(new Cards(Rank.Ace, Suit.DIAMONDS));
        drawnCards.add(new Cards(Rank.Ten, Suit.DIAMONDS));
        testDealerHand.add(drawnCards);

        Hand testPlayerHand = new Hand();
        List<Cards> drawnCardsForPlayer = new ArrayList<>();
        drawnCardsForPlayer.add(new Cards(Rank.Ace, Suit.DIAMONDS));
        drawnCardsForPlayer.add(new Cards(Rank.Three, Suit.DIAMONDS));
        testPlayerHand.add(drawnCardsForPlayer);

        BlackJackEngine newTestEngine = new BlackJackEngine(dealer, player, new IOConsole());
        newTestEngine.playerBJHand = testPlayerHand;
        newTestEngine.dealerBJHand = testDealerHand;

        //when
        newTestEngine.distributeWinnings(10);
        //then
        assertTrue(newTestEngine.getBlackJackHandTotal(testPlayerHand) <
                newTestEngine.getBlackJackHandTotal(testDealerHand));

    }

    @Test
    public void testDistributeWinnings_PlayerWin() {
        //given
        CasinoAccount testPlayer = new CasinoAccount("cay", "1");
        testPlayer.addWinningsToBalance(1000);
        player.setCasinoAccount(testPlayer);
        Hand testDealerHand = new Hand();
        List<Cards> drawnCards = new ArrayList<>();
        drawnCards.add(new Cards(Rank.Ace, Suit.DIAMONDS));
        drawnCards.add(new Cards(Rank.Three, Suit.DIAMONDS));
        testDealerHand.add(drawnCards);

        Hand testPlayerHand = new Hand();
        List<Cards> drawnCardsForPlayer = new ArrayList<>();
        drawnCardsForPlayer.add(new Cards(Rank.Ace, Suit.DIAMONDS));
        drawnCardsForPlayer.add(new Cards(Rank.Ten, Suit.DIAMONDS));
        testPlayerHand.add(drawnCardsForPlayer);

        BlackJackEngine newTestEngine = new BlackJackEngine(dealer, player, new IOConsole());
        newTestEngine.playerBJHand = testPlayerHand;
        newTestEngine.dealerBJHand = testDealerHand;

        //when
        newTestEngine.distributeWinnings(10);
        //then
        assertTrue(newTestEngine.getBlackJackHandTotal(testPlayerHand) >
                newTestEngine.getBlackJackHandTotal(testDealerHand));
    }

    @Test
    public void testDistributeWinnings_DealerOver21() {
        //given
        CasinoAccount testPlayer = new CasinoAccount("cay", "1");
        testPlayer.addWinningsToBalance(1000);
        player.setCasinoAccount(testPlayer);
        Hand testDealerHand = new Hand();
        List<Cards> drawnCards = new ArrayList<>();
        drawnCards.add(new Cards(Rank.Ten, Suit.CLUBS));
        drawnCards.add(new Cards(Rank.Three, Suit.DIAMONDS));
        drawnCards.add(new Cards(Rank.Ten, Suit.DIAMONDS));
        testDealerHand.add(drawnCards);

        BlackJackEngine newTestEngine = new BlackJackEngine(dealer, player, new IOConsole());
        newTestEngine.dealerBJHand = testDealerHand;

        //when
        newTestEngine.distributeWinnings(10);
        //then
        assertTrue(newTestEngine.getBlackJackHandTotal(testDealerHand) > 21);
    }

    @Test
    public void testDetermineWinner_IfPlayerBusts() {
        // given
        CasinoAccount testPlayer = new CasinoAccount("cay", "1");
        testPlayer.addWinningsToBalance(1000);
        player.setCasinoAccount(testPlayer);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "You bust, therefore you lose your initial bet!";

        // when
        BlackJackEngine systemWithFakeInput = new BlackJackEngine(dealer, player, new IOConsole());
        systemWithFakeInput.determineWinner(true, 10);

        // then
        assertTrue(outContent.toString().contains(expected));
    }

    @Test
    public void testDetermineWinner_IfPlayerDoesntBust() {
        // given
        CasinoAccount testPlayer = new CasinoAccount("cay", "1");
        testPlayer.addWinningsToBalance(1000);
        player.setCasinoAccount(testPlayer);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Dealer's hand: %s, Dealer's total: %d\n\n";

        // when
        BlackJackEngine systemWithFakeInput = new BlackJackEngine(dealer, player, new IOConsole());
        systemWithFakeInput.determineWinner(false, 10);

        // then
        assertFalse(outContent.toString().contains(expected));
    }

    @Test
    public void testWelcomePlayer() {
        // given
        CasinoAccount testPlayer = new CasinoAccount("cay", "1");
        testPlayer.addWinningsToBalance(1000);
        player.setCasinoAccount(testPlayer);
        String expected = "\n \n Welcome to the table, %s%n";
        //when
        systemUnderTest.welcomePlayer();
        //then
        assertEquals(expected, "\n \n Welcome to the table, %s%n");
    }
}
