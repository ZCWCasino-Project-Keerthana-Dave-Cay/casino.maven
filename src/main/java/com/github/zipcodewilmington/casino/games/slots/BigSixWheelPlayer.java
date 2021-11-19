package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;

/**
 * Created by leon on 7/21/2020.
 */


public class BigSixWheelPlayer implements PlayerInterface {
    //get balance
    //set bet amount
    //collect winnings

    private CasinoAccount casinoAccount;


    public BigSixWheelPlayer(CasinoAccount casinoAccount){
            this.casinoAccount = casinoAccount;

    }

    public CasinoAccount getArcadeAccount(){

        return casinoAccount;
    }



    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }

}