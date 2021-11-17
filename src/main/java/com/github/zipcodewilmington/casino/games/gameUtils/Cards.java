package com.github.zipcodewilmington.casino.games.gameUtils;

public class Cards {

    private Suit suit;
    private Rank rank;
    public Cards(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }


    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }


}
