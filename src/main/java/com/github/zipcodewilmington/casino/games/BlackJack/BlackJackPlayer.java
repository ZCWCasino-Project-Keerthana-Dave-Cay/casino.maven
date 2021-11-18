package com.github.zipcodewilmington.casino.games.BlackJack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;

public class BlackJackPlayer implements PlayerInterface {
    CasinoAccount casinoAccount;

    public BlackJackPlayer() {
    }

    public void setCasinoAccount(CasinoAccount casinoAccount) {
        this.casinoAccount = casinoAccount;
    }

    @Override
    public CasinoAccount getArcadeAccount() {

        return casinoAccount;
    }

    @Override
    public <SomeReturnType> SomeReturnType play() { //means action of playing a car like hit, push, fold
        return null;
    }

    //setting balance
    //get balance
    //set bet amount
    //set bet multiplier
    //collect winnings
}
