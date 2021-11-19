package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.gameUtils.Cards;
import com.github.zipcodewilmington.casino.games.gameUtils.Deck;
import com.github.zipcodewilmington.casino.games.gameUtils.Hand;

/**
 * Created by leon on 7/21/2020.
 */
public class Dealer implements PlayerInterface {

    private Deck dealerHand;

    public Dealer() {
        this.dealerHand = new Hand().dealerWarHand();
    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return null;
    }

    @Override
    public Cards play() {

        return this.dealerHand.drawCard();
    }
}