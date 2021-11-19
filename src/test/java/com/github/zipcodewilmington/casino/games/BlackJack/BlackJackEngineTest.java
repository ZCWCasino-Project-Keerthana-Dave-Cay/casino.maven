package com.github.zipcodewilmington.casino.games.BlackJack;

import com.github.zipcodewilmington.casino.games.gameUtils.Cards;
import com.github.zipcodewilmington.casino.games.gameUtils.Hand;
import com.github.zipcodewilmington.casino.games.gameUtils.Rank;
import com.github.zipcodewilmington.casino.games.gameUtils.Suit;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BlackJackEngineTest {
    BlackJackEngine systemUnderTest;
    BlackJackPlayer dealer;
    BlackJackPlayer player;

    @Before
    public void setUp() throws Exception {
        dealer = new BlackJackPlayer();
        player = new BlackJackPlayer();
        systemUnderTest = new BlackJackEngine(dealer, player);
    }

    @Test
    public void testPlayerInitializeHand() {


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

}