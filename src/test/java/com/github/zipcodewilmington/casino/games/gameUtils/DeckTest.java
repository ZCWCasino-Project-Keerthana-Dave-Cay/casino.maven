package com.github.zipcodewilmington.casino.games.gameUtils;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    public void test_draw_empty_deck_expect_null(){
        //given
        Deck testDeck = new Deck();
        Cards expected = new Cards(Rank.Two, Suit.DIAMONDS);
        //when
        testDeck.drawCard();
        System.out.println(testDeck);
        Cards actual = testDeck.drawCard();
        //then
        Assertions.assertNull(actual);



    }

    @Test
    public void test_draw_one_card_deck_expect_match_card(){
        //given
        Deck testDeck = new Deck();
        Cards expectedCard = new Cards(Rank.Two, Suit.DIAMONDS);
        //when
        testDeck.add(expectedCard);
        Cards actual = testDeck.drawCard();
        //then
        Assertions.assertEquals(expectedCard.toString(),actual.toString());
        System.out.println(testDeck);
    }

    @Test
    public void test_two_consecutive_draw_with_one_card_deck_expect_null_for_second_draw(){


    }

}