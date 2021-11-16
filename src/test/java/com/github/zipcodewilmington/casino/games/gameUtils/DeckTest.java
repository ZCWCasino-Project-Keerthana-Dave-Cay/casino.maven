package com.github.zipcodewilmington.casino.games.gameUtils;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    @Test
    public void testDeckDraw(){
        //given
        Deck testDeck = new Deck();
        Cards expected = new Cards(Suit.DIAMONDS, Rank.Two);
        //when
        testDeck.draw();
        System.out.println(testDeck);
        Cards actual = testDeck.draw();
        //then
        assertEquals(expected, actual);
    }

}