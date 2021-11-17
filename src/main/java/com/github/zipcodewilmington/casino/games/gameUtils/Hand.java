package com.github.zipcodewilmington.casino.games.gameUtils;



public class Hand extends Deck {


    public Deck dealerBlackJackHand() {
        Deck dealersHandBJ = new Deck();
        dealersHandBJ.drawMultipleCards(2);
        return dealersHandBJ;
    }




}
