package com.github.zipcodewilmington.casino.games.BlackJack;

import com.github.zipcodewilmington.casino.games.gameUtils.Card;
import com.github.zipcodewilmington.casino.games.gameUtils.Hand;
import com.github.zipcodewilmington.casino.games.gameUtils.Rank;
import com.github.zipcodewilmington.casino.games.gameUtils.Suit;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlackJackEngineTest {
    @Test
    public void testValueOfCard(){
        //given
        Card testCard = new Card(Rank.Two, Suit.DIAMONDS);
        Hand testHand = new Hand();
        testHand.add(testCard);
        Integer expected = 2;
        //when
        Integer actual = testHand.getBlackJackHandTotal();
        //then
        assertEquals(expected, actual);

    }

}