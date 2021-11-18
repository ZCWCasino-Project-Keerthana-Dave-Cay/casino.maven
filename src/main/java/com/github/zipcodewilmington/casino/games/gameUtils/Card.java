package com.github.zipcodewilmington.casino.games.gameUtils;


public class Card {

    private Suit suit;
    private Rank rank;

    public Card(Rank rank, Suit suit) {
        this.suit = suit;
        this.rank = rank;
    }


    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }

    @Override
    public String toString() {
        return this.rank.getDisplayValue() + this.suit.getGraphic();
    }
}
