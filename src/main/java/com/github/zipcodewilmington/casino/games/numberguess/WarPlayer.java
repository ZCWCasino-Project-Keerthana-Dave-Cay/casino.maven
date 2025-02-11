package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.gameUtils.Cards;
import com.github.zipcodewilmington.casino.games.gameUtils.Deck;
import com.github.zipcodewilmington.casino.games.gameUtils.Hand;

/**
 * Created by leon on 7/21/2020.
 */
public class WarPlayer implements PlayerInterface {

    private CasinoAccount casinoAccount;

    private Deck playerHand;

    public WarPlayer(CasinoAccount givenCasinoAccount) {
        this.casinoAccount = givenCasinoAccount;
        this.playerHand = new Hand().playerWarHand();
        if (this.casinoAccount != null
                && this.casinoAccount.getAccountBalance() == null) {
            this.casinoAccount.setBalance(0);
        }
    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return casinoAccount;
    }

    @Override
    public Cards play() {

        return this.playerHand.drawCard();
    }
}