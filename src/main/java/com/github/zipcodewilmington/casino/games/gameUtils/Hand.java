package com.github.zipcodewilmington.casino.games.gameUtils;


public class Hand extends Deck {


    public Deck dealerBlackJackHand() {
        Deck dealersHandBJ = new Deck();
        dealersHandBJ.drawMultipleCards(2);
        return dealersHandBJ;
    }

    public Deck dealerWarHand() {
        Deck dealerHandWar = new Deck();
        dealerHandWar.drawMultipleCards(1);
        return dealerHandWar;
    }

    public Deck playerBlackJackHand(){
        Deck playersHandBJ = new Deck();
        playersHandBJ.drawMultipleCards(2);
        return playersHandBJ;
    }

    public Deck playerWarHand(){
        Deck playersHandWar = new Deck();
        playersHandWar.drawMultipleCards(1);
        return playersHandWar;
    }

}
