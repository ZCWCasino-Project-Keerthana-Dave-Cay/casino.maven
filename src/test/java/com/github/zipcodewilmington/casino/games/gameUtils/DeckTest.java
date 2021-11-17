package com.github.zipcodewilmington.casino.games.gameUtils;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    @Test
    public void testDeckDraw(){
        //given
        Deck testDeck = new Deck();
        Cards expected = new Cards(Rank.Ace, Suit.DIAMONDS);
        //when
        testDeck.drawCard();

        Cards actual = testDeck.drawCard();
        //then
        System.out.println(testDeck.getDeck().toString());
        assertEquals(expected, actual);
        //this test will fail because card drawing is random
    }

}